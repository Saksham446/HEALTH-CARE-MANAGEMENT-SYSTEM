import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.*;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dao.SpecialistDao;
import com.entity.Specalist;

public class SpecialistDaoTest {

    private Connection conn;
    private SpecialistDao specialistDao;

    @BeforeEach
    public void setUp() {
        conn = mock(Connection.class);
        specialistDao = new SpecialistDao(conn);
    }

    @Test
    public void testAddSpecialist() throws SQLException {
        String specName = "Cardiologist";
        String sql = "insert into specialist(spec_name) values(?)";
        PreparedStatement ps = mock(PreparedStatement.class);
        when(conn.prepareStatement(sql)).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);

        boolean result = specialistDao.addSpecialist(specName);

        assertTrue(result);
        verify(conn).prepareStatement(sql);
        verify(ps).executeUpdate();
    }

    @Test
    public void testGetAllSpecialists() throws SQLException {
        String sql = "select * from specialist";
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.prepareStatement(sql)).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt(1)).thenReturn(1);
        when(rs.getString(2)).thenReturn("Cardiologist");

        List<Specalist> specialists = specialistDao.getAllSpecialist();

        assertNotNull(specialists);
        assertFalse(specialists.isEmpty());
        assertEquals("Cardiologist", specialists.get(0).getSpecialistName());
    }
}
