package models;

import models.helpers.ExpertsToAlternativeSetPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "experts_to_alternative_set", schema = "public", catalog = "rating_bd")
@AssociationOverrides({
        @AssociationOverride(name = "PK.alternativesSet", joinColumns = @JoinColumn(name = "id_alternative_set")),
        @AssociationOverride(name = "PK.expert", joinColumns = @JoinColumn(name = "id_expert"))
})
public class ExpertsToAlternativeSet implements Serializable {
    ExpertsToAlternativeSetPK pk = new ExpertsToAlternativeSetPK();

    public ExpertsToAlternativeSet() {
    }

    public ExpertsToAlternativeSet(AlternativesSet alternativesSet, Expert expert) {
        this.pk.setAlternativesSet(alternativesSet);
        this.pk.setExpert(expert);
    }

    @EmbeddedId
    public ExpertsToAlternativeSetPK getPK() {
        return pk;
    }

    public void setPK(ExpertsToAlternativeSetPK pk) {
        this.pk = pk;
    }

    @Transient
    public AlternativesSet getAlternativesSet() {
        return getPK().getAlternativesSet();
    }

    public void setAlternativesSet(AlternativesSet alternativesSet) {
        getPK().setAlternativesSet(alternativesSet);
    }

    @Transient
    public Expert getExpert() {
        return getPK().getExpert();
    }

    public void setExpert(Expert expert) {
        getPK().setExpert(expert);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpertsToAlternativeSet that = (ExpertsToAlternativeSet) o;

        if (((ExpertsToAlternativeSet) o).pk != that.pk) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pk.hashCode();
        return result;
    }
}
