package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.appl.domain.Department;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class EntityUtilsTest {

    @Test
    public void testGetTableNameMethod() {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "departments", tableName);
    }

    @Test
    public void testGetColumns() {
        List<ColumnInfo> list = EntityUtils.getColumns(Department.class);
        assertEquals("Number of columns should be 3!", 3, list.size());
    }

    @Test
    public void testCastFromSqlType() {
        BigDecimal value = new BigDecimal(1.0);
        Object result = EntityUtils.castFromSqlType(value, Integer.class);
        assertEquals("Type is not Integer!", Integer.class, result.getClass());
    }

    @Test
    public void testGetFieldsByAnnotations() {
        List<Field> list = EntityUtils.getFieldsByAnnotations(Department.class, Column.class);
        assertEquals("Number of columns should be 2!", (long)2, list.size());
    }

    /*
    @Test
    public void testGetSqlValue() {
        Department dep = new Department();
        dep.setId(12);
        Object result = EntityUtils.getSqlValue();
        assertEquals("", 12, result);
    }
    */

}
