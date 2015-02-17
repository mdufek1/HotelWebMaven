/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.web.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mike
 */
public class HotelDAO implements DataAccessObjectStrategy {
    DatabaseAccessorStrategy db = new MySqlAccessor();
    String[] connection;
    String table;

    public HotelDAO(String[] connection, String table) {
        this.connection = connection;
        this.table = table;
    }

    
    @Override
    public List<Hotel> getAllHotels(){
        List<Hotel> list = new ArrayList<>();
        db.openConnection(connection[0], connection[1], connection[2], connection[3]);
        List<Map<String,Object>> rawList = db.retreiveAllRecordsFromTable("hotel");
        for(Map<String, Object> rec : rawList){ 
            list.add(new Hotel((Integer)rec.get("hotel_id"), (String)rec.get("hotel_name"),
                (String)rec.get("street_address"),(String)rec.get("city"),(String)rec.get("state"),(String)rec.get("postal_code"),(String)rec.get("notes")));
        }
        
        return list;
    }
    
    @Override
    public Hotel getHotelByID(int id){
        Hotel h = null;
        db.openConnection(connection[0], connection[1], connection[2], connection[3]);
        List<Map<String,Object>> rawList = db.retreiveAllRecordsFromTableWithColumn("hotel","hotel_id", id+"");
        for(Map<String, Object> rec : rawList){ 
            h = (new Hotel((Integer)rec.get("hotel_id"), (String)rec.get("hotel_name"),
                (String)rec.get("street_address"),(String)rec.get("city"),(String)rec.get("state"),(String)rec.get("postal_code"),(String)rec.get("notes")));
        }
        
        return h;
    }
    
    @Override
    public void addHotel(Hotel hotel) throws SQLException{
        db.openConnection(connection[0], connection[1], connection[2], connection[3]);
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("hotel_id", hotel.getHotelID());
        m.put("hotel_name", hotel.getHotelName());
        m.put("street_address", hotel.getStreetAddress());
        m.put("city", hotel.getCity());
        m.put("state",hotel.getState());
        m.put("postal_code", hotel.getPostalCode());
        m.put("notes", hotel.getNote().replace("'", "\\'"));
        db.addNewRecord(table, m);
    }
    
    @Override
    public void updateHotel(Hotel hotel, String where, String value) throws SQLException{
        db.openConnection(connection[0], connection[1], connection[2], connection[3]);
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("hotel_id", hotel.getHotelID());
        m.put("hotel_name", hotel.getHotelName());
        m.put("street_address", hotel.getStreetAddress());
        m.put("city", hotel.getCity());
        m.put("state",hotel.getState());
        m.put("postal_code", hotel.getPostalCode());
        m.put("notes", hotel.getNote());
        db.updateRecords(table, m, where, value);
    }
    @Override
    public void removeHotel(String where, String value) throws SQLException{
        db.openConnection(connection[0], connection[1], connection[2], connection[3]);
        db.deleteRecords(table, where, value);
    }

    @Override
    public DatabaseAccessorStrategy getDb() {
        return db;
    }

    @Override
    public void setDb(DatabaseAccessorStrategy db) {
        this.db = db;
    }

    @Override
    public String[] getConnection() {
        return connection;
    }

    @Override
    public void setConnection(String[] connection) {
        this.connection = connection;
    }

    @Override
    public String getTable() {
        return table;
    }

    @Override
    public void setTable(String table) {
        this.table = table;
    }


    

}