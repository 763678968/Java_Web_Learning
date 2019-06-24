package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ListenerTester extends HttpServlet {

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("test session attributes<br>");

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1200);

        if (session==null) {
            out.println("no session was available");
            out.println("making one...");
            session = request.getSession();
        } else {
            out.println("there was a session!");
        }

    }
}
