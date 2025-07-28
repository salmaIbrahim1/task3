package com.example.HotelReservation.controller;

import com.example.HotelReservation.DTO.ReservationDTO;
import com.example.HotelReservation.entity.Reservation;
import com.example.HotelReservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
@Tag(name = "Reservation Management", description = "Endpoints for reservation operations")
@SecurityRequirement(name = "bearerAuth")
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(
            summary = "Create a reservation",
            description = "Book a room for specified dates",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reservation created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input or dates"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Room or user not found")
            })
    @PostMapping
    public ResponseEntity<Reservation> makeReservation(@RequestBody @Valid ReservationDTO reservationDTO) {
        Reservation reservation = reservationService.makeReservation(reservationDTO);
        return ResponseEntity.ok(reservation);
    }

    @Operation(
            summary = "Get user reservations",
            description = "Retrieve all reservations for a specific user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUser(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Long userId) {
        List<Reservation> reservations = reservationService.getReservationsByUser(userId);
        return ResponseEntity.ok(reservations);
    }

    @Operation(
            summary = "Update reservation",
            description = "Update check-in/check-out dates of a reservation",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reservation updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Reservation not found")
            })
    @PutMapping("/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(
            @Parameter(description = "ID of the reservation to update", required = true)
            @PathVariable Long reservationId,
            @RequestBody @Valid ReservationDTO reservationDTO) {
        Reservation updated = reservationService.updateReservation(reservationId, reservationDTO);
        return ResponseEntity.ok(updated);
    }

    @Operation(
            summary = "Cancel reservation",
            description = "Cancel an existing reservation",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Reservation cancelled successfully"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Reservation not found")
            })
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> cancelReservation(
            @Parameter(description = "ID of the reservation to cancel", required = true)
            @PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.noContent().build();
    }
}