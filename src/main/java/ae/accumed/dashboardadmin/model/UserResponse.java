package ae.accumed.dashboardadmin.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UserResponse {
    private String userName;

    private boolean isActive;

    private String name;

    private ArrayList<String> hospitalIds;

    public UserResponse(String userName, boolean isActive, String name) {
        this.userName = userName;
        this.isActive = isActive;
        if (name.toLowerCase().equals("null"))
            this.name = userName;
        else
            this.name = name;
    }

    public UserResponse(String userName, boolean isActive, String name, ArrayList<String> hospitalIds) {
        this.userName = userName;
        this.isActive = isActive;
        this.name = name;
        this.hospitalIds = hospitalIds;
    }

}
