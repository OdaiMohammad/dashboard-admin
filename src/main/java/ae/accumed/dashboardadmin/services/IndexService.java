package ae.accumed.dashboardadmin.services;

import ae.accumed.dashboardadmin.entities.Facility;
import ae.accumed.dashboardadmin.entities.User;
import ae.accumed.dashboardadmin.entities.UserDashboard;
import ae.accumed.dashboardadmin.model.IndexResponse;
import ae.accumed.dashboardadmin.model.UserResponse;
import ae.accumed.dashboardadmin.repositories.FacilityRepository;
import ae.accumed.dashboardadmin.repositories.UserDashboardRepository;
import ae.accumed.dashboardadmin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexService {
    private final UserRepository userRepository;
    private final FacilityRepository facilityRepository;
    private final UserDashboardRepository userDashboardRepository;

    @Autowired
    public IndexService(UserRepository userRepository, FacilityRepository facilityRepository, UserDashboardRepository userDashboardRepository) {
        this.userRepository = userRepository;
        this.facilityRepository = facilityRepository;
        this.userDashboardRepository = userDashboardRepository;
    }


    public IndexResponse getIndexData() {
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
        ArrayList<Facility> facilities = (ArrayList<Facility>) facilityRepository.findAll();
        ArrayList<UserResponse> userResponses = (ArrayList<UserResponse>) users.stream().map(user -> new UserResponse(user.getUserName(), user.isActive(), user.getName())).collect(Collectors.toList());
        userResponses.forEach(userResponse -> userResponse.setFacilityLicences(getUserFacilityLicences(userResponse.getUserName())));
        IndexResponse indexResponse = new IndexResponse();
        indexResponse.setFacilities(facilities);
        indexResponse.setUserResponses(userResponses);
        return indexResponse;
    }

    private ArrayList<String> getUserFacilityLicences(String userName) {
        List<UserDashboard> userDashboards = (List<UserDashboard>) userDashboardRepository.findAllByUserName(userName);
        return (ArrayList<String>) userDashboards.stream().map(UserDashboard::getFacilityLicense).collect(Collectors.toList());
    }
}
