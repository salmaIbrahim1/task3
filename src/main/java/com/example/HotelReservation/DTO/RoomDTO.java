package com.example.HotelReservation.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoomDTO {

    private Long id;

    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @NotBlank(message = "Room type is required")
    private String roomType;

    @NotBlank(message = "Room status is required")
    private String status;
}