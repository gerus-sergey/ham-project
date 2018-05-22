package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "criterions_set", schema = "public", catalog = "rating_bd")
public class CriterionsSet implements Serializable {
    private Integer id;
    private String name;
    private String comment;

    public CriterionsSet(){}

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "criterions_set_seq")
    @SequenceGenerator(name = "criterions_set_seq", sequenceName = "criterions_set_id_seq", allocationSize = 1)
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

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
