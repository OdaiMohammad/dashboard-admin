package ae.accumed.dashboardadmin.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SSRSUser")
@Data
public class User {
    @Id
    @Column(name = "UserName")
    private String userName;

    @Column(name = "IsActive")
    private boolean isActive;

    @Column(name = "Name")
    private String name;
}
