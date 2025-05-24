import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.*;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dao.DoctorDao;
import com.entity.Doctor;

public class DoctorDaoTest {

    private Connection conn;
    private DoctorDao doctorDao;

    @BeforeEach
    public void setUp() {
        conn = mock(Connection.class);
        doctorDao = new DoctorDao(conn);
    }

    @Test
    public void testRegisterDoctor() throws SQLException {
        Doctor doctor = new Doctor("Dr. John Doe", "1980-05-15", "MBBS", "Cardiology", "john@example.com", "1234567890", "password123");
        String sql = "insert into doctor(full_name,dob,qualification,specialist,email,mobno,password) values(?,?,?,?,?,?,?)";
        PreparedStatement ps = mock(PreparedStatement.class);
        when(conn.prepareStatement(sql)).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);

        boolean result = doctorDao.registerDoctor(doctor);

        assertTrue(result);
        verify(conn).prepareStatement(sql);
        verify(ps).executeUpdate();
    }

    @Test
    public void testGetAllDoctor() throws SQLException {
        String sql = "select * from doctor order by id desc";
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(sql)).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt(1)).thenReturn(1);
        when(rs.getString(2)).thenReturn("Dr. John Doe");

        List<Doctor> doctors = doctorDao.getAllDoctor();

        assertNotNull(doctors);
        assertFalse(doctors.isEmpty());
    }

    @Test
    public void testGetDoctorById() throws SQLException {
        String sql = "select * from doctor where id=?";
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        Doctor doctor = new Doctor();
        doctor.setFullName("Dr. John Doe");

        when(conn.prepareStatement(sql)).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(1);
        when(rs.getString(2)).thenReturn("Dr. John Doe");

        Doctor result = doctorDao.getDoctorById(1);

        assertNotNull(result);
        assertEquals("Dr. John Doe", result.getFullName());
    }

    @Test
    public void testUpdateDoctor() throws SQLException {
        Doctor doctor = new Doctor("Dr. Jane Doe", "1985-08-10", "MD", "Neurology", "jane@example.com", "9876543210", "newpassword");
        String sql = "update doctor set full_name=?,dob=?,qualification=?,specialist=?,email=?,mobno=?,password=? where id=?";
        PreparedStatement ps = mock(PreparedStatement.class);
        when(conn.prepareStatement(sql)).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);

        boolean result = doctorDao.updateDoctor(doctor);

        assertTrue(result);
        verify(conn).prepareStatement(sql);
        verify(ps).executeUpdate();
    }

    @Test
    public void testDeleteDoctor() throws SQLException {
        String sql = "delete from doctor where id=?";
        PreparedStatement ps = mock(PreparedStatement.class);
        when(conn.prepareStatement(sql)).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);

        boolean result = doctorDao.deleteDoctor(1);

        assertTrue(result);
        verify(conn).prepareStatement(sql);
        verify(ps).executeUpdate();
    }

    @Test
    public void testLogin() throws SQLException {
        String email = "john@example.com";
        String password = "password123";
        String sql = "select * from doctor where email=? and password=?";
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        Doctor doctor = new Doctor("Dr. John Doe", "1980-05-15", "MBBS", "Cardiology", email, "1234567890", password);

        when(conn.prepareStatement(sql)).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(1);
        when(rs.getString(2)).thenReturn("Dr. John Doe");

        Doctor result = doctorDao.login(email, password);

        assertNotNull(result);
        assertEquals("Dr. John Doe", result.getFullName());
    }
}
