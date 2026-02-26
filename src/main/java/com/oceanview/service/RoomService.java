package com.oceanview.service;
import com.oceanview.dao.RoomDAO;
import com.oceanview.model.Room;
import java.util.List;
public class RoomService {
    private final RoomDAO roomDAO=new RoomDAO();
    public List<Room> getAvailableRooms() throws Exception { return roomDAO.getAvailableRooms(); }
    public List<Room> getAllRooms() throws Exception { return roomDAO.getAllRooms(); }
}
