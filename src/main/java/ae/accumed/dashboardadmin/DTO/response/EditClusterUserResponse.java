package ae.accumed.dashboardadmin.DTO.response;

import lombok.Data;

@Data
public class EditClusterUserResponse {
    private String userName;

    private boolean isActive;

    private String name;

    public EditClusterUserResponse(String userName, boolean isActive, String name) {
        this.userName = userName;
        this.isActive = isActive;
        if (name.toLowerCase().equals("null"))
            this.name = userName;
        else
            this.name = name;
    }

}
