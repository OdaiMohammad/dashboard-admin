package ae.accumed.dashboardadmin.repositories;

import ae.accumed.dashboardadmin.entities.Cluster;
import org.springframework.data.repository.CrudRepository;

public interface ClusterRepository extends CrudRepository<Cluster, String> {
}
