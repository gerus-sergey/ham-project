package models.helpers;

import models.AlternativesSet;
import models.Expert;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ExpertsToAlternativeSetPK implements Serializable {
    private Expert expert;
    private AlternativesSet alternativesSet;

    public ExpertsToAlternativeSetPK(Expert expert, AlternativesSet alternativesSet) {
        this.expert = expert;
        this.alternativesSet = alternativesSet;
    }

    public ExpertsToAlternativeSetPK() {
    }

    @ManyToOne
    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    @ManyToOne
    public AlternativesSet getAlternativesSet() {
        return alternativesSet;
    }

    public void setAlternativesSet(AlternativesSet alternativesSet) {
        this.alternativesSet = alternativesSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpertsToAlternativeSetPK that = (ExpertsToAlternativeSetPK) o;

        if (alternativesSet != null ? !alternativesSet.equals(that.alternativesSet) : that.alternativesSet != null)
            return false;
        return expert != null ? expert.equals(that.expert) : that.expert == null;
    }

    @Override
    public int hashCode() {
        int result = alternativesSet != null ? alternativesSet.hashCode() : 0;
        result = 31 * result + (expert != null ? expert.hashCode() : 0);
        return result;
    }
}
