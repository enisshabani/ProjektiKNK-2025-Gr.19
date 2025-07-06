package Service;

import Model.DamageReport;
import Repository.DamageReportRepository;

import java.time.LocalDate;
import java.util.List;

public class DamageReportService {
    private final DamageReportRepository repository;

    public DamageReportService(DamageReportRepository repository) {
        this.repository = repository;
    }

    public void createDamageReport(String description, String reporter) {
        DamageReport report = new DamageReport(
                0,
                description,
                LocalDate.now(),
                reporter,
                DamageReport.Status.Ne_Pritje);
        repository.save(report);
    }

    public DamageReport getReportById(int id) {
        return repository.findById(id);
    }

    public List<DamageReport> getAllReports() {
        return repository.findAll();
    }

    public List<DamageReport> getReportsByReporter(String reporter) {
        return repository.findByReporter(reporter);
    }

    public void markReportAsRepaired(int id) {
        repository.updateStatus(id, DamageReport.Status.E_Riparuar);
    }
}
