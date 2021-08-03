package controllers.products;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Product;

/**
 * Servlet implementation class ReportsNewServlet
 */
@WebServlet("/products/new")
public class ProductsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsNewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());
        Product r = new Product();
        r.setDate(new Date(System.currentTimeMillis()));
        request.setAttribute("product", r);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/products/new.jsp");
        rd.forward(request, response);
    }
}