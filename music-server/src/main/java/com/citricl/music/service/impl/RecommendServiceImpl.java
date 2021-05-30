package com.citricl.music.service.impl;

import com.citricl.music.dao.RecommendListMapper;
import com.citricl.music.dao.SongMapper;
import com.citricl.music.domain.ListSong;
import com.citricl.music.domain.RecommendList;
import com.citricl.music.service.RecommendService;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class RecommendServiceImpl implements RecommendService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecommendServiceImpl.class);
    @Autowired
    private RecommendListMapper recommendListMapper;

    @Autowired
    private SongMapper songMapper;

    @Override
    public List<ListSong> getRecommendList(int userId) throws ParseException {
        // 已进行分析推荐的用户，直接从数据库中获取
        // 获取当日时间戳
        long today = getTodayTimeStamp();
        //如果数据库中存在当日推荐生成的歌单，则直接返回
        List<RecommendList> recommendLists = recommendListMapper.allRecommedList(userId, today);
        if (recommendLists != null && recommendLists.size() > 0 && recommendLists.get(0).getUpdateTime() >= today) {
            return toListSong(recommendLists);
        }

        // 分析推荐模块
        List<RecommendedItem> recommendedItemList = new ArrayList<>();
        try {
            // 初始化数据模型
            DataModel dataModel = initDataModel();
            // 设置相似度计算方式，此处选择余弦相似度算法进行计算，对应类型为UncenteredCosineSimilarity
            UserSimilarity similarity = new UncenteredCosineSimilarity(dataModel);
            // 设置领域划分算法，此处采用以阈值为标准的划分方式，相似度大于threshold时，认为是当前用户的邻居
            UserNeighborhood userNeighborhood = new ThresholdUserNeighborhood(0.6, similarity, dataModel);
            // 经过上一步，得到了相似的用户群体，即相似领域。此处根据领域中其他邻居的信息，进一步推测用户对歌曲的喜好程度
            Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, similarity);
            // 获取到推荐列表之后，从推荐列表中取10个进行推荐
            recommendedItemList = recommender.recommend(userId, 10);
            LOGGER.info("generic recommender finished, user {} recommend list is {}", userId, recommendedItemList);
        } catch (TasteException e) {
            LOGGER.error("get recommend list error", e);
        }
        // 将最终的推荐列表存储到数据库中，以便下一次获取
        return saveRecommend(recommendedItemList, userId, today);
    }

    private DataModel initDataModel() {
        MysqlDataSource dataSource = initDataSource();
        return new MySQLJDBCDataModel(dataSource, "recommend",
                "uid", "iid",
                "val", "time");
    }

    private MysqlDataSource initDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        try {
            dataSource.setServerTimezone("Asia/Shanghai");
            dataSource.setNullCatalogMeansCurrent(true);
            dataSource.setUseSSL(false);
        } catch (SQLException e) {
            LOGGER.error("data source set errror ", e);
        }
        // Database's config
        dataSource.setServerName("localhost");
        dataSource.setUser("user");
        dataSource.setPassword("pass");
        dataSource.setDatabaseName("music");
        return dataSource;
    }

    private List<ListSong> saveRecommend(List<RecommendedItem> recommendedItemList, int userId, long today) {
        List<RecommendList> recommendLists = new ArrayList<>();
        for (RecommendedItem recommendedItem : recommendedItemList) {
            long id = recommendedItem.getItemID();
            RecommendList recommendList = new RecommendList();
            recommendList.setUpdateTime(today);
            recommendList.setSongId((int) id);
            recommendList.setUserId(userId);
            recommendListMapper.insert(recommendList);
            recommendLists.add(recommendList);
        }

        if (recommendLists.size() < 10) {
            int size = songMapper.count();
            boolean[] bucket = new boolean[size];
            for (int i = 0; i < 10; ) {
                if (i < recommendLists.size()) {
                    bucket[recommendLists.get(i).getSongId()] = true;
                    i++;
                } else {
                    Random random = new Random();
                    int randomInt = random.nextInt(size);
                    if (!bucket[randomInt]) {
                        bucket[randomInt] = true;

                        RecommendList recommendList = new RecommendList();
                        recommendList.setUpdateTime(today);
                        recommendList.setSongId(randomInt);
                        recommendList.setUserId(userId);
                        recommendListMapper.insert(recommendList);
                        recommendLists.add(recommendList);
                        i++;
                    }
                }
            }
        }

        return toListSong(recommendLists);
    }

    private List<ListSong> toListSong(List<RecommendList> recommendLists) {
        List<ListSong> listSongs = new ArrayList<>();
        for (RecommendList recommendList : recommendLists) {
            ListSong listSong = new ListSong();
            listSong.setSongId(recommendList.getSongId());
            listSong.setSongListId(1);
            listSongs.add(listSong);
        }
        return listSongs;
    }

    private long getTodayTimeStamp() throws ParseException {
        // 当天日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(date.getTime());

        // 当天凌晨(毫秒)
        return simpleDateFormat.parse(today).getTime();
    }
}
