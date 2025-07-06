package Service;

import Model.DTO.RoomDTO;
import Repository.RoomRepository;
import java.util.List;
import java.util.ResourceBundle;

public class RoomService {
    private final RoomRepository repository;
    private final ResourceBundle bundle;

    public RoomService(ResourceBundle bundle) {
        this.repository = new RoomRepository();
        this.bundle = bundle;
    }

    public List<RoomDTO> getAllRooms() {
        return repository.getAllRooms();
    }

    public String addRoom(RoomDTO roomDTO) {
        if (roomDTO.getRoomNumber() == null || roomDTO.getRoomNumber().trim().isEmpty()) {
            return bundle.getString("roomNumberRequired");
        }
        if (roomDTO.getCapacity() <= 0) {
            return bundle.getString("invalidCapacity");
        }
        if (repository.roomNumberExists(roomDTO.getRoomNumber())) {
            return bundle.getString("roomNumberExists");
        }
        return repository.addRoom(roomDTO) ? "success" : bundle.getString("roomAddFailed");
    }

    public String updateRoom(RoomDTO roomDTO) {
        if (roomDTO.getId() <= 0) {
            return bundle.getString("invalidRoomId");
        }
        if (roomDTO.getRoomNumber() == null || roomDTO.getRoomNumber().trim().isEmpty()) {
            return bundle.getString("roomNumberRequired");
        }
        if (roomDTO.getCapacity() <= 0) {
            return bundle.getString("invalidCapacity");
        }
        return repository.updateRoom(roomDTO) ? "success" : bundle.getString("roomUpdateFailed");
    }

    public String deleteRoom(int roomId) {
        if (roomId <= 0) {
            return bundle.getString("invalidRoomId");
        }
        return repository.deleteRoom(roomId) ? "success" : bundle.getString("roomDeleteFailed");
    }

    public boolean roomExists(int roomId) {
        return repository.roomExists(roomId);
    }
}