<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Password</title>
<%@include file="component/allcss.jsp"%>
<style type="text/css">
    .paint-card {
        box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
    }
    .error-message {
        color: red;
        font-size: 0.9em;
    }
</style>
<script>
    function validatePasswordForm() {
        const newPassword = document.getElementById("newPassword").value.trim();
        const confirmPassword = document.getElementById("confirmPassword").value.trim();
        const oldPassword = document.getElementById("oldPassword").value.trim();
        const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;

        // Reset error messages
        document.getElementById("newPasswordError").textContent = "";
        document.getElementById("confirmPasswordError").textContent = "";
        document.getElementById("oldPasswordError").textContent = "";

        let isValid = true;

        // Validate Old Password
        if (oldPassword === "") {
            document.getElementById("oldPasswordError").textContent = "Old password is required.";
            isValid = false;
        }

        // Validate New Password
        if (!newPassword.match(passwordPattern)) {
            document.getElementById("newPasswordError").textContent =
                "Password must be at least 8 characters long and include at least one letter, one number, and one special character.";
            isValid = false;
        }

        // Validate Confirm Password
        if (newPassword !== confirmPassword) {
            document.getElementById("confirmPasswordError").textContent = "Passwords do not match.";
            isValid = false;
        }

        return isValid;
    }
</script>
</head>
<body>
    <%@include file="component/navbar.jsp"%>

    <c:if test="${ empty userObj }">
        <c:redirect url="user_login.jsp"></c:redirect>
    </c:if>

    <div class="container p-4">
        <div class="row">
            <div class="col-md-4 offset-md-4">
                <div class="card paint-card">
                    <p class="text-center fs-3">Change Password</p>
                    <c:if test="${not empty succMsg }">
                        <p class="text-center text-success fs-3">${succMsg}</p>
                        <c:remove var="succMsg" scope="session" />
                    </c:if>

                    <c:if test="${not empty errorMsg }">
                        <p class="text-center text-danger fs-5">${errorMsg}</p>
                        <c:remove var="errorMsg" scope="session" />
                    </c:if>
                    <div class="card-body">
                        <form action="userChangePassword" method="post" onsubmit="return validatePasswordForm();">
                            <div class="mb-3">
                                <label>Enter New Password</label>
                                <input type="password" id="newPassword" name="newPassword" class="form-control" required>
                                <span id="newPasswordError" class="error-message"></span>
                            </div>
                            <div class="mb-3">
                                <label>Confirm New Password</label>
                                <input type="password" id="confirmPassword" class="form-control" required>
                                <span id="confirmPasswordError" class="error-message"></span>
                            </div>
                            <div class="mb-3">
                                <label>Enter Old Password</label>
                                <input type="password" id="oldPassword" name="oldPassword" class="form-control" required>
                                <span id="oldPasswordError" class="error-message"></span>
                            </div>
                            <input type="hidden" value="${userObj.id}" name="uid">
                            <button class="btn btn-success col-md-12">Change Password</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
