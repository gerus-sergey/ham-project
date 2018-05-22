package models;

import models.helpers.CriterionsToCriterionSetPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "criterions_to_criterion_set", schema = "public", catalog = "rating_bd")
@AssociationOverrides({
        @AssociationOverride(name = "PK.criterionsSet", joinColumns = @JoinColumn(name = "id_criterion_set")),
        @AssociationOverride(name = "PK.criterion", joinColumns = @JoinColumn(name = "id_criterion"))
})
public class CriterionsToCriterionSet implements Serializable {
    CriterionsToCriterionSetPK pk = new CriterionsToCriterionSetPK();

    public CriterionsToCriterionSet() {
    }

    public CriterionsToCriterionSet(CriterionsSet criterionsSet, Criterion criterion) {
        pk.setCriterionsSet(criterionsSet);
        pk.setCriterion(criterion);
    }

    @EmbeddedId
    public CriterionsToCriterionSetPK getPK() {
        return pk;
    }

    public void setPK(CriterionsToCriterionSetPK pk) {
        this.pk = pk;
    }

    @Transient
    public CriterionsSet getCriterionSet() {
        return getPK().getCriterionsSet();
    }

    public void setCriterionSet(CriterionsSet criterionSet) {
        getPK().setCriterionsSet(criterionSet);
    }

    @Transient
    public Criterion getCriterion() {
        return getPK().getCriterion();
    }

    public void setCriterion(Criterion criterion) {
        getPK().setCriterion(criterion);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriterionsToCriterionSet that = (CriterionsToCriterionSet) o;

        if (((CriterionsToCriterionSet) o).pk != that.pk) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pk.hashCode();
        return result;
    }
}
