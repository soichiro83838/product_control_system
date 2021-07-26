package controllers.parts;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Part;

/**
 * Servlet implementation class ReportsNewServlet
 */
@WebServlet("/parts/new")
public class PartsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartsNewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        Part r = new Part();
        r.setDate(new Date(System.currentTimeMillis()));
        request.setAttribute("part", r);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/parts/new.jsp");
        rd.forward(request, response);
    }

}