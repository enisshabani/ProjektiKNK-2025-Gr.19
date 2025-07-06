package Service;

import java.util.List;
import java.util.stream.Collectors;
import Model.DTO.AplikimetDTO;
import Repository.AplikimetRepository;
import Repository.AplikimetRepositoryImpl;

public class AplikimetService {
    private final AplikimetRepository aplikimetRepository;

    public AplikimetService() {
        this.aplikimetRepository = new AplikimetRepositoryImpl();
    }

    public List<AplikimetDTO> getAllAplikimet() {
        return aplikimetRepository.getAllAplikimet();
    }
public void updateAplikimStatus(int aplikimId, String newStatus) {
        aplikimetRepository.updateAplikimStatus(aplikimId, newStatus);
    }

    public List<AplikimetDTO> filterAplikimet(String searchText, String statusFilter) {
        List<AplikimetDTO> allAplikimet = getAllAplikimet();
        return allAplikimet.stream()
                .filter(app -> (searchText.isEmpty() || app.getStudentName().toLowerCase().contains(searchText.toLowerCase()))
                        && (statusFilter.equals("Të gjitha") || app.getStatus().equals(statusFilter)))
                .collect(Collectors.toList());
    }
}
