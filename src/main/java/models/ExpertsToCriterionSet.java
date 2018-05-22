package models;

import models.helpers.ExpertsToCriterionSetPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "experts_to_criterion_set", schema = "public", catalog = "rating_bd")
@AssociationOverrides({
        @AssociationOverride(name = "PK.criterionsSet", joinColumns = @JoinColumn(name = "id_criterion_set")),
        @AssociationOverride(name = "PK.expert", joinColumns = @JoinColumn(name = "id_expert"))
})
public class ExpertsToCriterionSet implements Serializable {
    ExpertsToCriterionSetPK pk = new ExpertsToCriterionSetPK();

    public ExpertsToCriterionSet() {
    }

    public ExpertsToCriterionSet(CriterionsSet criterionsSet, Expert expert) {
        pk.setCriterionsSet(criterionsSet);
        pk.setExpert(expert);
    }

    @EmbeddedId
    public ExpertsToCriterionSetPK getPK() {
        return pk;
    }

    public void setPK(ExpertsToCriterionSetPK pk) {
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

        ExpertsToCriterionSet that = (ExpertsToCriterionSet) o;

        if (((ExpertsToCriterionSet) o).pk != that.pk) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pk.hashCode();
        return result;
    }
}
