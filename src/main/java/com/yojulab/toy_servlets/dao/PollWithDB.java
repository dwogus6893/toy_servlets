package com.yojulab.toy_servlets.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class PollWithDB {
    public HashMap<String, Object> getList(String example_Uid) throws SQLException {
        Commons commons = new Commons(); 
        Statement statement = commons.getStatement();
        String query = "SELECT * FROM EXAMPLE_LIST" +
                    "WHERE EXAMPLE_UID = '" + example_Uid +"'";

        ResultSet resultSet_Example = statement.executeQuery(query);
        HashMap<String,Object> bundle_list = new HashMap<>();
        HashMap<String,Object> example = new HashMap<>();
        ArrayList<String> questions = new ArrayList<>();

        //답항 넣기
        while(resultSet_Example.next()){
            example.put("EXAMPLE",resultSet_Example.getString("EXAMPLE"));
            example.put("ORDERS" ,resultSet_Example.getInt("ORDERS"));
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
        HashMap<String, Object> result2 = null;
        while(resultSet_Question.next()){
            result2 = new HashMap<>();
            result2.put("QUESTIONS_UID",resultSet_Question.getString("QUESTIONS_UID"));
            result2.put("QUESTIONS", resultSet_Question.getString("QUESTIONS"));
            result2.put("ORDERS",resultSet_Question.getInt("ORDERS"));
           }
        

        bundle_list.put("EXAMPLE",example);
        bundle_list.put("ANSWERS",questions);

        public HashMap<String, Object> getQuestion(String questionsUid) throws SQLException{
        
            Commons commons = new Commons();
            Statement statement = commons.getStatement();

            String query = "";

            ResultSet resultSet = statement.executeQuery(query);
            HashMap<String, Object> result = null;
            while(resultSet.next()) {
                result = new HashMap<>();
            }
        }






    }
}
