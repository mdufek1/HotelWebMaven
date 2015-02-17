/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.web.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mike
 */
public interface DataAccessObjectStrategy {


    abstract List<Hotel> getAllHotels();

    abstract String[] getConnection();

    abstract DatabaseAccessorStrategy getDb();

    abstract String getTable();

    abstract void removeHotel(String where, String value) throws SQLException;

    abstract void setConnection(String[] connection);

    abstract void setDb(DatabaseAccessorStrategy db);

    abstract void setTable(String table);
    
    abstract void updateHotel(Hotel hotel, String where, String value) throws SQLException;
    abstract void addHotel(Hotel hotel) throws SQLException;
    abstract Hotel getHotelByID(int id);
}
