package com.ants.library.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ants.library.exception.AntsException;

import java.io.IOException;

/**
 * Created by betterfly
 * Date : 2019.09.15
 */
public final class JsonUtils {
    private static final ObjectMapper OBJECT_MAPPER;

    private JsonUtils() {
        throw new IllegalStateException("JsonUtils is static final class");
    }

    static {
        OBJECT_MAPPER = new ObjectMapper();
        // int나 double 타입에서 값이 null일 경우 0으로 바꿔준다.
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        // enum을 String으로 변환한다. enum.toString()호출
        OBJECT_MAPPER.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        // 모르는 properties가 있을 때 오류를 반환하지 않는다.
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 직렬화 어노테이션이 없어도 직렬화 시킨다.
        OBJECT_MAPPER.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        // 필드명이 더블쿼테이션이 아니더라도(싱글이더라도) 인정함.
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        OBJECT_MAPPER.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        // ENUM 값이 존재하지 않으면 null로 설정한다. Enum 항목이 추가되어도 무시하고 넘어가게 할 때 필요하다.
        OBJECT_MAPPER.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

    }

    /**
     * 스트링 JSON데이터를 객체로 반환합니다
     * @param json
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            throw new AntsException(e.getMessage());
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (IOException e) {
            throw new AntsException(e.getMessage());
        }
    }

    /**
     * 객체 데이터를 스트링으로 변환홥니다.
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new AntsException(e.getMessage());
        }
    }

    public static String toPrettyJson(Object object) {
        OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        String json = toJson(object);
        OBJECT_MAPPER.disable(SerializationFeature.INDENT_OUTPUT);
        return json;
    }

    public static boolean verifyJson(String maybeJson) {
        if (maybeJson == null)
            return false;

        return (maybeJson.startsWith("[") && maybeJson.endsWith("]"))
                || (maybeJson.startsWith("{") && maybeJson.endsWith("}"));
    }
}