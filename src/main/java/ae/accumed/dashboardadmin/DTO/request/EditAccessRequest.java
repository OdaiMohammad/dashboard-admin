package ae.accumed.dashboardadmin.DTO.request;

import lombok.Data;

import java.util.List;

@Data
public class EditAccessRequest {
    private String userName;

    private List<String> hospitalIds;
}
