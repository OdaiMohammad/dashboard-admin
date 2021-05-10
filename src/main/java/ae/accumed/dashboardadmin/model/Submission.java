package ae.accumed.dashboardadmin.model;

import lombok.Data;

import java.util.List;

@Data
public class Submission {
    private String userName;

    private List<String> facilityLicences;
}
