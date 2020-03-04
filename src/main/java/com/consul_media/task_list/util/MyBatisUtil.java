package com.consul_media.task_list.util;

import org.apache.ibatis.jdbc.SQL;

public class MyBatisUtil {
    public String getStarsWithAstronomerName(){
        return new SQL() {{
            SELECT("s.id, s.astronomer_id, s.star_name, s.longitude, s.latitude, s.color, a.astronomer_name");
            FROM("stars s");
            INNER_JOIN("astronomers a");
            WHERE("s.astronomer_id = a.id");
        }}.toString();
    }

    public String getStarById(){
        return new SQL() {{
            SELECT("s.id, s.astronomer_id, s.star_name, s.longitude, s.latitude, s.color, a.astronomer_name");
            FROM("stars s");
            LEFT_OUTER_JOIN("astronomers a on s.astronomer_id = a.id");
            WHERE("s.id = #{id}");
        }}.toString();
    }

    public String getAstronomers(){
        return new SQL(){{
            SELECT("*");
            FROM("astronomers");
        }}.toString();
    }
}
