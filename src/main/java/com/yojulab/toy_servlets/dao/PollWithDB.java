package com.yojulab.toy_servlets.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class PollWithDB {
    public HashMap<String, Object> getList(String questions_Uid) throws SQLException {
        Commons commons = new Commons();
        Statement statement = commons.getStatement();
        String query = "SELECT * FROM QUESTIONS_LIST"+
                "WHERE QUESTIONS_UID ='" + questions_Uid + "'";
        
        ResultSet resultSet_Question = statement.executeQuery(query);
        HashMap<String, Object> bundle_list = new HashMap<>();
        HashMap<String, Object> question = new HashMap<>();
        ArrayList<String> answers = new ArrayList<>();    
        
        //질문넣기
        while(resultSet_Question.next()) {
            question.put("QUESTION", resultSet_Question.getString("QUESTIONS"));
            question.put("ORDERS", resultSet_Question.getInt("ORDERS"));
        }

        //답항 만들기
        Statement statement2 = commons.getStatement();
        query = "SELECT ANSWERS.QUESTIONS_UID, EXAMPLE_LIST.ORDERS, EXAMPLE_LIST.EXAMPLE " +
                "FROM ANSWERS " +
                "INNER JOIN EXAMPLE_LIST " +
                "ON ANSWERS.EXAMPLE_UID = EXAMPLE_LIST.EXAMPLE_UID " +
                "WHERE QUESTIONS_UID = '" + questions_Uid + "' " +
                "ORDER BY QUESTIONS_UID; ";

        ResultSet resultSet_Answer = statement2.executeQuery(query);

        while (resultSet_Answer.next()) {
            answers.add(resultSet_Answer.getInt("ORDERS") + ". " + resultSet_Answer.getString("Example"));
        }
        bundle_list.put("QUESTION", question);
        bundle_list.put("ANSWERS", answers);

        return bundle_list;
    }
}
