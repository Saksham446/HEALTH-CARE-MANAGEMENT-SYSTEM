package com.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.dao.UserDao;
import com.db.DBConnect;
import com.entity.User;

@WebServlet("/user_register")
public class UserRegister extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        try {
            // Get form parameters
            String fullName = req.getParameter("fullname").trim();
            String email = req.getParameter("email").trim();
            String password = req.getParameter("password");

            // Validate input
            if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                session.setAttribute("errorMsg", "All fields are required.");
                resp.sendRedirect("signup.jsp");
                return;
            }

            if (!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
                session.setAttribute("errorMsg", "Invalid email format.");
                resp.sendRedirect("signup.jsp");
                return;
            }

            // Hash the password
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            // Create User object
            User user = new User(fullName, email, hashedPassword);

            // Interact with DAO
            UserDao dao = new UserDao(DBConnect.getConn());
            boolean isRegistered = dao.register(user);

            // Provide feedback
            if (isRegistered) {
                session.setAttribute("sucMsg", "Registered successfully!");
                resp.sendRedirect("signup.jsp");
            } else {
                session.setAttribute("errorMsg", "Registration failed. Try again later.");
                resp.sendRedirect("signup.jsp");
            }

        } catch (Exception e) {
            session.setAttribute("errorMsg", "An unexpected error occurred. Please try again later.");
            resp.sendRedirect("signup.jsp");
            e.printStackTrace();
        }
    }
}
