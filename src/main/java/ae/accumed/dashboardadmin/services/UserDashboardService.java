package ae.accumed.dashboardadmin.services;

import ae.accumed.dashboardadmin.entities.Facility;
import ae.accumed.dashboardadmin.entities.User;
import ae.accumed.dashboardadmin.entities.UserDashboard;
import ae.accumed.dashboardadmin.model.Submission;
import ae.accumed.dashboardadmin.repositories.FacilityRepository;
import ae.accumed.dashboardadmin.repositories.UserDashboardRepository;
import ae.accumed.dashboardadmin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDashboardService {
    private final UserRepository userRepository;
    private final FacilityRepository facilityRepository;
    private final UserDashboardRepository userDashboardRepository;

    @Autowired
    public UserDashboardService(UserRepository userRepository, FacilityRepository facilityRepository, UserDashboardRepository userDashboardRepository) {
        this.userRepository = userRepository;
        this.facilityRepository = facilityRepository;
        this.userDashboardRepository = userDashboardRepository;
    }

    @Transactional
    public void saveUserDashboard(Submission submission) {
        Optional<User> userOptional = userRepository.findById(submission.getUserName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (submission.getFacilityLicences() == null)
                userDashboardRepository.deleteAllByUserName(user.getUserName());
            else {
                List<Facility> userFacilities = (List<Facility>) facilityRepository.findAllById(submission.getFacilityLicences());
                userDashboardRepository.deleteAllByUserName(user.getUserName());
                List<UserDashboard> userDashboards = userFacilities.stream()
                        .map(userFacility ->
                                new UserDashboard(
                                        user.getUserName(),
                                        userFacility.getFacilityName(),
                                        userFacility.getFacilityLicence()))
                        .collect(Collectors.toList());
                userDashboardRepository.saveAll(userDashboards);
            }
        }
    }
}
