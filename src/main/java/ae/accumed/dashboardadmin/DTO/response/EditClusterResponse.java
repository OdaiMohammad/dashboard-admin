package ae.accumed.dashboardadmin.DTO.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EditClusterResponse {
    private List<EditClusterUserResponse> users;
    private List<ClusterResponse> clusterResponses;
}
