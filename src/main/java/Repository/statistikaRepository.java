package Repository;

import Model.DTO.AplikimetDTO;
import java.util.List;

public interface StatistikaRepository {
    List<AplikimetDTO> getAplikimet();
}
