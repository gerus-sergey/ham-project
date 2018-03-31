package models;


public class RatingAlternatives {
    private Integer id;
    private Integer dimensionId;
    private Criterion alternativeId;
    private Double rating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(Integer dimensionId) {
        this.dimensionId = dimensionId;
    }

    public Criterion getAlternativeId() {
        return alternativeId;
    }

    public void setAlternativeId(Criterion alternativeId) {
        this.alternativeId = alternativeId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
