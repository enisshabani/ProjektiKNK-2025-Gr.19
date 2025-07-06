package Repository;

import java.util.List;
import Model.DTO.AplikimetDTO;

public interface AplikimetRepository {
    List<AplikimetDTO> getAllAplikimet();
    void updateAplikimStatus(int aplikimId, String newStatus);
}