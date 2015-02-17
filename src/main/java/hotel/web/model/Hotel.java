/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.web.model;

/**
 *
 * @author Mike
 */
public class Hotel {
    int hotelID;
    String hotelName;
    String streetAddress;
    String city;
    String state;
    String postalCode;
    String note;

    public Hotel(int hotelID, String hotelName, String streetAddress, String city, String state, String postalCode, String note) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.note = note;
    }

    @Override
    public String toString() {
        return hotelID +" - "+hotelName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.hotelID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hotel other = (Hotel) obj;
        if (this.hotelID != other.hotelID) {
            return false;
        }
        return true;
    }

    public int getHotelID() {
        return hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
}
