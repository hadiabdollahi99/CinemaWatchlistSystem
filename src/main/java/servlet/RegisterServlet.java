package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Role;
import model.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;
import service.impl.UserServiceImpl;
import util.PasswordUtil;

import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {

    private UserRepository userRepository;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/register.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        password = PasswordUtil.hashPassword(password);
        userService.saveOrUpdate(User.builder().username(username).email(email).password(password).role(Role.USER).build());
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}