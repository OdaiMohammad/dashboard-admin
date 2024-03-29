package ae.accumed.dashboardadmin.services;

import ae.accumed.dashboardadmin.DTO.request.EditAccessByClusterRequest;
import ae.accumed.dashboardadmin.DTO.response.ClusterResponse;
import ae.accumed.dashboardadmin.DTO.response.EditClusterResponse;
import ae.accumed.dashboardadmin.DTO.response.EditClusterUserResponse;
import ae.accumed.dashboardadmin.entities.Cluster;
import ae.accumed.dashboardadmin.entities.User;
import ae.accumed.dashboardadmin.entities.UserDashboard;
import ae.accumed.dashboardadmin.repositories.ClusterRepository;
import ae.accumed.dashboardadmin.repositories.UserDashboardRepository;
import ae.accumed.dashboardadmin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class EditAccessByClusterService {
    private final UserRepository userRepository;
    private final ClusterRepository clusterRepository;
    private final UserDashboardRepository userDashboardRepository;

    @Autowired
    public EditAccessByClusterService(UserRepository userRepository, ClusterRepository clusterRepository, UserDashboardRepository userDashboardRepository) {
        this.userRepository = userRepository;
        this.clusterRepository = clusterRepository;
        this.userDashboardRepository = userDashboardRepository;
    }

    public EditClusterResponse getEditClusterData() {
        EditClusterResponse editClusterResponse = new EditClusterResponse();
        List<User> users = (List<User>) userRepository.findAll();
        editClusterResponse.setUsers(users.stream().map(user -> new EditClusterUserResponse(user.getUserName(), user.isActive(), user.getName())).collect(Collectors.toList()));
        List<Cluster> clusters = (List<Cluster>) clusterRepository.findAll();
        List<Cluster> uniqueClusters = clusters.stream().filter(distinctByKey(Cluster::getRegion)).collect(Collectors.toList());
        List<ClusterResponse> clusterResponses = uniqueClusters.stream().map(cluster -> new ClusterResponse(cluster.getRegion(), cluster.getRegionArabic(), getClusters(clusters, cluster))).collect(Collectors.toList());
        editClusterResponse.setClusterResponses(clusterResponses);
        return editClusterResponse;
    }

    private List<String> getClusters(List<Cluster> clusters, Cluster cluster) {
        List<Cluster> regionClusters = clusters.stream().filter(filterCluster -> filterCluster.getRegion().equals(cluster.getRegion())).collect(Collectors.toList());
        return regionClusters.stream().filter(distinctByKey(Cluster::getClustering)).map(Cluster::getClustering).collect(Collectors.toList());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    @Transactional
    public void saveUserDashboard(EditAccessByClusterRequest editAccessByClusterRequest) {
        Optional<User> userOptional = userRepository.findById(editAccessByClusterRequest.getUserName());
        if (userOptional.isPresent()) {
            List<Cluster> clusters = new ArrayList<>();
            if (editAccessByClusterRequest.getClustering() != null) {
                 clusters.addAll((List<Cluster>) clusterRepository.findAllByClustering(editAccessByClusterRequest.getClustering()));
            } else {
                clusters.addAll((List<Cluster>) clusterRepository.findAllByRegion(editAccessByClusterRequest.getRegion()));
            }
            List<UserDashboard> userDashboards = clusters.stream().map(cluster -> new UserDashboard(
                    editAccessByClusterRequest.getUserName(), cluster.getHospitalName(), cluster.getHospitalId()
            )).collect(Collectors.toList());
            userDashboardRepository.saveAll(userDashboards);
        }
    }
}
