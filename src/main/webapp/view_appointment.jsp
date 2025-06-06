<%@page import="com.entity.User"%>
<%@page import="com.entity.Doctor"%>
<%@page import="com.dao.DoctorDao"%>
<%@page import="com.entity.Appointment"%>
<%@page import="java.util.List"%>
<%@page import="com.db.DBConnect"%>
<%@page import="com.dao.AppointmentDAO"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>View Appointment</title>
    <%@include file="component/allcss.jsp"%>
    <style type="text/css">
        .paint-card {
            box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
        }

        .backImg {
            background: linear-gradient(rgba(0, 0, 0, .4), rgba(0, 0, 0, .4)),
                        url("img/hospital.jpg");
            height: 20vh;
            width: 100%;
            background-size: cover;
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>
    <%-- Redirect to login if user is not logged in --%>
    <c:if test="${empty userObj}">
        <c:redirect url="user_login.jsp"></c:redirect>
    </c:if>

    <%-- Include Navbar --%>
    <%@include file="component/navbar.jsp"%>

    <div class="container-fulid backImg p-5">
        <p class="text-center fs-2 text-white"></p>
    </div>

    <div class="container p-3">
        <div class="row">
            <%-- Appointment Table --%>
            <div class="col-md-9">
                <div class="card paint-card">
                    <div class="card-body">
                        <p class="fs-4 fw-bold text-center text-success">Appointment List</p>
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Full Name</th>
                                    <th scope="col">Gender</th>
                                    <th scope="col">Age</th>
                                    <th scope="col">Appoint Date</th>
                                    <th scope="col">Diseases</th>
                                    <th scope="col">Doctor Name</th>
                                    <th scope="col">Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                User user = (User) session.getAttribute("userObj");
                                AppointmentDAO appointmentDAO = new AppointmentDAO(DBConnect.getConn());
                                DoctorDao doctorDAO = new DoctorDao(DBConnect.getConn());
                                List<Appointment> appointments = appointmentDAO.getAllAppointmentByLoginUser(user.getId());
                                for (Appointment ap : appointments) {
                                    Doctor doctor = doctorDAO.getDoctorById(ap.getDoctorId());
                                %>
                                <tr>
                                    <td><%= ap.getFullName() %></td>
                                    <td><%= ap.getGender() %></td>
                                    <td><%= ap.getAge() %></td>
                                    <td><%= ap.getAppoinDate() %></td>
                                    <td><%= ap.getDiseases() %></td>
                                    <td><%= doctor.getFullName() %></td>
                                    <td>
                                        <% if ("Pending".equals(ap.getStatus())) { %>
                                            <span class="badge bg-warning">Pending</span>
                                        <% } else if ("Approved".equals(ap.getStatus())) { %>
                                            <span class="badge bg-success">Approved</span>
                                        <% } else { %>
                                            <span class="badge bg-danger">Rejected</span>
                                        <% } %>
                                    </td>
                                </tr>
                                <%
                                }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <%-- Static Image Section --%>
            <div class="col-md-3 p-3">
                <img alt="Doctor" src="img/doct.jpg" class="img-fluid rounded">
            </div>
        </div>
    </div>

    <%-- Include Footer --%>
    <%@include file="component/footer.jsp"%>
</body>
</html>
