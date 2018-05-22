package models.helpers;

import models.Criterion;
import models.CriterionsSet;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class CriterionsToCriterionSetPK  implements Serializable {
    private CriterionsSet criterionsSet;
    private Criterion criterion;

    public CriterionsToCriterionSetPK(CriterionsSet criterionsSet, Criterion criterion) {
        this.criterionsSet = criterionsSet;
        this.criterion = criterion;
    }

    public CriterionsToCriterionSetPK(){}

    @ManyToOne
    public CriterionsSet getCriterionsSet() {
        return criterionsSet;
    }

    public void setCriterionsSet(CriterionsSet criterionsSet) {
        this.criterionsSet = criterionsSet;
    }

    @ManyToOne
    public Criterion getCriterion() {
        return criterion;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CriterionsToCriterionSetPK that = (CriterionsToCriterionSetPK) o;

        if (criterionsSet != null ? !criterionsSet.equals(that.criterionsSet) : that.criterionsSet != null)
            return false;
        return criterion != null ? criterion.equals(that.criterion) : that.criterion == null;
    }

    @Override
    public int hashCode() {
        int result = criterionsSet != null ? criterionsSet.hashCode() : 0;
        result = 31 * result + (criterion != null ? criterion.hashCode() : 0);
        return result;
    }
}
