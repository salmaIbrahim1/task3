package com.example.HotelReservation.controller;

import com.example.HotelReservation.DTO.RoomDTO;
import com.example.HotelReservation.entity.Room;
import com.example.HotelReservation.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@Tag(name = "Rooms", description = "Room management API")
@SecurityRequirement(name = "bearerAuth")
public class RoomController {

    private final RoomService roomService;

    @Operation(summary = "Get all rooms", description = "Retrieve a list of all rooms")
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @Operation(summary = "Get rooms by type", description = "Retrieve rooms filtered by room type")
    @GetMapping("/type/{roomType}")
    public ResponseEntity<List<Room>> getRoomsByType(@PathVariable String roomType) {
        List<Room> rooms = roomService.getRoomsByType(roomType);
        return ResponseEntity.ok(rooms);
    }




}