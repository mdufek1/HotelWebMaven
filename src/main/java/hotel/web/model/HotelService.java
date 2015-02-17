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
public class HotelService {
    private DataAccessObjectStrategy hd;
    
    public HotelService(String[] connection, String table) {
        hd = new HotelDAO(connection, table);
    }

    public void setHd(HotelDAO hd) {
        this.hd = hd;
    }
    
   public Hotel getHotelByID(int id){
       return hd.getHotelByID(id);
   }
   
   public List<Hotel> getAllHotels(){
       return hd.getAllHotels();
   } 
   
   public void addHotel(Hotel hotel) throws SQLException{
       hd.addHotel(hotel);
   }
   
   public void updateHotel(Hotel hotel, String where, String value) throws SQLException{
       hd.updateHotel(hotel, where, value);
   }
   
   public void removeHotel(String where, String value) throws SQLException{
       hd.removeHotel(where, value);
   }
}
