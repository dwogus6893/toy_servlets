package com.yojulab.toy_servlets.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class PollWithDB {
    public HashMap<String, Object> getList(String example_Uid) throws SQLException {
        Commons commons = new Commons(); //DB연결을 위해 선언
        Statement statement = commons.getStatement();// sql쿼리 쏴야되서 선언
        String query = "SELECT * FROM EXAMPLE_LIST" +
                    "WHERE EXAMPLE_UID = '" + example_Uid +"'";

        ResultSet resultSet_Example = statement.executeQuery(query);
        HashMap<String,Object> bundle_list = new HashMap<>();
        HashMap<String,Object> example = new HashMap<>();
        ArrayList<String> questions = new ArrayList<>();

        //답항 넣기
        While(resultSet_Example.next()){
            example.put("EXAMPLE",resultSet_Example.getString("EXAMPLE"));
            example.put("ORDERS" resultSet_Example.getInt("ORDERS"));
        }

        //질문 만들기
        Statement statement2 = commons.getStatement();
        query = "SELECT answers.QUESTIONS_UID, questions_list.ORDERS,questions_list.QUESTIONS"+
                "FROM answers"+
                "INNER JOIN questions_list"+
                "ON answers.QUESTIONS_UID = questions_list.QUESTIONS_UID"+
                "WHERE QUESTIONS_UID='" +example_Uid+"'"+
                "ORDER BY QUESTIONS_UID;";

        ResultSet resultSet_Question = statement2.executeQuery(query);

        while(resultSet_Question.next()){
            answer.add(resultSet_Question.getInt("ORDERS")+ "." + resultSet_Example.getString(""));
        }

        bundle_list.put("EXAMPLE",example);
        bundle_list.put("ANSWERS",answers);

        






    }
}
