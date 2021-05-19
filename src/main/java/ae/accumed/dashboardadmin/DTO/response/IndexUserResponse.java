package ae.accumed.dashboardadmin.DTO.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class IndexUserResponse {
    private String userName;

    private boolean isActive;

    private String name;

    private ArrayList<String> hospitalIds;

    public IndexUserResponse(String userName, boolean isActive, String name) {
        this.userName = userName;
        this.isActive = isActive;
        if (name.toLowerCase().equals("null"))
            this.name = userName;
        else
            this.name = name;
    }

    public IndexUserResponse(String userName, boolean isActive, String name, ArrayList<String> hospitalIds) {
        this.userName = userName;
        this.isActive = isActive;
        this.name = name;
        this.hospitalIds = hospitalIds;
    }

}
