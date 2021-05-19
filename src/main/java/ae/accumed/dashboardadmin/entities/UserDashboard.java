package ae.accumed.dashboardadmin.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "User_Dashboard")
@Data
@NoArgsConstructor
public class UserDashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Username")
    private String userName;

    @Column(name = "Facility_Name")
    private String facilityName;

    @Column(name = "Facility_License")
    private String facilityLicense;

    public UserDashboard(String userName, String facilityName, String facilityLicense) {
        this.userName = userName;
        this.facilityName = facilityName;
        this.facilityLicense = facilityLicense;
    }
}
