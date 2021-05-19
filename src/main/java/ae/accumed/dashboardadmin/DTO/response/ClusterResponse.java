package ae.accumed.dashboardadmin.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClusterResponse {
    private String region;
    private String regionArabic;

    private List<String> clusters;

}
