package servlet.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Role;
import model.User;

import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        if(session == null){
            resp.sendRedirect(req.getContextPath()+"/login");
            return;
        }
        User user = ((User) session.getAttribute("user"));
        if (user == null || !user.getRole().equals(Role.ADMIN)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN,"Access Denied");
            return;
        }
        chain.doFilter(request,response);
    }
}
