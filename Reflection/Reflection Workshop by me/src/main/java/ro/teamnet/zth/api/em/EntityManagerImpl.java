package ro.teamnet.zth.api.em;


import ro.teamnet.zth.api.database.DBManager;

import java.sql.*;
import java.util.List;

public class EntityManagerImpl implements EntityManager{

    @Override
    public <T> T findById(Class<T> entityClass, Long id) {
        return null;
    }

    @Override
    public Long getNextIdVal(String tableName, String columnIdName) {

        Connection connection = DBManager.getConnection();
        String max_id = null;

        Statement statement = null;
        String query = "SELECT MAX("+ columnIdName +") AS MAX_ID FROM "+ tableName;

        try {
            statement = connection.createStatement();
            ResultSet RS =  statement.executeQuery(query);

            while (RS.next()) {

                max_id = RS.getString("MAX_ID");
            }

            long result = Long.valueOf(max_id).longValue();

            return result+1;

        } catch (SQLException e ) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T> Object insert(T entity) {
        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        return null;
    }
}
