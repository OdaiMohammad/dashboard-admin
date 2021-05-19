package ae.accumed.dashboardadmin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {

    private String hospitalId;

    private String hospitalName;

    private String hospitalNameArabic;
}
