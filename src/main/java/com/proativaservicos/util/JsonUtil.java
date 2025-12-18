package com.proativaservicos.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proativaservicos.model.bancoMaster.ConsultaLimitesPorCpfResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class JsonUtil {


    public static <T> T fromJson(String json, Class<T> clazz) {

        try {

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, clazz);

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }



    public static <T> List<T> fromJsonArray(String json, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return mapper.readValue(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean isJSON(String json) {

        try {

            new JSONObject(json);
            return true;

        } catch (Exception e) {
            return false;
        }
    }


    public static boolean isArrayJSON(String json) {

        try {

            new JSONArray(json);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

}
