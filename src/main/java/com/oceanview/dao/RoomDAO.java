package com.oceanview.dao;
import com.oceanview.model.Room;
import com.oceanview.util.DBConnection;
import java.sql.*;
import java.util.*;
public class RoomDAO {
    public List<Room> getAvailableRooms() throws Exception {
        List<Room> list=new ArrayList<>(); Connection conn=DBConnection.getInstance().getConnection();
        ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM room WHERE status='AVAILABLE' ORDER BY room_type,room_number");
        while(rs.next())list.add(mapRoom(rs)); return list;
    }
    public List<Room> getAllRooms() throws Exception {
        List<Room> list=new ArrayList<>(); Connection conn=DBConnection.getInstance().getConnection();
        ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM room ORDER BY room_number");
        while(rs.next())list.add(mapRoom(rs)); return list;
    }
    public Room getRoomById(int roomId) throws Exception {
        Connection conn=DBConnection.getInstance().getConnection();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM room WHERE room_id=?");
        ps.setInt(1,roomId); ResultSet rs=ps.executeQuery();
        if(rs.next())return mapRoom(rs); return null;
    }
    private Room mapRoom(ResultSet rs) throws SQLException {
        Room r=new Room(); r.setRoomId(rs.getInt("room_id")); r.setRoomNumber(rs.getString("room_number"));
        r.setRoomType(rs.getString("room_type")); r.setPricePerNight(rs.getDouble("price_per_night"));
        r.setStatus(rs.getString("status")); r.setDescription(rs.getString("description")); return r;
    }
}
