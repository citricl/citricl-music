package com.citricl.music.service;

import com.citricl.music.domain.MusicDetail;
import com.citricl.music.domain.Song;

import java.util.List;

public interface SongService {

    boolean addSong (Song song);

    boolean updateSongMsg(Song song);

    boolean updateSongUrl(Song song);

    boolean updateSongPic(Song song);

    boolean deleteSong(Integer id);

    List<Song> allSong();

    List<Song> songOfSingerId(Integer singerId);

    List<Song> songOfId(Integer id);

    List<Song> songOfSingerName(String name);

    List<Song> songOfName(String name);

    void count(Integer userId, Integer songId);

    List<MusicDetail> songOfList(String id);
}
