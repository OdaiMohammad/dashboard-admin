package ae.accumed.dashboardadmin.model;

import lombok.Data;

import java.util.List;

@Data
public class EditAccessSubmission {
    private String userName;

    private List<String> hospitalIds;
}
