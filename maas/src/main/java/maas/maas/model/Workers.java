package maas.maas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "workers")
public class Workers {
    @Id
    @Column(name = "worker_id")
    private Long id;

    @Column(name = "worker_name")
    private String username;

    @Column(name = "security_level")
    private Long email;
}
