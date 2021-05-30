package com.citricl.music.service.impl;

import com.citricl.music.dao.SongListMapper;
import com.citricl.music.domain.PlayListDetail;
import com.citricl.music.domain.SongList;
import com.citricl.music.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    @Override
    public boolean updateSongListMsg(SongList songList) {
        return songListMapper.updateSongListMsg(songList) > 0;
    }

    @Override
    public boolean deleteSongList(Integer id) {
        return songListMapper.deleteSongList(id) > 0;
    }

    @Override
    public List<SongList> allSongList()
    {
        return songListMapper.allSongList();
    }

    @Override
    public List<SongList> likeTitle(String title)
    {
        return songListMapper.likeTitle(title);
    }

    @Override
    public List<SongList> likeStyle(String style)
    {
        return songListMapper.likeStyle(style);
    }

    @Override
    public List<SongList> songListOfTitle(String title)
    {
        return songListMapper.songListOfTitle(title);
    }

    @Override
    public boolean addSongList(SongList songList)
    {
        return songListMapper.insertSelective(songList) > 0;
    }

    @Override
    public boolean updateSongListImg(SongList songList) {

        return songListMapper.updateSongListImg(songList) > 0;
    }

    @Override
    public List<PlayListDetail> songListPage(String param) {
        int offset = Integer.parseInt(param);
        List<SongList> songLists = songListMapper.listSongOfPage(offset + 1);
        return toDetail(songLists);
    }

    private List<PlayListDetail> toDetail(List<SongList> songLists) {
        List<PlayListDetail> res = new ArrayList<>();
        for (SongList songList : songLists) {
            PlayListDetail detail = new PlayListDetail();
            detail.setId(songList.getId());
            detail.setTitle(songList.getTitle());
            detail.setImgUrl("https://music.zlza.icu:8443" + songList.getPic());
            detail.setDesc(songList.getIntroduction());
            res.add(detail);
        }
        return res;
    }
}
