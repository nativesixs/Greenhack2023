package maas.maas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chairs")
public class Chairs {

    @Id
    @Column(name = "chair_id")
    private Long id;

    @Column(name = "is_free")
    private String isFree;

    @Column(name = "has_monitor")
    private String hasMonitor;
    @Column(name = "is_openspace")
    private String isOpenSpace;
}
