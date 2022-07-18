package pl.coderslab.web;

import pl.coderslab.dao.Plan;
import pl.coderslab.dao.PlanDAO;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PlanList", value = "/app/plan/list")
public class PlanList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int adminId = Integer.parseInt(session.getAttribute("adminId").toString());

        PlanDAO planDAO = new PlanDAO();
        request.setAttribute("planDAO", planDAO.findAllbyAdmin(adminId));
        List<Plan> plans = planDAO.findAllbyAdmin(adminId);
        request.setAttribute("planDAO", plans);
        getServletContext().getRequestDispatcher("/planList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        }
}