package Repository;

import Model.Staff;
import java.util.List;

public interface StaffRepository {
    void save(Staff staff);

    Staff findById(int id);

    List<Staff> findAll();

    void update(Staff staff);

    void delete(int id);

    List<Staff> findByBuildingId(int buildingId);
}
