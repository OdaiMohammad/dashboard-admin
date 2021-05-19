package ae.accumed.dashboardadmin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EditClusterResponse {
    private List<EditClusterUserResponse> users;
    private List<ClusterResponse> clusterResponses;
}
