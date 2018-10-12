package kr.or.visitkorea.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Executer {

    private static final Logger logger = LoggerFactory.getLogger(Executer.class);

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connection;

    public Executer() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mariadb://211.254.216.170:3306/KTO", "front", "front!@#$");
    }

    public List<Map<String, String>> selectQuery(String query) throws SQLException {
        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultList = buildResultSetMap(query, statement.executeQuery(query));
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return resultList;
    }

    private List<Map<String, String>> buildResultSetMap(final String query, final ResultSet rs) throws SQLException {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        ResultSetMetaData rsmd = rs.getMetaData();
        while (rs.next()) {
            HashMap<String, String> record = new HashMap<String, String>();
            for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
                String key = rsmd.getColumnName(i);
                String val = rs.getString(i);
                record.put(key, val);
            }
            list.add(record);
        }
        return list;
    }

    public int executeUpdate(String sql) throws SQLException {
        Statement statement = null;
        // NOTE. AUTO COMMIT ENABLED!
        if (!connection.getAutoCommit())
            connection.setAutoCommit(true);
        try {
            statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}