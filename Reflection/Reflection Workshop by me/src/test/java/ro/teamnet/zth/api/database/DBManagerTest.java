package ro.teamnet.zth.api.database;

import org.junit.Test;

import java.sql.Connection;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class DBManagerTest {

    @Test
    public void testGetConnection() {
        Connection connection = DBManager.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void testCheckConnection() {
        Connection connection = DBManager.getConnection();
        boolean flag = DBManager.checkConnection(connection);
        assertEquals("Connection could not be established!", true, flag);
    }

}
