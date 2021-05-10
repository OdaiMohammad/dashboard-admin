package ae.accumed.dashboardadmin.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UserResponse {
    private String userName;

    private boolean isActive;

    private String name;

    private ArrayList<String> facilityLicences;

    public UserResponse(String userName, boolean isActive, String name) {
        this.userName = userName;
        this.isActive = isActive;
        if (name.toLowerCase().equals("null"))
            this.name = userName;
        else
            this.name = name;
    }
}
