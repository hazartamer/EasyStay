package com.example.easystay.repository;


import com.example.easystay.model.entity.Room;
import com.example.easystay.model.enums.RoomType;

import com.example.easystay.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM rooms r WHERE r.roomType = :roomType AND r.status = com.example.easystay.model.enums.Status.AVAILABLE")
    List<Room> findByRoomType(RoomType roomType);
    Optional<Room> findByRoomNumber(int roomNumber);
    List<Room> findByStatus(Status status);





}
