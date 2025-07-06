package Service;

import Model.Building;
import Model.Staff;
import Repository.StaffRepository;

import java.util.List;

public class StaffService {
    private final StaffRepository repository;

    public StaffService(StaffRepository repository) {
        this.repository = repository;
    }

    public void addStaff(String firstName, String lastName, Staff.Position position, Staff.Shift shift,
            Building building) {
        Staff staff = new Staff(0, firstName, lastName, position, shift, building);
        repository.save(staff);
    }

    public Staff getStaffById(int id) {
        return repository.findById(id);
    }

    public List<Staff> getAllStaff() {
        return repository.findAll();
    }

    public void updateStaff(int id, String firstName, String lastName, Staff.Position position, Staff.Shift shift,
            Building building) {
        Staff staff = new Staff(id, firstName, lastName, position, shift, building);
        repository.update(staff);
    }

    public void removeStaff(int id) {
        repository.delete(id);
    }

    public List<Staff> getStaffByBuilding(int buildingId) {
        return repository.findByBuildingId(buildingId);
    }
}
