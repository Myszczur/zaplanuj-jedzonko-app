package pl.coderslab;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "/app/*")
public class Filter implements javax.servlet.Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;

        HttpSession httpSession = httpServletRequest.getSession();

        if (httpSession.getAttribute("adminId") == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
            return;
        }
        chain.doFilter(req, resp);
    }


    public void destroy() {
    }
    public void init(FilterConfig config) throws ServletException {
    }

}