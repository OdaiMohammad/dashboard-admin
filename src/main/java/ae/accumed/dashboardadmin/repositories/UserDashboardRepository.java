package ae.accumed.dashboardadmin.repositories;

import ae.accumed.dashboardadmin.entities.UserDashboard;
import org.springframework.data.repository.CrudRepository;

public interface UserDashboardRepository extends CrudRepository<UserDashboard, Integer> {
    Iterable<UserDashboard> findAllByUserName(String userName);

    void deleteAllByUserName(String userName);
}
