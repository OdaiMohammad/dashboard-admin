package ae.accumed.dashboardadmin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class IndexResponse {
    private List<UserResponse> userResponses;
    private List<Hospital> hospitals;
}
