package com.yojulab.toy_servlets.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.yojulab.toy_servlets.dao.PollWithDB;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/polls/PollServlet")
public class DetailServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
  
        String example_Uid = request.getParameter("EXAMPLE_UID");
        
        //DB 객체 생성 및 LIST 받기
        PollWithDB pollWithDB = new PollWithDB();
        HashMap<String, Object> bundle_list = null;
        ArrayList<String> answers = null;

        try {
            bundle_list = pollWithDB.getExample(example_Uid);
            HashMap<String, Object> example = (HashMap<String, Object>) bundle_list.get("EXAMPLE");
            System.out.println(example.get("ORDERS") + ". " + example.get("EXAMPLE"));
            
            answers = (ArrayList<String>) bundle_list.get("ANSWERS");
            for (int i = 0; i < answers.size(); i++) {
                System.out.println(answers.get(i));
            }
            request.setAttribute("example", example);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("answers", answers);
        request.setAttribute("EXAMPLE_UID", example_Uid);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/polls/details.jsp");
        requestDispatcher.forward(request, response);


    }
}