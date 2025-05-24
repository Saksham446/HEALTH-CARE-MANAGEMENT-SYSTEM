import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dao.UserDao;
import com.entity.User;

public class UserDaoTest {

    private Connection conn;
    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        conn = mock(Connection.class);
        userDao = new UserDao(conn);
    }

    @Test
    public void testRegister() throws SQLException {
        String sql = "insert into user_dtls(full_name,email,password) values(?,?,?)";
        PreparedStatement ps = mock(PreparedStatement.class);
        when(conn.prepareStatement(sql)).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);

        User user = new User();
        user.setFullName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("password123");

        boolean result = userDao.register(user);

        assertTrue(result);
        verify(conn).prepareStatement(sql);
        verify(ps).executeUpdate();
    }

    @Test
    public void testLogin() throws SQLException {
        String sql = "select * from user_dtls where email=? and password=?";
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(sql)).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt(1)).thenReturn(1);
        when(rs.getString(2)).thenReturn("John Doe");
        when(rs.getString(3)).thenReturn("john@example.com");
        when(rs.getString(4)).thenReturn("password123");

        User user = userDao.login("john@example.com", "password123");

        assertNotNull(user);
        assertEquals("John Doe", user.getFullName());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    public void testCheckOldPassword() throws SQLException {
        String sql = "select * from user_dtls where id=? and password=?";
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(sql)).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);

        boolean result = userDao.checkOldPassword(1, "oldpassword123");

        assertTrue(result);
        verify(conn).prepareStatement(sql);
        verify(ps).executeQuery();
    }

    @Test
    public void testChangePassword() throws SQLException {
        String sql = "update user_dtls set password=? where id=?";
        PreparedStatement ps = mock(PreparedStatement.class);
        when(conn.prepareStatement(sql)).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);

        boolean result = userDao.changePassword(1, "newpassword123");

        assertTrue(result);
        verify(conn).prepareStatement(sql);
        verify(ps).executeUpdate();
    }
}
