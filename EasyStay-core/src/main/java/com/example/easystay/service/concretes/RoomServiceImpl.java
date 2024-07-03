package com.example.easystay.service.concretes;

import com.example.easystay.core.exceptionhandling.exception.types.BusinessException;
import com.example.easystay.mapper.RoomMapper;
import com.example.easystay.model.entity.Room;
import com.example.easystay.model.entity.User;
import com.example.easystay.model.enums.RoomType;
import com.example.easystay.repository.RoomRepository;
import com.example.easystay.repository.UserRepository;
import com.example.easystay.service.abstracts.RoomService;
import com.example.easystay.service.dtos.requests.room.AddRoomRequest;
import com.example.easystay.service.dtos.requests.room.UpdateRoomRequest;
import com.example.easystay.service.dtos.responses.room.AddRoomResponse;
import com.example.easystay.service.dtos.responses.room.DeleteRoomResponse;
import com.example.easystay.service.dtos.responses.room.GetRoomResponse;
import com.example.easystay.service.dtos.responses.room.ListRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final UserRepository repository;

    @Override
    public List<ListRoomResponse> getAll() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(r -> new ListRoomResponse(r.getRoomNumber(),r.getPrice())).toList();
    }

    @Override
    public AddRoomResponse add(AddRoomRequest request) {
        Room room = new Room();

        room.setRoomNumber(request.getRoomNumber());
        room.setPrice(request.getPrice());
        room.setStatus(request.getStatus());
        room.setRoomType(request.getRoomType());
        roomNumberShouldNotExist(request.getRoomNumber());

        roomRepository.save(room);

        AddRoomResponse addRoomResponse = new AddRoomResponse();

        addRoomResponse.setPrice(room.getPrice());
        addRoomResponse.setRoomNumber(request.getRoomNumber());
        return addRoomResponse;
    }
    @Override
    public AddRoomResponse update(UpdateRoomRequest request) {
        Room room = roomRepository.findById(request.getId()).orElseThrow(()
                -> new BusinessException("Oda bulunamamıştır."));
        room.setRoomNumber(request.getRoomNumber());
        room.setPrice(request.getPrice());
        room.setStatus(request.getStatus());
        room.setRoomType(request.getRoomType());

        roomNumberShouldNotExist(request.getRoomNumber());

        roomRepository.save(room);
        AddRoomResponse addRoomResponse = new AddRoomResponse();
        addRoomResponse.setPrice(room.getPrice());
        addRoomResponse.setRoomNumber(request.getRoomNumber());
        return addRoomResponse;
    }

    @Override
    public DeleteRoomResponse delete(long id) {
        Room room = roomRepository.findById(id).orElseThrow(()
                -> new BusinessException("Bu Id'e sahip bir oda bulunamadı."));
        DeleteRoomResponse response = RoomMapper.INSTANCE.roomFromDeleteResponse(room);
        roomRepository.delete(room);
        return response;
    }

    @Override
    public GetRoomResponse getById(long id) {
        Room room = roomRepository.findById(id).orElseThrow(()
                -> new BusinessException("Böyle bir Id'ye sahip oda bulunamamıştır."));
        GetRoomResponse response = RoomMapper.INSTANCE.roomFromGetResponse(room);
        return response;
    }

    @Override
    public List<ListRoomResponse> findByRoomType(RoomType roomType) {
        List<Room> rooms = roomRepository.findByRoomType(roomType);
        return rooms.stream().map(r -> new ListRoomResponse(r.getRoomNumber(),r.getPrice())).toList() ;
    }

    //Business Rules
    private void roomNumberShouldNotExist(int roomNumber){
        Optional<Room> room = roomRepository.findByRoomNumber(roomNumber);
        if (room.isPresent()){
            throw new BusinessException("Böyle bir oda numarasına sahip oda bulunmaktadır.");
        }
    }
}
