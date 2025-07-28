package com.example.HotelReservation.repository;


import com.example.HotelReservation.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByRoomTypeIgnoreCase(String roomType);
}