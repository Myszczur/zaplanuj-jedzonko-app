package pl.coderslab.web;

import pl.coderslab.dao.AdminDAO;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/login.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AdminDAO dao = new AdminDAO();
        if (dao.checkEmailVsPassword(email, password)) {
            Admin admin = dao.readAdminByEmail(email);
            HttpSession session = request.getSession();
            session.setAttribute("login", email);
            session.setAttribute("adminId", admin.getId());
            response.sendRedirect("/app/dashboard");

        }
        if (!dao.checkEmailVsPassword(email, password)) {
            String msg = "Nie poprawny email lub haslo!";
            request.setAttribute("msgText", msg);
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);


        }


    }
}
