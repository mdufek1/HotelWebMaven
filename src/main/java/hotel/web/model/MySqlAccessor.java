/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.web.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class MySqlAccessor implements DatabaseAccessorStrategy {

    private Connection connection;

    @Override
    public void openConnection(String dbDriver, String dbAddress, String dbUser, String dbPassword) {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySqlAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection = DriverManager.getConnection(dbAddress, dbUser, dbPassword);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Map<String, Object>> retreiveAllRecordsFromTable(String table) {
        Map rec = null;
        ResultSet result = null;
        Statement statement = null;
        ResultSetMetaData meta = null;
        List recList = new ArrayList();
        String query = "Select * From " + table;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            meta = result.getMetaData();
            int numCols = meta.getColumnCount();

            while (result.next()) {
                rec = new HashMap();
                for (int i = 1; i <= numCols; i++) {
                    rec.put(meta.getColumnName(i), result.getObject(i));

                }
                recList.add(rec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySqlAccessor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySqlAccessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConnection();
        }
        return recList;
    }
    public List<Map<String, Object>> retreiveAllRecordsFromTableWithColumn(String table, String col, String val) {
        Map rec = null;
        ResultSet result = null;
        Statement statement = null;
        ResultSetMetaData meta = null;
        List recList = new ArrayList();
        String query = "Select * From " + table +" where "+col+" = "+val;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            meta = result.getMetaData();
            int numCols = meta.getColumnCount();

            while (result.next()) {
                rec = new HashMap();
                for (int i = 1; i <= numCols; i++) {
                    rec.put(meta.getColumnName(i), result.getObject(i));

                }
                recList.add(rec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySqlAccessor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySqlAccessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConnection();
        }
        return recList;
    }
    @Override
    public void addNewRecord(String table, Map<String, Object> record) throws SQLException {
        String sqlInsert = "";
        String sqlValues = "";
        Statement statement = connection.createStatement();
        Object[] keys = record.keySet().toArray();
        Object[] values = record.values().toArray();
        for (int i = 0; i < record.size(); i++) {
            if (i == keys.length - 1) {
                sqlInsert += keys[i];
                sqlValues += "'" + values[i].toString() + "'";
            } else {
                sqlInsert += keys[i] + ",";
                sqlValues += "'" + values[i].toString() + "'" + ",";
            }
        }
        String sql = "INSERT INTO " + table + " (" + sqlInsert + ") " + "VALUES(" + sqlValues + ")";
        System.out.println(sql);
        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlAccessor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySqlAccessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConnection();
        }

    }

    @Override
    public void addNewRecord(String table, String[] columns, Object[] recordValues) {
        String sqlInsert = "";
        String sqlValues = "";
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < columns.length; i++) {
            if (i == columns.length - 1) {
                sqlInsert += columns[i];
                sqlValues += "'" + recordValues[i].toString() + "'";
            } else {
                sqlInsert += columns[i] + ",";
                sqlValues += "'" + recordValues[i].toString() + "'" + ",";
            }
        }
        String sql = "INSERT INTO " + table + " (" + sqlInsert + ") " + "VALUES(" + sqlValues + ")";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlAccessor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySqlAccessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConnection();
        }
    }

    public void updateRecords(String table, Map<String, Object> updateInfo, String where, String value) throws SQLException {

        String sql = "UPDATE " + table + " SET ";
        String sqlInsert = "";
        String sqlValues = "";
        Statement statement = connection.createStatement();
        Object[] keys = updateInfo.keySet().toArray();
        Object[] values = updateInfo.values().toArray();
        for (int i = 0; i < updateInfo.size(); i++) {
            if (i == keys.length - 1) {
                sql += keys[i] + " = '" + values[i] + "' WHERE " + where + " = " + value;
            } else {
                sql += keys[i] + " = '" + values[i] + "', ";
            }
        }
        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlAccessor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySqlAccessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConnection();
        }
    }
    
    @Override
    public void deleteRecords(String table, String where, String value) throws SQLException{
        //                        sql = "DELETE FROM actor WHERE"
//                                + " actor_id = " + deleteId;
        String sql = "DELETE FROM " + table + " WHERE "+where+" = "+value;
        String resetCount = "ALTER TABLE "+table+" AUTO_INCREMENT = 1";
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate(sql);
            statement.executeUpdate(resetCount);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlAccessor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySqlAccessor.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConnection();
        }
    }
    
}
