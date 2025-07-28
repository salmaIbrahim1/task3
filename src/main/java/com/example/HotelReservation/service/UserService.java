package com.example.HotelReservation.service;
import com.example.HotelReservation.DTO.UserDTO;
import com.example.HotelReservation.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> getAllUsers(Pageable pageable);
    User registerUser(UserDTO userDTO);
    User findUserByEmail(String email);
}
