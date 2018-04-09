package models;

import javax.persistence.*;

@Entity
@Table(name = "alternatives", schema = "public", catalog = "rating_bd")
public class Alternative {
    private Integer id;
    private String alternativeName;

    public Alternative(){}

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alternatives_seq")
    @SequenceGenerator(name = "alternatives_seq", sequenceName = "alternatives_id_seq", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name_alt")
    public String getAlternativeName() {
        return alternativeName;
    }

    public void setAlternativeName(String alternativeName) {
        this.alternativeName = alternativeName;
    }

    @Override
    public String toString() {
        return "Alternative{" +
                "id=" + id +
                ", alternativeName='" + alternativeName + '\'' +
                '}';
    }
}
