package com.lakshay.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lakshay.dao.QuestionDAO;
import com.lakshay.dao.ResultDAO;
import com.lakshay.dto.Question;
import com.lakshay.dto.Result;
import com.lakshay.dto.Student;

@WebServlet("/exam")
public class ExamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Display Exam Page
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("student") == null) {
            response.sendRedirect("login.html");
            return;
        }

        QuestionDAO qdao = new QuestionDAO();
        List<Question> questions = qdao.getAllQuestions();

        request.setAttribute("questions", questions);
        request.getRequestDispatcher("exam.jsp").forward(request, response);
    }

    // Submit Exam
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Student s = (Student) session.getAttribute("student");

        QuestionDAO qdao = new QuestionDAO();
        List<Question> questions = qdao.getAllQuestions();

        int score = 0;

        for (Question q : questions) {
            String answer = request.getParameter("q" + q.getqId());
            if (answer != null && Integer.parseInt(answer) == q.getCorrectOption()) {
                score++;
            }
        }

        Result r = new Result();
        r.setStudentId(s.getStudentId());
        r.setScore(score);
        r.setTotal(questions.size());

        ResultDAO rdao = new ResultDAO();
        rdao.saveResult(r);

        request.setAttribute("score", score);
        request.setAttribute("total", questions.size());

        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
