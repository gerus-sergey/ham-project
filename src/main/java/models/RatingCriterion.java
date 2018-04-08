package models;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ratingcriterions", schema = "public", catalog = "rating_bd")
public class RatingCriterion implements Serializable {
    private Integer id;
    private Integer dimensionId;
    private Criterion criterion;
    private Double rating;
    private String weights;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ratingcriterions_seq")
    @SequenceGenerator(name = "ratingcriterions_seq", sequenceName = "ratingcriterions_id_seq", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "id_dimensions")
    public Integer getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(Integer dimensionId) {
        this.dimensionId = dimensionId;
    }

    @ManyToOne
    @JoinColumn(name = "id_criterion", referencedColumnName = "id", nullable = false)
    public Criterion getCriterion() {
        return criterion;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }

    @Column(name = "rating")
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getWeights() {
        return weights;
    }

    public void setWeights(String weights) {
        this.weights = weights;
    }
}
