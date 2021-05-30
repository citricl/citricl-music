package com.citricl.music.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Gson gson = new Gson();

    public static String toString(Object o) {
        String json = gson.toJson(o);
        return json;
    }

    public static Map<String, Object> toMap(String str) {
        Map<String, Object> res = null;
        try {
            res = gson.fromJson(str, new TypeToken<Map<String, Object>>(){}.getType());
        } catch (JsonSyntaxException e) {
            LOGGER.error("fail to convert str to map, str = {}", str, e);
        }
        return res;
    }

    public static List<Object> toList(String str) {
        List<Object> res = null;
        try {
            res = gson.fromJson(str, new TypeToken<List<Object>>(){}.getType());
        } catch (JsonSyntaxException e) {
            LOGGER.error("fail to convert str to list, str = {}", str, e);
        }
        return res;
    }

    public static List<Map<String, Object>> toListMap(String str) {
        List<Map<String, Object>> res = null;
        try {
            res = gson.fromJson(str, new TypeToken<List<Map<String, Object>>>(){}.getType());
        } catch (JsonSyntaxException e) {
            LOGGER.error("fail to convert str to map list, str = {}", str, e);
        }
        return res;
    }

    public static String toJsonString(Object req) {
        String res;
        try {
            res = objectMapper.writeValueAsString(req);
        } catch (JsonProcessingException e) {
            return String.format("toJson err %s", e.getMessage());
        }
        return res;
    }
}
