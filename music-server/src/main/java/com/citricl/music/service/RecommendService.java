package com.citricl.music.service;

import com.citricl.music.domain.ListSong;
import com.citricl.music.domain.SongList;

import java.text.ParseException;
import java.util.List;

public interface RecommendService {
    public List<ListSong> getRecommendList(int userId) throws ParseException;
}
