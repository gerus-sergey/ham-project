package models.helpers;

import models.CriterionsSet;
import models.Expert;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ExpertsToCriterionSetPK implements Serializable {
    private CriterionsSet criterionsSet;
    private Expert expert;

    public ExpertsToCriterionSetPK(CriterionsSet criterionsSet, Expert expert) {
        this.criterionsSet = criterionsSet;
        this.expert = expert;
    }

    public ExpertsToCriterionSetPK(){}

    @ManyToOne
    public CriterionsSet getCriterionsSet() {
        return criterionsSet;
    }

    public void setCriterionsSet(CriterionsSet criterionsSet) {
        this.criterionsSet = criterionsSet;
    }

    @ManyToOne
    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpertsToCriterionSetPK that = (ExpertsToCriterionSetPK) o;

        if (criterionsSet != null ? !criterionsSet.equals(that.criterionsSet) : that.criterionsSet != null)
            return false;
        return expert != null ? expert.equals(that.expert) : that.expert == null;
    }

    @Override
    public int hashCode() {
        int result = criterionsSet != null ? criterionsSet.hashCode() : 0;
        result = 31 * result + (expert != null ? expert.hashCode() : 0);
        return result;
    }
}
