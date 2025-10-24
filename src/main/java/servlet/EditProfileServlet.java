package servlet;

import jakarta.servlet.ServletConfig;
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
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024
)
public class EditProfileServlet extends HttpServlet {
    private UserRepository userRepository;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userRepository = new UserRepositoryImpl();
        this.userService = new UserServiceImpl(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Edit Profile - Cinema System</title>");
        out.println("    <style>");
        out.println("        body {");
        out.println("            font-family: Arial, sans-serif;");
        out.println("            margin: 0;");
        out.println("            padding: 20px;");
        out.println("            background: #f5f5f5;");
        out.println("        }");
        out.println("        .container {");
        out.println("            max-width: 600px;");
        out.println("            margin: 0 auto;");
        out.println("            background: white;");
        out.println("            padding: 30px;");
        out.println("            border-radius: 8px;");
        out.println("            box-shadow: 0 2px 10px rgba(0,0,0,0.1);");
        out.println("        }");
        out.println("        h1 {");
        out.println("            text-align: center;");
        out.println("            color: #333;");
        out.println("            margin-bottom: 30px;");
        out.println("        }");
        out.println("        .profile-header {");
        out.println("            text-align: center;");
        out.println("            margin-bottom: 30px;");
        out.println("        }");
        out.println("        .profile-picture {");
        out.println("            width: 150px;");
        out.println("            height: 150px;");
        out.println("            border-radius: 50%;");
        out.println("            object-fit: cover;");
        out.println("            border: 3px solid #007bff;");
        out.println("            margin-bottom: 15px;");
        out.println("        }");
        out.println("        .default-avatar {");
        out.println("            width: 150px;");
        out.println("            height: 150px;");
        out.println("            border-radius: 50%;");
        out.println("            background: #ddd;");
        out.println("            display: flex;");
        out.println("            align-items: center;");
        out.println("            justify-content: center;");
        out.println("            margin: 0 auto 15px;");
        out.println("            border: 3px solid #ccc;");
        out.println("            font-size: 14px;");
        out.println("            color: #666;");
        out.println("        }");
        out.println("        .form-group {");
        out.println("            margin-bottom: 20px;");
        out.println("        }");
        out.println("        label {");
        out.println("            display: block;");
        out.println("            margin-bottom: 5px;");
        out.println("            color: #555;");
        out.println("            font-weight: bold;");
        out.println("        }");
        out.println("        input[type='text'],");
        out.println("        input[type='email'],");
        out.println("        input[type='password'],");
        out.println("        input[type='file'] {");
        out.println("            width: 100%;");
        out.println("            padding: 10px;");
        out.println("            border: 1px solid #ddd;");
        out.println("            border-radius: 4px;");
        out.println("            box-sizing: border-box;");
        out.println("        }");
        out.println("        input[type='file'] {");
        out.println("            padding: 8px;");
        out.println("        }");
        out.println("        .password-section {");
        out.println("            background: #f8f9fa;");
        out.println("            padding: 20px;");
        out.println("            border-radius: 6px;");
        out.println("            border-left: 4px solid #007bff;");
        out.println("            margin: 25px 0;");
        out.println("        }");
        out.println("        .password-section h3 {");
        out.println("            margin-top: 0;");
        out.println("            color: #333;");
        out.println("            border-bottom: 1px solid #dee2e6;");
        out.println("            padding-bottom: 10px;");
        out.println("        }");
        out.println("        .form-note {");
        out.println("            font-size: 12px;");
        out.println("            color: #6c757d;");
        out.println("            margin-top: 5px;");
        out.println("            font-style: italic;");
        out.println("        }");
        out.println("        .btn {");
        out.println("            padding: 12px 24px;");
        out.println("            border: none;");
        out.println("            border-radius: 4px;");
        out.println("            cursor: pointer;");
        out.println("            font-size: 16px;");
        out.println("            text-decoration: none;");
        out.println("            display: inline-block;");
        out.println("            text-align: center;");
        out.println("            margin-right: 10px;");
        out.println("        }");
        out.println("        .btn-primary {");
        out.println("            background: #007bff;");
        out.println("            color: white;");
        out.println("        }");
        out.println("        .btn-primary:hover {");
        out.println("            background: #0056b3;");
        out.println("        }");
        out.println("        .btn-secondary {");
        out.println("            background: #6c757d;");
        out.println("            color: white;");
        out.println("        }");
        out.println("        .btn-secondary:hover {");
        out.println("            background: #545b62;");
        out.println("        }");
        out.println("        .btn-success {");
        out.println("            background: #28a745;");
        out.println("            color: white;");
        out.println("        }");
        out.println("        .btn-success:hover {");
        out.println("            background: #1e7e34;");
        out.println("        }");
        out.println("        .form-actions {");
        out.println("            text-align: center;");
        out.println("            margin-top: 30px;");
        out.println("            border-top: 1px solid #eee;");
        out.println("            padding-top: 20px;");
        out.println("        }");
        out.println("        .alert {");
        out.println("            padding: 12px;");
        out.println("            border-radius: 4px;");
        out.println("            margin-bottom: 20px;");
        out.println("        }");
        out.println("        .alert-success {");
        out.println("            background: #d4edda;");
        out.println("            color: #155724;");
        out.println("            border: 1px solid #c3e6cb;");
        out.println("        }");
        out.println("        .alert-error {");
        out.println("            background: #f8d7da;");
        out.println("            color: #721c24;");
        out.println("            border: 1px solid #f5c6cb;");
        out.println("        }");
        out.println("        .current-info {");
        out.println("            background: #e9ecef;");
        out.println("            padding: 8px 12px;");
        out.println("            border-radius: 4px;");
        out.println("            margin-top: 5px;");
        out.println("            font-size: 14px;");
        out.println("        }");
        out.println("        .file-input-wrapper {");
        out.println("            position: relative;");
        out.println("            overflow: hidden;");
        out.println("            display: inline-block;");
        out.println("            width: 100%;");
        out.println("        }");
        out.println("        .file-input-label {");
        out.println("            display: block;");
        out.println("            padding: 10px;");
        out.println("            background: #f8f9fa;");
        out.println("            border: 1px dashed #dee2e6;");
        out.println("            border-radius: 4px;");
        out.println("            text-align: center;");
        out.println("            cursor: pointer;");
        out.println("            color: #6c757d;");
        out.println("        }");
        out.println("        .file-input-label:hover {");
        out.println("            background: #e9ecef;");
        out.println("        }");
        out.println("        .nav-links {");
        out.println("            text-align: center;");
        out.println("            margin-top: 20px;");
        out.println("            padding-top: 20px;");
        out.println("            border-top: 1px solid #eee;");
        out.println("        }");
        out.println("        .nav-links a {");
        out.println("            color: #007bff;");
        out.println("            text-decoration: none;");
        out.println("            margin: 0 10px;");
        out.println("        }");
        out.println("        .nav-links a:hover {");
        out.println("            text-decoration: underline;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <div class='profile-header'>");
        out.println("            <h1>Edit Profile</h1>");


        if (user.getProfilePictureBase64() != null) {
            out.println("            <img src='data:image/jpeg;base64," + user.getProfilePictureBase64() + "' " +
                    "alt='Profile Picture' class='profile-picture'>");
//            <img src="data:image/jpeg;base64,${movie.posterImageBase64}" alt="Movie Poster">
            //<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAA..." alt="Test Image">
        } else {
            out.println("            <div class='default-avatar'>");
            out.println("                No Picture Uploaded");
            out.println("            </div>");
        }
        out.println("        </div>");


        out.println("        <form action='" + request.getContextPath() + "/edit-profile' method='post' enctype='multipart/form-data'>");
        out.println("            ");
        out.println("            <div class='form-group'>");
        out.println("                <label for='username'>Username</label>");
        out.println("                <input type='text' id='username' name='username' " +
                "value='" + user.getUsername() + "' required>");
        out.println("            </div>");

        out.println("            <div class='form-group'>");
        out.println("                <label for='email'>Email</label>");
        out.println("                <input type='email' id='email' name='email' " +
                "value='" + user.getEmail() + "' required>");
        out.println("            </div>");


        out.println("            <div class='form-group'>");
        out.println("                <label>Change Profile Picture</label>");
        out.println("                <div class='file-input-wrapper'>");
        out.println("                    <div class='file-input-label' id='fileLabel'>");
        out.println("                        Choose file: No file chosen");
        out.println("                    </div>");
        out.println("                    <input type='file' id='profilePicture' name='pictureUrl' " +
                "accept='image/*' onchange='updateFileName(this)'>");
        out.println("                </div>");
        out.println("            </div>");


        out.println("            <div class='password-section'>");
        out.println("                <h3>Change Password (optional)</h3>");
        out.println("                ");
        out.println("                <div class='form-group'>");
        out.println("                    <label for='currentPassword'>Current Password</label>");
        out.println("                    <input type='password' id='currentPassword' name='currentPassword'>");
        out.println("                </div>");

        out.println("                <div class='form-group'>");
        out.println("                    <label for='newPassword'>New Password</label>");
        out.println("                    <input type='password' id='newPassword' name='newPassword'>");
        out.println("                </div>");

        out.println("                <div class='form-group'>");
        out.println("                    <label for='confirmPassword'>Confirm New Password</label>");
        out.println("                    <input type='password' id='confirmPassword' name='confirmPassword'>");

        out.println("                </div>");
        out.println("            </div>");


        out.println("            <div class='form-actions'>");
        out.println("                <button type='submit' class='btn btn-success'>Save</button>");
        out.println("                <a href='" + request.getContextPath() + "/user' class='btn btn-secondary'>Back</a>");
        out.println("            </div>");
        out.println("        </form>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession(false);

        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

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

        if (!Arrays.equals(profilePicture, user.getProfileUrl())) {
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
