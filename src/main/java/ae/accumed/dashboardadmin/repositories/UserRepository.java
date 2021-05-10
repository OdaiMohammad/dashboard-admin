package ae.accumed.dashboardadmin.repositories;

import ae.accumed.dashboardadmin.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
