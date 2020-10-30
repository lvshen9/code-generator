package com.generator.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 常量类
 *
 * @author Lvshen
 * @version 1.0
 * @date: 2020/7/4 13:46
 * @since JDK 1.8
 */
public class Constant {

    public static final String EXAMPLE_SUFFIX="Example";
    public static final String API_MODEL_PROPERTY_FULL_CLASS_NAME="io.swagger.annotations.ApiModelProperty";
    public static final String API_JSON_SERIALIZE="com.fasterxml.jackson.databind.annotation.JsonSerialize";
    public static final String API_DATE_TIME_FORMAT="org.springframework.format.annotation.DateTimeFormat";
    public static final String DATE_TYPE="Date";
    public static final String API_JSON_FORMAT ="com.fasterxml.jackson.annotation.JsonFormat";
    public static final String API_COLUMN="javax.persistence.Column";
    public static final String API_TABLE_NAME="javax.persistence.Table";

    public static Map<String, String> mapData = new HashMap<>();

    public static String date2Str(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }


}
