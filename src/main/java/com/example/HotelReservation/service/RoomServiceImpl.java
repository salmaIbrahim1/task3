package com.example.HotelReservation.service;

import com.example.HotelReservation.DTO.RoomDTO;
import com.example.HotelReservation.entity.Room;
import com.example.HotelReservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> getRoomsByType(String roomType) {
        if (roomType == null || roomType.trim().isEmpty()) {
            throw new IllegalArgumentException("Room type cannot be null or empty");
        }

        List<Room> rooms = roomRepository.findByRoomTypeIgnoreCase(roomType.trim());


        return rooms;
    }

    @Override
    public Page<Room> getAllRooms(String roomType, String status, Pageable pageable) {
        // Implement your paginated search logic here
        // This is just a placeholder - adjust based on your repository methods
        return roomRepository.findAll(pageable);
    }

    @Override
    public Room addRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setRoomType(roomDTO.getRoomType());
        room.setStatus(roomDTO.getStatus());
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoomStatus(Long id, String status) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        room.setStatus(status);
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long roomId) {
        if (!roomRepository.existsById(roomId)) {
            throw new RuntimeException("Room not found");
        }
        roomRepository.deleteById(roomId);
    }
}