/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.web.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mike
 */
public interface DatabaseAccessorStrategy {
    public abstract void openConnection(String dbDriver, String dbAddress, String dbUser, String dbPassword);
    public abstract void closeConnection();
    public abstract void addNewRecord(String table, String[] columns, Object[] recordValues) throws SQLException;
    public abstract void addNewRecord(String table, Map<String, Object> record) throws SQLException;
    public abstract List<Map<String, Object>> retreiveAllRecordsFromTable(String table);

    public void deleteRecords(String hotel, String hotel_id, String string)throws SQLException;

    public void updateRecords(String hotel, Map<String, Object> updateInfo, String where, String value)throws SQLException;
    public abstract List<Map<String, Object>> retreiveAllRecordsFromTableWithColumn(String table, String col, String val) ;
}
