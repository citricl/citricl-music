package com.citricl.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.citricl.music.domain.ListSong;
import com.citricl.music.service.impl.ListSongServiceImpl;
import com.citricl.music.service.impl.RecommendServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;

@RestController
@Controller
public class ListSongController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ListSongController.class);

    @Autowired
    private ListSongServiceImpl listSongService;

    @Autowired
    private RecommendServiceImpl recommendService;

    //    给歌单添加歌曲
    @ResponseBody
    @RequestMapping(value = "/ListSong/add", method = RequestMethod.POST)
    public Object addListSong(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String songId = req.getParameter("songId").trim();
        String songListId = req.getParameter("songListId").trim();

        ListSong listsong = new ListSong();
        listsong.setSongId(Integer.parseInt(songId));
        listsong.setSongListId(Integer.parseInt(songListId));

        boolean res = listSongService.addListSong(listsong);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "添加成功");
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "添加失败");
        }
        return jsonObject;
    }

    //    返回歌单里包含的所有歌曲
    @RequestMapping(value = "/listSong", method = RequestMethod.GET)
    public Object allListSong() {
        return listSongService.allListSong();
    }

    //    返回歌单里指定歌单ID的歌曲
    @RequestMapping(value = "/listSong/detail", method = RequestMethod.GET)
    public Object listSongOfSongId(HttpServletRequest req) {
        String songListId = req.getParameter("songListId");
        int id = Integer.parseInt(songListId);
        if (id == 1) {
            String userId = req.getParameter("userId");
            try {
                if (userId == null) {
                    return null;
                } else {
                    return recommendService.getRecommendList(Integer.parseInt(userId));
                }
            } catch (ParseException e) {
                LOGGER.error("parse exception", e);
                return new ArrayList<ListSong>();
            }
        } else {
            return listSongService.listSongOfSongId(id);
        }
    }

    //    删除歌单里的歌曲
    @RequestMapping(value = "/ListSong/delete", method = RequestMethod.GET)
    public Object deleteListSong(HttpServletRequest req) {
        String songId = req.getParameter("songId");
        return listSongService.deleteListSong(Integer.parseInt(songId));
    }

    //    更新歌单里面的歌曲信息
    @ResponseBody
    @RequestMapping(value = "/listSong/update", method = RequestMethod.POST)
    public Object updateListSongMsg(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String songId = req.getParameter("songId").trim();
        String songListId = req.getParameter("songListId").trim();

        ListSong listsong = new ListSong();
        listsong.setId(Integer.parseInt(id));
        listsong.setSongId(Integer.parseInt(songId));
        listsong.setSongListId(Integer.parseInt(songListId));

        boolean res = listSongService.updateListSongMsg(listsong);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "修改成功");
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "修改失败");
        }
        return jsonObject;
    }
}
