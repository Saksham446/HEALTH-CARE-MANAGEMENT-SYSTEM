<%@page import="com.db.DBConnect"%>
<%@page import="com.dao.DoctorDao"%>
<%@page import="com.entity.Doctor"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User Appointment</title>
    <%@include file="component/allcss.jsp"%>
    <style type="text/css">
        .paint-card {
            box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.3);
        }

        .backImg {
            background: linear-gradient(rgba(0, 0, 0, .4), rgba(0, 0, 0, .4)),
                        url("img/hospital.jpg");
            height: 20vh;
            width: 100%;
            background-size: cover;
            background-repeat: no-repeat;
        }
        .error-message {
            color: red;
            font-size: 0.9em;
        }
    </style>
    <script>
        function validateAppointmentForm() {
            // Get form field values
            const fullname = document.getElementById("fullname").value.trim();
            const age = document.getElementById("age").value.trim();
            const appointDate = document.getElementById("appoint_date").value.trim();
            const email = document.getElementById("email").value.trim();
            const phno = document.getElementById("phno").value.trim();
            const diseases = document.getElementById("diseases").value.trim();
            const doctor = document.getElementById("doctor").value.trim();
            const address = document.getElementById("address").value.trim();

            const emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
            const phonePattern = /^[0-9]{10}$/;

            // Reset error messages
            document.getElementById("fullnameError").textContent = "";
            document.getElementById("ageError").textContent = "";
            document.getElementById("appointDateError").textContent = "";
            document.getElementById("emailError").textContent = "";
            document.getElementById("phnoError").textContent = "";
            document.getElementById("diseasesError").textContent = "";
            document.getElementById("doctorError").textContent = "";
            document.getElementById("addressError").textContent = "";

            let isValid = true;

            // Validate Full Name
            if (fullname === "") {
                document.getElementById("fullnameError").textContent = "Full Name is required.";
                isValid = false;
            }

            // Validate Age
            if (age === "" || isNaN(age) || age <= 0) {
                document.getElementById("ageError").textContent = "Please enter a valid age.";
                isValid = false;
            }

            // Validate Appointment Date
            if (appointDate === "") {
                document.getElementById("appointDateError").textContent = "Appointment date is required.";
                isValid = false;
            }

            // Validate Email
            if (!email.match(emailPattern)) {
                document.getElementById("emailError").textContent = "Invalid email format.";
                isValid = false;
            }

            // Validate Phone Number
            if (!phno.match(phonePattern)) {
                document.getElementById("phnoError").textContent = "Phone number must be 10 digits.";
                isValid = false;
            }

            // Validate Diseases
            if (diseases === "") {
                document.getElementById("diseasesError").textContent = "Please specify your diseases.";
                isValid = false;
            }

            // Validate Doctor Selection
            if (doctor === "") {
                document.getElementById("doctorError").textContent = "Please select a doctor.";
                isValid = false;
            }

            // Validate Address
            if (address === "") {
                document.getElementById("addressError").textContent = "Address is required.";
                isValid = false;
            }

            return isValid;
        }
    </script>
</head>
<body>
    <%@include file="component/navbar.jsp"%>

    <c:if test="${empty userObj}">
        <c:redirect url="user_login.jsp"></c:redirect>
    </c:if>

    <div class="container-fulid backImg p-5">
        <p class="text-center fs-2 text-white"></p>
    </div>

    <div class="container p-3">
        <div class="row">
            <div class="col-md-6 p-5">
                <img src="img/doct.jpg" alt="Doctor" class="img-fluid rounded">
            </div>
            <div class="col-md-6">
                <div class="card paint-card">
                    <div class="card-body">
                        <p class="text-center fs-3">User Appointment</p>
                        <c:if test="${not empty errorMsg}">
                            <p class="fs-5 text-center text-danger">${errorMsg}</p>
                            <c:remove var="errorMsg" scope="session" />
                        </c:if>
                        <c:if test="${not empty succMsg}">
                            <p class="fs-5 text-center text-success">${succMsg}</p>
                            <c:remove var="succMsg" scope="session" />
                        </c:if>

                        <form class="row g-3" action="appAppointment" method="post" onsubmit="return validateAppointmentForm();">
                            <input type="hidden" name="userid" value="${userObj.id}">

                            <div class="col-md-6">
                                <label for="fullname" class="form-label">Full Name</label>
                                <input id="fullname" name="fullname" type="text" class="form-control">
                                <span id="fullnameError" class="error-message"></span>
                            </div>

                            <div class="col-md-6">
                                <label for="gender" class="form-label">Gender</label>
                                <select id="gender" class="form-control" name="gender" required>
                                    <option value="male">Male</option>
                                    <option value="female">Female</option>
                                </select>
                            </div>

                            <div class="col-md-6">
                                <label for="age" class="form-label">Age</label>
                                <input id="age" name="age" type="number" class="form-control">
                                <span id="ageError" class="error-message"></span>
                            </div>

                            <div class="col-md-6">
                                <label for="appoint_date" class="form-label">Appointment Date</label>
                                <input id="appoint_date" name="appoint_date" type="date" class="form-control">
                                <span id="appointDateError" class="error-message"></span>
                            </div>

                            <div class="col-md-6">
                                <label for="email" class="form-label">Email</label>
                                <input id="email" name="email" type="email" class="form-control">
                                <span id="emailError" class="error-message"></span>
                            </div>

                            <div class="col-md-6">
                                <label for="phno" class="form-label">Phone No</label>
                                <input id="phno" name="phno" type="text" class="form-control">
                                <span id="phnoError" class="error-message"></span>
                            </div>

                            <div class="col-md-6">
                                <label for="diseases" class="form-label">Diseases</label>
                                <input id="diseases" name="diseases" type="text" class="form-control">
                                <span id="diseasesError" class="error-message"></span>
                            </div>

                            <div class="col-md-6">
                                <label for="doctor" class="form-label">Doctor</label>
                                <select id="doctor" name="doct" class="form-control">
                                    <option value="">--Select--</option>
                                    <%
                                    DoctorDao doctorDao = new DoctorDao(DBConnect.getConn());
                                    List<Doctor> doctors = doctorDao.getAllDoctor();
                                    for (Doctor doctor : doctors) {
                                    %>
                                    <option value="<%=doctor.getId()%>">
                                        <%=doctor.getFullName()%> (<%=doctor.getSpecialist()%>)
                                    </option>
                                    <%
                                    }
                                    %>
                                </select>
                                <span id="doctorError" class="error-message"></span>
                            </div>

                            <div class="col-md-12">
                                <label for="address" class="form-label">Full Address</label>
                                <textarea id="address" name="address" class="form-control" rows="3"></textarea>
                                <span id="addressError" class="error-message"></span>
                            </div>

                            <div class="col-md-12 text-center">
                                <button class="btn btn-success" type="submit">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@include file
