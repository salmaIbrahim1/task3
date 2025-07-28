package com.example.HotelReservation.service;
import com.example.HotelReservation.DTO.ReservationDTO;
import com.example.HotelReservation.entity.Reservation;
import com.example.HotelReservation.entity.Room;
import com.example.HotelReservation.entity.User;
import com.example.HotelReservation.repository.ReservationRepository;
import com.example.HotelReservation.repository.RoomRepository;
import com.example.HotelReservation.repository.UserRepository;
import com.example.HotelReservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Override
    public Reservation makeReservation(ReservationDTO reservationDTO) {
        if (reservationDTO.getCheckInDate() == null || reservationDTO.getCheckOutDate() == null) {
            throw new RuntimeException("Dates cannot be null");
        }

        if (reservationDTO.getCheckInDate().isAfter(reservationDTO.getCheckOutDate())) {
            throw new RuntimeException("Check-out date must be after check-in date");
        }

        if (reservationDTO.getCheckInDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Check-in date cannot be in the past");
        }

        User user = userRepository.findById(reservationDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Room room = roomRepository.findById(reservationDTO.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (!"AVAILABLE".equals(room.getStatus())) {
            throw new RuntimeException("Room is not available");
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setCheckInDate(reservationDTO.getCheckInDate());
        reservation.setCheckOutDate(reservationDTO.getCheckOutDate());
        reservation.setStatus("CONFIRMED");

        room.setStatus("OCCUPIED");
        roomRepository.save(room);

        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getReservationsByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        return reservationRepository.findByUserId(userId);
    }

    @Override
    public Reservation updateReservation(Long reservationId, ReservationDTO reservationDTO) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (reservationDTO.getCheckInDate() != null) {
            reservation.setCheckInDate(reservationDTO.getCheckInDate());
        }
        if (reservationDTO.getCheckOutDate() != null) {
            reservation.setCheckOutDate(reservationDTO.getCheckOutDate());
        }

        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Room room = reservation.getRoom();
        room.setStatus("AVAILABLE");
        roomRepository.save(room);

        reservationRepository.delete(reservation);
    }
}