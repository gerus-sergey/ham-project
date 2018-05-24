package models;

import javax.persistence.*;

@Entity
@Table(name = "alternatives_set", schema = "public", catalog = "rating_bd")
public class AlternativesSet {
    private Integer id;
    private String name;
    private String comment;

    public AlternativesSet() {
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alternatives_set_seq")
    @SequenceGenerator(name = "alternatives_set_seq", sequenceName = "alternatives_set_id_seq", allocationSize = 1)
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
