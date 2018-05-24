package models;

import models.helpers.AlternativesToAlternativeSetPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "alternatives_to_alternative_set", schema = "public", catalog = "rating_bd")
@AssociationOverrides({
        @AssociationOverride(name = "PK.alternativesSet", joinColumns = @JoinColumn(name = "id_alternative_set")),
        @AssociationOverride(name = "PK.alternative", joinColumns = @JoinColumn(name = "id_alternative"))
})
public class AlternativesToAlternativeSet implements Serializable {
    AlternativesToAlternativeSetPK pk = new AlternativesToAlternativeSetPK();

    public AlternativesToAlternativeSet() {
    }

    public AlternativesToAlternativeSet(AlternativesSet alternativesSet, Alternative alternative) {
        this.pk.setAlternativesSet(alternativesSet);
        this.pk.setAlternative(alternative);
    }

    @EmbeddedId
    public AlternativesToAlternativeSetPK getPK() {
        return pk;
    }

    public void setPK(AlternativesToAlternativeSetPK pk) {
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
    public Alternative getAlternative() {
        return getPK().getAlternative();
    }

    public void setAlternative(Alternative alternative) {
        getPK().setAlternative(alternative);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlternativesToAlternativeSet that = (AlternativesToAlternativeSet) o;

        if (((AlternativesToAlternativeSet) o).pk != that.pk) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pk.hashCode();
        return result;
    }
}
