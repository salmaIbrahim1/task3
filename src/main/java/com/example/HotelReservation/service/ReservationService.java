package com.example.HotelReservation.service;
import com.example.HotelReservation.DTO.ReservationDTO;
import com.example.HotelReservation.entity.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation makeReservation(ReservationDTO reservationDTO);
    List<Reservation> getReservationsByUser(Long userId);
    Reservation updateReservation(Long reservationId, ReservationDTO reservationDTO);
    void cancelReservation(Long reservationId);
}