package ae.accumed.dashboardadmin.DTO.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EditAccessByClusterRequest {
    @NotNull(message = "Please select a user")
    private String userName;

    @NotNull(message = "Please select a region")
    private String region;

    @NotNull(message = "Please select a cluster")
    private String clustering;
}
