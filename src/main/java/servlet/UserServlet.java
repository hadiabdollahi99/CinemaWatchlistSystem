package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;
import service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserServlet extends HttpServlet {
    private UserRepository userRepository;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userRepository = new UserRepositoryImpl();
        this.userService = new UserServiceImpl(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession(false);

        User user = (User) session.getAttribute("user");

        writer.println("<h1><b>Welcome User " + user.getUsername() + ".</b></h1>");
        writer.println("<a href='edit-profile'>Edit Profile</a><br>");
        writer.println("<a href='movie-user'>All Movie User</a><br>");
        writer.println("<a href='watchlist'>Watchlist</a><br>");
        writer.println("<a href='logout'>Logout</a>");
    }

}
