package ae.accumed.dashboardadmin.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCUMED_FACILITY")
@Data
public class Facility {
    @Id
    @Column(name = "FACILITY_LICENSE")
    private String facilityLicence;

    @Column(name = "FACILITY_NAME")
    private String facilityName;

    @Column(name = "IsDeleted")
    private boolean isDeleted;
}
