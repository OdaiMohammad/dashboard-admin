package ae.accumed.dashboardadmin.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EditClusterSubmission {
    @NotNull(message = "Please select a user")
    private String userName;

    @NotNull(message = "Please select a region")
    private String region;

    @NotNull(message = "Please select a cluster")
    private String clustering;
}
