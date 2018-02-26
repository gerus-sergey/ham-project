package models;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "criterions", schema = "public", catalog = "rating_bd")
public class Criterion implements Serializable {
    private Integer id;
    private String criterionName;
    private String description;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "criterions_seq")
    @SequenceGenerator(name = "criterions_seq", sequenceName = "criterions_id_seq", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getCriterionName() {
        return criterionName;
    }

    public void setCriterionName(String criterionName) {
        this.criterionName = criterionName;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Criterion{" +
                "id=" + id +
                ", criterionName='" + criterionName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
