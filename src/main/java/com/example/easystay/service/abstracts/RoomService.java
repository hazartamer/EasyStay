package com.example.easystay.service.abstracts;

import com.example.easystay.model.enums.RoomType;
import com.example.easystay.service.dtos.requests.room.AddRoomRequest;
import com.example.easystay.service.dtos.responses.room.AddRoomResponse;
import com.example.easystay.service.dtos.responses.room.ListRoomResponse;

import java.util.List;

public interface RoomService {
    List<ListRoomResponse> getAll();

    AddRoomResponse add(AddRoomRequest request);

}
