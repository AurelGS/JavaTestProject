package ro.teamnet.zth.api.em;


import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EntityUtils {

    private EntityUtils() throws UnsupportedOperationException{

    }

    public static String getTableName(Class entity){

        Table table = (Table)entity.getAnnotation(Table.class);
        if(table!=null) {
            return table.name();
        } else {
            return entity.getSimpleName();
        }

    }

    public static List<ColumnInfo> getColumns(Class entity){

        Field[] allFields = entity.getDeclaredFields();
        List<ColumnInfo> colInfo = new ArrayList<>();
        for (Field field : allFields)
        {
            ColumnInfo info = new ColumnInfo();
            if( field.getAnnotation(Id.class) != null)
                info.setisId(true);
            else
            {
                info.setColumnName(field.getName());
                info.setisId(false);
                info.getColumnType(field.getType());
            }
            colInfo.add(info);
        }
        return colInfo;
    }

    public static Object castFromSqlType(Object value, Class wantedType){
        if (value.getClass().equals(BigDecimal.class) && wantedType.getClass().equals(Integer.class)){
            return ((BigDecimal) value).intValue();
        } else if (value.getClass().equals(BigDecimal.class) && wantedType.equals(Long.class)){
            return ((BigDecimal) value).longValue();
        } else if (value.getClass().equals(BigDecimal.class) && wantedType.equals(Float.class)){
            return ((BigDecimal) value).floatValue();
        } else if (value.getClass().equals(BigDecimal.class) && wantedType.equals(Double.class)){
            return ((BigDecimal) value).doubleValue();
        } else if (value.getClass().equals(BigDecimal.class) == false){
            return value;
        }
        else return value;
    }

    public static List<Field> getFieldsByAnnotations(Class clazz, Class annotation){
        Field[] fields = clazz.getDeclaredFields();
        List<Field> list = new ArrayList<>();
        for (Field field : fields){
            if(field.getAnnotation(annotation) != null){
                list.add(field);
            }
        }
        return list;
    }

    public static Object getSqlValue(Object object){
        if(object.getClass().getAnnotations(Table.class) != null)
        {
            Field[] allFields = object.getClass().getDeclaredFields();
            for (Field field : allFields)
            {

            }
        }

    return
    }

}
