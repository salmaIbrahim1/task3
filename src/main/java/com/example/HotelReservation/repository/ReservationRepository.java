package com.example.HotelReservation.repository;
import com.example.HotelReservation.entity.Reservation;
import com.example.HotelReservation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Date;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId);
    boolean existsByRoomIdAndDateRange(Long roomId, Date startDate, Date endDate);
}