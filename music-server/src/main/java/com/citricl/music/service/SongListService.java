package com.citricl.music.service;

import com.citricl.music.domain.PlayListDetail;
import com.citricl.music.domain.SongList;

import java.util.List;

public interface SongListService {

    boolean addSongList (SongList songList);

    boolean updateSongListMsg(SongList songList);

    boolean updateSongListImg(SongList songList);

    boolean deleteSongList(Integer id);

    List<SongList> allSongList();

    List<SongList> likeTitle(String title);

    List<SongList> likeStyle(String style);

    List<SongList> songListOfTitle(String title);

    List<PlayListDetail> songListPage(String param);

}
