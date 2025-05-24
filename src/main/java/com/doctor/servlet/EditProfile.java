package com.doctor.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DoctorDao;
import com.db.DBConnect;
import com.entity.Doctor;

@WebServlet("/doctorUpdateProfile")
public class EditProfile extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        try {
            // Get form parameters
            String fullName = req.getParameter("fullname").trim();
            String dob = req.getParameter("dob").trim();
            String qualification = req.getParameter("qualification").trim();
            String specialization = req.getParameter("spec").trim();
            String email = req.getParameter("email").trim();
            String mobNo = req.getParameter("mobno").trim();
            int id = Integer.parseInt(req.getParameter("id"));

            // Validate input
            if (fullName.isEmpty() || dob.isEmpty() || qualification.isEmpty() || 
                specialization.isEmpty() || email.isEmpty() || mobNo.isEmpty()) {
                session.setAttribute("errorMsgd", "All fields are required.");
                resp.sendRedirect("doctor/edit_profile.jsp");
                return;
            }

            if (!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
                session.setAttribute("errorMsgd", "Invalid email format.");
                resp.sendRedirect("doctor/edit_profile.jsp");
                return;
            }

            if (!mobNo.matches("^\\d{10}$")) {
                session.setAttribute("errorMsgd", "Invalid mobile number. It must be 10 digits.");
                resp.sendRedirect("doctor/edit_profile.jsp");
                return;
            }

            // Create Doctor object
            Doctor doctor = new Doctor(id, fullName, dob, qualification, specialization, email, mobNo, "");

            // Interact with DAO
            DoctorDao dao = new DoctorDao(DBConnect.getConn());
            boolean isUpdated = dao.editDoctorProfile(doctor);

            // Provide feedback
            if (isUpdated) {
                Doctor updatedDoctor = dao.getDoctorById(id);
                session.setAttribute("succMsgd", "Profile updated successfully!");
                session.setAttribute("doctObj", updatedDoctor);
                resp.sendRedirect("doctor/edit_profile.jsp");
            } else {
                session.setAttribute("errorMsgd", "Profile update failed. Try again later.");
                resp.sendRedirect("doctor/edit_profile.jsp");
            }

        } catch (Exception e) {
            session.setAttribute("errorMsgd", "An unexpected error occurred. Please try again later.");
            resp.sendRedirect("doctor/edit_profile.jsp");
            e.printStackTrace();
        }
    }
}
