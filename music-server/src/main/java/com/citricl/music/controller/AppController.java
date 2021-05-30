package com.citricl.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.citricl.music.domain.ListSong;
import com.citricl.music.domain.MusicDetail;
import com.citricl.music.domain.PlayListDetail;
import com.citricl.music.service.SongListService;
import com.citricl.music.service.impl.ListSongServiceImpl;
import com.citricl.music.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Controller
public class AppController {
    @Autowired
    private SongListService songListService;

    @Autowired
    private SongServiceImpl songService;

    @RequestMapping(value = "/playlist/details", method = RequestMethod.GET)
    public Object playListDetails(HttpServletRequest req){
        String id = req.getParameter("id").trim();
        JSONObject jsonObject = new JSONObject();

        List<MusicDetail> musicDetails = songService.songOfList(id);
        if (musicDetails.isEmpty()) {
            jsonObject.put("flag", false);
        }
        jsonObject.put("flag", true);
        jsonObject.put("list", musicDetails);
        return jsonObject;
    }

    @RequestMapping(value = "/playlist/get", method = RequestMethod.GET)
    public Object playList(HttpServletRequest req){
        String offset = req.getParameter("offset").trim();

        JSONObject jsonObject = new JSONObject();

        List<PlayListDetail> playListDetails = songListService.songListPage(offset);
        if (playListDetails.isEmpty()) {
            jsonObject.put("flag", false);
        }
        jsonObject.put("flag", true);
        jsonObject.put("list", playListDetails);
        return jsonObject;
    }
}
