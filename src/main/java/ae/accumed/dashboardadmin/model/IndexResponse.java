package ae.accumed.dashboardadmin.model;

import ae.accumed.dashboardadmin.entities.Facility;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Optional;

@Data
@NoArgsConstructor
public class IndexResponse {
    private ArrayList<UserResponse> userResponses;
    private ArrayList<Facility> facilities;
}
