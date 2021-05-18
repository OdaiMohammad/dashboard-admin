package ae.accumed.dashboardadmin.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLUSTERING")
@Data
public class Cluster {
    @Id
    @Column(name = "ANONYMIZED_FACILITY_EQUIVALENT")
    private String hospitalId;

    @Column(name = "HOSPITAL_NAME")
    private String hospitalName;

    @Column(name = "HOSPITAL_NAME_ARABIC")
    private boolean hospitalNameArabic;

    @Column(name = "REGION")
    private boolean region;

    @Column(name = "REGION_ARABIC")
    private boolean regionArabic;

    @Column(name = "CLUSTERING")
    private boolean clustering;
}
