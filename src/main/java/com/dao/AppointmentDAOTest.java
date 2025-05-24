import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.*;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dao.AppointmentDAO;
import com.entity.Appointment;

public class AppointmentDAOTest {

    private Connection conn;
    private AppointmentDAO appointmentDAO;

    @BeforeEach
    public void setUp() {
        conn = mock(Connection.class);
        appointmentDAO = new AppointmentDAO(conn);
    }

    @Test
    public void testAddAppointment() throws SQLException {
        Appointment ap = new Appointment(1, "John Doe", "Male", "30", "2024-12-25", "john@example.com", "1234567890", "Fever", 101, "123 Street", "Pending");
        String sql = "insert into appointment(user_id,fullname,gender,age,appoint_date,email,phno,diseases,doctor_id,address,status) values(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = mock(PreparedStatement.class);
        when(conn.prepareStatement(sql)).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);

        boolean result = appointmentDAO.addAppointment(ap);

        assertTrue(result);
        verify(conn).prepareStatement(sql);
        verify(ps).executeUpdate();
    }

    @Test
    public void testGetAllAppointmentByLoginUser() throws SQLException {
        String sql = "select * from appointment where user_id=?";
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(sql)).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt(1)).thenReturn(1);
        when(rs.getInt(2)).thenReturn(1);
        when(rs.getString(3)).thenReturn("John Doe");

        List<Appointment> appointments = appointmentDAO.getAllAppointmentByLoginUser(1);

        assertNotNull(appointments);
        assertFalse(appointments.isEmpty());
    }

    @Test
    public void testGetAppointmentById() throws SQLException {
        String sql = "select * from appointment where id=?";
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        Appointment ap = new Appointment(1, "John Doe", "Male", "30", "2024-12-25", "john@example.com", "1234567890", "Fever", 101, "123 Street", "Pending");

        when(conn.prepareStatement(sql)).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(1);
        when(rs.getString(3)).thenReturn("John Doe");

        Appointment result = appointmentDAO.getAppointmentById(1);

        assertNotNull(result);
        assertEquals("John Doe", result.getFullName());
    }

    @Test
    public void testUpdateCommentStatus() throws SQLException {
        String sql = "update appointment set status=? where id=? and doctor_id=?";
        PreparedStatement ps = mock(PreparedStatement.class);
        when(conn.prepareStatement(sql)).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);

        boolean result = appointmentDAO.updateCommentStatus(1, 101, "Completed");

        assertTrue(result);
        verify(conn).prepareStatement(sql);
        verify(ps).executeUpdate();
    }
}
