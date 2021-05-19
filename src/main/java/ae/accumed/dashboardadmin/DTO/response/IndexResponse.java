package ae.accumed.dashboardadmin.DTO.response;

import ae.accumed.dashboardadmin.model.Hospital;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class IndexResponse {
    private List<IndexUserResponse> indexUserResponses;
    private List<Hospital> hospitals;
}
