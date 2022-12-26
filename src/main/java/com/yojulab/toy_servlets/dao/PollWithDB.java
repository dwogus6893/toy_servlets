package com.yojulab.toy_servlets.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class PollWithDB {
    public HashMap<String, Object> getExample(String example_Uid) throws SQLException {
       
        Commons commons = new Commons(); 
        Statement statement = commons.getStatement();
        String query = "SELECT * FROM EXAMPLE_LIST " +
        "WHERE EXAMPLE_UID = '" + example_Uid + "'";

        ResultSet resultSet = statement.executeQuery(query); //DB에 던짐;
        
        ResultSet resultSet_Example = statement.executeQuery(query);
        HashMap<String, Object> bundle_list = new HashMap<>();
        HashMap<String, Object> example = new HashMap<>();
        ArrayList<String> answers = new ArrayList<>();
    
        // 질문 넣기
        while (resultSet_Example.next()) {
        example.put("EXAMPLE", resultSet_Example.getString("EXAMPLE"));
        example.put("ORDERS", resultSet_Example.getInt("ORDERS"));
    }
        //답항 만들기
        Statement statement2 = commons.getStatement();
        
        query = "SELECT ANSWERS.EXAMPLE_UID, EXAMPLE_LIST.ORDERS, EXAMPLE_LIST.EXAMPLE " +
        "FROM ANSWERS " +
        "INNER JOIN EXAMPLE_LIST " +
        "ON ANSWERS.EXAMPLE_UID = EXAMPLE_LIST.EXAMPLE_UID " +
        "WHERE EXAMPLE_UID = '" + example_Uid + "' " +
        "ORDER BY EXAMPLE_UID; ";


        ResultSet resultSet_Answer = statement2.executeQuery(query);

        while (resultSet_Answer.next()) {
            answers.add(resultSet_Answer.getInt("ORDERS") + ". " + resultSet_Answer.getString("Example"));
        }
        bundle_list.put("EXAMPLE", example);
        bundle_list.put("ANSWERS", answers);

        return bundle_list;
        }
    }
