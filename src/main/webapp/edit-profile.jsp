<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: abdol
  Date: 10/26/2025
  Time: 7:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Edit Profile</title>
    <link rel="stylesheet" href="css/edit-profile.css">
</head>
<body>
<div class='container'>
    <div class='profile-header'>
        <h1>Edit Profile</h1>
        <%
            User user = (User) request.getAttribute("user");
            if (user.getProfilePictureBase64() != null) {
        %>
        <img src='data:image/jpeg;base64,<%=user.getProfilePictureBase64()%>' alt='Profile Picture'
             class='profile-picture'>
        <% }
            else { %>
        <div class='default-avatar'>
            No Picture Uploaded
        </div>
        <% } %>
    </div>


    <form action='<%= request.getContextPath() + "/edit-profile"%>' method='post' enctype='multipart/form-data'>

        <div class='form-group'>
            <label for='username'>Username</label>
            <input type='text' id='username' name='username' value='<%=user.getUsername()%>' required>
        </div>

        <div class='form-group'>
            <label for='email'>Email</label>
            <input type='email' id='email' name='email' value='<%=user.getEmail()%>' required>
        </div>


        <div class='form-group'>
            <label>Change Profile Picture</label>
            <div class='file-input-wrapper'>
                <input type='file' id='profilePicture' name='pictureUrl' accept='image/*'>
            </div>
        </div>


        <div class='password-section'>
            <h3>Change Password (optional)</h3>

            <div class='form-group'>
                <label for='currentPassword'>Current Password</label>
                <input type='password' id='currentPassword' name='currentPassword'>
            </div>

            <div class='form-group'>
                <label for='newPassword'>New Password</label>
                <input type='password' id='newPassword' name='newPassword'>
            </div>

            <div class='form-group'>
                <label for='confirmPassword'>Confirm New Password</label>
                <input type='password' id='confirmPassword' name='confirmPassword'>

            </div>
        </div>


        <div class='form-actions'>
            <button type='submit' class='btn btn-success'>Save</button>
            <a href='<%=request.getContextPath() + "/user"%>' class='btn btn-secondary'>Back</a>
        </div>
    </form>
</div>


</body>
</html>
