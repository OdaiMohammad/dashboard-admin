package ae.accumed.dashboardadmin.services;

import ae.accumed.dashboardadmin.DTO.response.IndexResponse;
import ae.accumed.dashboardadmin.DTO.response.IndexUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class ListAccessService {
    private final UserDashboardService userDashboardService;

    @Autowired
    public ListAccessService(UserDashboardService userDashboardService) {
        this.userDashboardService = userDashboardService;
    }

    public IndexResponse getIndexData() {
        IndexResponse indexResponse = userDashboardService.getIndexResponse();
        ArrayList<IndexUserResponse> filteredIndexUserResponses = (ArrayList<IndexUserResponse>) indexResponse.getIndexUserResponses().stream().filter(user -> !user.getHospitalIds().isEmpty()).collect(Collectors.toList());
        indexResponse.setIndexUserResponses(filteredIndexUserResponses);
        return indexResponse;
    }
}
