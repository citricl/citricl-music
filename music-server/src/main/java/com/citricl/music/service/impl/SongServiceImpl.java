package com.citricl.music.service.impl;

import com.citricl.music.dao.*;
import com.citricl.music.domain.*;
import com.citricl.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Autowired
    private ListSongMapper listSongMapper;

    @Autowired
    private SongCountMapper songCountMapper;

    @Autowired
    private SingerMapper singerMapper;

    @Autowired
    private RecommendMapper recommendMapper;

    @Override
    public List<Song> allSong()
    {
        return songMapper.allSong();
    }

    @Override
    public boolean addSong(Song song)
    {
        return songMapper.insertSelective(song) > 0;
    }

    @Override
    public boolean updateSongMsg(Song song) {
        return songMapper.updateSongMsg(song) > 0;
    }

    @Override
    public boolean updateSongUrl(Song song) {

        return songMapper.updateSongUrl(song) > 0;
    }

    @Override
    public boolean updateSongPic(Song song) {

        return songMapper.updateSongPic(song) > 0;
    }

    @Override
    public boolean deleteSong(Integer id) {
        return songMapper.deleteSong(id) > 0;
    }

    @Override
    public List<Song> songOfSingerId(Integer singerId)

    {
        return songMapper.songOfSingerId(singerId);
    }

    @Override
    public List<Song> songOfId(Integer id)

    {
        return songMapper.songOfId(id);
    }

    @Override
    public List<Song> songOfSingerName(String name)

    {
        return songMapper.songOfSingerName(name);
    }

    @Override
    public List<Song> songOfName(String name)

    {
        return songMapper.songOfName(name);
    }

    @Override
    public void count(Integer userId, Integer songId) {
        Long cnt = 1L;
        SongCount param = new SongCount();
        param.setUid(userId);
        param.setSongId(songId);
        param.setCnt(1L);
        SongCount songCount = songCountMapper.select(param);
        if (songCount != null) {
            songCount.setCnt(songCount.getCnt() + 1L);
            cnt = songCount.getCnt();
            songCountMapper.update(songCount);
        } else {
            songCountMapper.insert(param);
        }

        // update recommend list
        Recommend param1 = new Recommend();
        param1.setUid(userId);
        param1.setIid(songId);
        param1.setVal(0.001);
        Recommend recommend = recommendMapper.select(param1);
        if (recommend != null) {
            recommend.setVal(cnt * 0.001);
            recommendMapper.update(recommend);
        } else {
            recommendMapper.insert(param1);
        }

    }

    @Override
    public List<MusicDetail> songOfList(String id) {
        List<ListSong> listSongs = listSongMapper.listSongOfSongId(Integer.parseInt(id));
        List<Song> songs = new ArrayList<>();
        for (ListSong listSong: listSongs) {
            List<Song> song = songMapper.songOfId(listSong.getSongId());
            songs.add(song.get(0));
        }

        return getMusicDetail(songs);
    }

    private List<MusicDetail> getMusicDetail(List<Song> songs) {
        List<MusicDetail> res = new ArrayList<>();
        for(Song song: songs) {
            MusicDetail musicDetail = new MusicDetail();
            musicDetail.setId(song.getId());
            musicDetail.setName(song.getName());
            musicDetail.setImgUrl("https://music.zlza.icu:8443" + song.getPic());
            musicDetail.setMusicUrl("https://music.zlza.icu:8443" + song.getUrl());
//            String[] split = song.getUrl().split("/");
//            musicDetail.setMusicUrl("https://music.zlza.icu:8443" + split[split.length - 1]);
            musicDetail.setArtist(singerMapper.selectByPrimaryKey(song.getSingerId()).getName());
            res.add(musicDetail);
        }
        return res;
    }
}
