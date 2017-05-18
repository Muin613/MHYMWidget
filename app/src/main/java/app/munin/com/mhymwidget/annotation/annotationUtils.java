package app.munin.com.mhymwidget.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/5/1.
 */

public class annotationUtils {
    public static String getString(Object f) {
        StringBuilder sql = new StringBuilder();
        Class c1 = f.getClass();
        boolean exists = c1.isAnnotationPresent(Table.class);
        if (!exists)
            return null;
        Table t = (Table) c1.getAnnotation(Table.class);
        String table = t.value();
        sql.append("select * from ").append(table).append("where w=1");
        Field[] fields = c1.getDeclaredFields();
        for (Field field : fields) {
            boolean Fexists = field.isAnnotationPresent(Column.class);
            if (!Fexists) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            String filedName = field.getName();
            String getMethodName = "get" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
            Method method = null;
            Object columnValue = null;

            try {
                method = c1.getMethod(getMethodName);
                columnValue = method.invoke(f);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            if (columnValue instanceof Integer && (int) columnValue != 0) {
                sql.append("and " + columnName + "=" + columnValue + " ");
            } else if (columnValue instanceof String) {
                if (((String) columnValue).contains(",")) {
                    String[] value = ((String) columnValue).split(",");
                    sql.append("and" + columnName + "in  (");
                    for (String va : value)
                        sql.append("'").append(va).append("',");
                    sql.deleteCharAt(sql.length() - 1);
                    sql.append(")");
                } else
                    sql.append("and " + columnName + "= '" + columnValue + "' ");
            }

        }
        return sql.toString();
    }
}
