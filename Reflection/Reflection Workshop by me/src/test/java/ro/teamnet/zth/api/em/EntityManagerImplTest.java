package ro.teamnet.zth.api.em;


import org.junit.Test;
import static org.testng.AssertJUnit.*;

import ro.teamnet.zth.api.em.EntityManagerImpl;



public class EntityManagerImplTest {

    @Test
    public void testGetNextIdVal_1() {
        EntityManagerImpl EMI = new EntityManagerImpl();
        Long result = EMI.getNextIdVal("departments", "department_id");
        assertEquals(271, result.longValue());
    }

    @Test
    public void testGetNextIdVal_2() {
        EntityManagerImpl EMI = new EntityManagerImpl();
        Long result = EMI.getNextIdVal("employees", "employee_id");
        assertEquals(207, result.longValue());
    }

}
