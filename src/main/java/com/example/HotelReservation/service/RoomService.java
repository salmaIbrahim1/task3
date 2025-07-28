package com.example.HotelReservation.service;

import com.example.HotelReservation.DTO.RoomDTO;
import com.example.HotelReservation.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();  // Added this method to match controller
    List<Room> getRoomsByType(String roomType);
    Page<Room> getAllRooms(String roomType, String status, Pageable pageable);
    Room addRoom(RoomDTO roomDTO);
    Room updateRoomStatus(Long id, String status);
    void deleteRoom(Long roomId);  // Added this method to match controller
}