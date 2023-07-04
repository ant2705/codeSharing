package com.example.codesharing;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

public class OperateDB {
    public static boolean idNotExist(String ID){
        ApplicationContext context = new ClassPathXmlApplicationContext("myjdbc.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
        String sql = "Select shareid from idTable where shareid = \"" + ID + "\"";
        List<String> results = jdbcTemplate.query(sql, new MyStringRowMapper());
        if(results.size() == 0) return true;
        return false;
    }

    public static String getCodeByID(String ID){
        ApplicationContext context = new ClassPathXmlApplicationContext("myjdbc.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
        String sql = "Select codetext from idTable where shareid = \"" + ID + "\"";
        List<String> results = jdbcTemplate.query(sql, new MyStringRowMapper());
        if(results.size() != 1) return "Key is invalid (from mysql)";
        return results.get(0);
    }

    private static class MyStringRowMapper implements RowMapper<String> {
        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getString(1);
        }
    }

    public static boolean insertData(String ID, String codetext){
        LocalDateTime dateTime = LocalDateTime.now();
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dateTime);
        ApplicationContext context = new ClassPathXmlApplicationContext("myjdbc.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
        String sql = "INSERT INTO idTable (shareid, codetext, time) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(sql, ID, codetext, timestamp);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
