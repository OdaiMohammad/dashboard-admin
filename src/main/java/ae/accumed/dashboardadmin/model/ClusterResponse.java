package ae.accumed.dashboardadmin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ClusterResponse {
    private String region;
    private String regionArabic;

    private List<String> clusters;

}
