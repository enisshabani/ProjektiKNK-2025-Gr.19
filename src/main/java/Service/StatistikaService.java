package Service;

import Model.DTO.AplikimetDTO;
import Model.DTO.StatistikaDTO;
import Repository.StatistikaRepository;
import Repository.StatistikaRepositoryImpl;
import java.util.List;

public class StatistikaService {

    private final StatistikaRepository statistikaRepository;

    public StatistikaService() {
        this.statistikaRepository = new StatistikaRepositoryImpl();
    }

    public StatistikaDTO getStatistics() {
        List<AplikimetDTO> applications = statistikaRepository.getAplikimet();
        int total = applications.size();
        long approved = applications.stream().filter(a -> "Aprovuar".equals(a.getStatus())).count();
        long pending = applications.stream().filter(a -> "Pritje".equals(a.getStatus())).count();
        long rejected = applications.stream().filter(a -> "Refuzuar".equals(a.getStatus())).count();

        return new StatistikaDTO(total, approved, pending, rejected);
    }
}
