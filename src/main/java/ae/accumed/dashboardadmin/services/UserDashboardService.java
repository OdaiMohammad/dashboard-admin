package ae.accumed.dashboardadmin.services;

import ae.accumed.dashboardadmin.entities.Cluster;
import ae.accumed.dashboardadmin.model.Hospital;
import ae.accumed.dashboardadmin.entities.User;
import ae.accumed.dashboardadmin.entities.UserDashboard;
import ae.accumed.dashboardadmin.model.IndexResponse;
import ae.accumed.dashboardadmin.model.EditAccessSubmission;
import ae.accumed.dashboardadmin.model.UserResponse;
import ae.accumed.dashboardadmin.repositories.ClusterRepository;
import ae.accumed.dashboardadmin.repositories.UserDashboardRepository;
import ae.accumed.dashboardadmin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDashboardService {
    private final UserRepository userRepository;
    private final ClusterRepository clusterRepository;
    private final UserDashboardRepository userDashboardRepository;

    @Autowired
    public UserDashboardService(UserRepository userRepository, ClusterRepository clusterRepository, UserDashboardRepository userDashboardRepository) {
        this.userRepository = userRepository;
        this.clusterRepository = clusterRepository;
        this.userDashboardRepository = userDashboardRepository;
    }

    @Transactional
    public void saveUserDashboard(EditAccessSubmission editAccessSubmission) {
        Optional<User> userOptional = userRepository.findById(editAccessSubmission.getUserName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (editAccessSubmission.getHospitalIds() == null)
                userDashboardRepository.deleteAllByUserName(user.getUserName());
            else {
                List<Cluster> userFacilities = (List<Cluster>) clusterRepository.findAllById(editAccessSubmission.getHospitalIds());
                userDashboardRepository.deleteAllByUserName(user.getUserName());
                List<UserDashboard> userDashboards = userFacilities.stream()
                        .map(userHospital ->
                                new UserDashboard(
                                        user.getUserName(),
                                        userHospital.getHospitalName(),
                                        userHospital.getHospitalId()))
                        .collect(Collectors.toList());
                userDashboardRepository.saveAll(userDashboards);
            }
        }
    }

    public IndexResponse getEditData() {
        List<User> users = (List<User>) userRepository.findAll();
        List<Cluster> clusters = (List<Cluster>) clusterRepository.findAll();
        List<UserResponse> userResponses = users.stream().map(user -> new UserResponse(user.getUserName(), user.isActive(), user.getName())).collect(Collectors.toList());
        userResponses.forEach(userResponse -> userResponse.setHospitalIds(getUserFacilityLicences(userResponse.getUserName())));
        IndexResponse indexResponse = new IndexResponse();
        indexResponse.setHospitals(clusters.stream().map(cluster -> new Hospital(cluster.getHospitalId(), cluster.getHospitalName())).collect(Collectors.toList()));
        indexResponse.setUserResponses(userResponses);
        return indexResponse;
    }

    private ArrayList<String> getUserFacilityLicences(String userName) {
        List<UserDashboard> userDashboards = (List<UserDashboard>) userDashboardRepository.findAllByUserName(userName);
        return (ArrayList<String>) userDashboards.stream().map(UserDashboard::getFacilityLicense).collect(Collectors.toList());
    }
}
