package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ratingalternatives", schema = "public", catalog = "rating_bd")
public class RatingAlternative implements Serializable {
    private Integer id;
    private Integer dimensionId;
    private Alternative alternative;
    private Double rating;
    private String weights;

    public RatingAlternative(){}

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ratingalternatives_seq")
    @SequenceGenerator(name = "ratingalternatives_seq", sequenceName = "ratingalternatives_id_seq", allocationSize = 1)
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
    @JoinColumn(name = "id_alternative", referencedColumnName = "id", nullable = false)
    public Alternative getAlternative() {
        return alternative;
    }

    public void setAlternative(Alternative alternative) {
        this.alternative = alternative;
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
