package Repository;

import Model.DamageReport;
import java.util.List;

public interface DamageReportRepository {
    void save(DamageReport damageReport);

    DamageReport findById(int id);

    List<DamageReport> findAll();

    List<DamageReport> findByReporter(String reporter);

    void updateStatus(int id, DamageReport.Status status);
}
