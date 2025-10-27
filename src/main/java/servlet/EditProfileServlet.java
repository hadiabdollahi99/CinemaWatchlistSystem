package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import model.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;
import service.impl.UserServiceImpl;
import util.PasswordUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024
)
public class EditProfileServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        UserRepository userRepository = new UserRepositoryImpl();
        this.userService = new UserServiceImpl(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        request.setAttribute("user",user);
        request.getRequestDispatcher("edit-profile.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        Part usernamePart = req.getPart("username");
        Part emailPart = req.getPart("email");
        Part currentPasswordPart = req.getPart("currentPassword");
        Part newPasswordPart = req.getPart("newPassword");
        Part confirmPasswordPart = req.getPart("confirmPassword");
        Part profilePicturePart = req.getPart("pictureUrl");

        String username = new String(usernamePart.getInputStream().readAllBytes(), "UTF-8");
        String email = new String(emailPart.getInputStream().readAllBytes(), "UTF-8");
        String currentPassword = new String(currentPasswordPart.getInputStream().readAllBytes(), "UTF-8");
        String newPassword = new String(newPasswordPart.getInputStream().readAllBytes(), "UTF-8");
        String confirmPassword = new String(confirmPasswordPart.getInputStream().readAllBytes(), "UTF-8");

        byte[] profilePicture = null;
        if (profilePicturePart != null && profilePicturePart.getSize() > 0) {
            profilePicture = profilePicturePart.getInputStream().readAllBytes();
        }


        if (!username.isEmpty() && !username.equals(user.getUsername())) {
            user.setUsername(username);
        }
        if (!email.isEmpty() && !email.equals(user.getEmail())) {
            user.setEmail(email);
        }

        if (!Arrays.equals(profilePicture, user.getProfileUrl()) && profilePicture != null) {
            user.setProfileUrl(profilePicture);
        }

        if (!currentPassword.isEmpty() && !newPassword.isEmpty()) {

            if (!PasswordUtil.verifyPassword(currentPassword, user.getPassword())) {
                out.println("<h1 style='color: red'>Current password is incorrect!</h1>");
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                out.println("<h1 style='color: red'>New Password do not match!</h1>");
                return;
            }

            user.setPassword(PasswordUtil.hashPassword(newPassword));
        }

        userService.saveOrUpdate(user);

        session.setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + "/user");

    }
}
