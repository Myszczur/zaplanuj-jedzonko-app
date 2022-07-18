package pl.coderslab.web;

import pl.coderslab.dao.Plan;
import pl.coderslab.dao.PlanDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "appPlanAdd", value = "/app/plan/add")
public class AppPlanAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/addPlan.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Plan plan = new Plan();
        PlanDAO planDAO = new PlanDAO();

        HttpSession session = request.getSession();
        int adminId = Integer.parseInt(session.getAttribute("adminId").toString());

        plan.setAdminId(adminId);
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        plan.setName(request.getParameter("planName"));
        plan.setDescription(request.getParameter("planDescription"));
        plan.setCreated(timeStamp);
        planDAO.create(plan);
        response.sendRedirect("/app/plan/list");
    }
}


