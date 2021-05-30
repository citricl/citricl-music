package com.citricl.music.service.impl;

import com.citricl.music.dao.RankMapper;
import com.citricl.music.domain.Rank;
import com.citricl.music.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;

    @Override
    public int rankOfSongListId(Long songListId) {
        int rankNum = rankMapper.selectRankNum(songListId);
        int score = rankMapper.selectScoreSum(songListId);
        return rankNum == 0 ? 0 : score / rankNum;
    }

    @Override
    public boolean addRank(Rank rank) {
        return rankMapper.insertSelective(rank) > 0;
    }
}
