package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles", schema = "public", catalog = "rating_bd")
public class Roles implements Serializable {
    private Integer id;
    private String name;

    public Roles(){
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_seq")
    @SequenceGenerator(name = "roles_seq", sequenceName = "roles_id_seq", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
