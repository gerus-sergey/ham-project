package models.helpers;

import models.Alternative;
import models.AlternativesSet;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class AlternativesToAlternativeSetPK implements Serializable {
    private Alternative alternative;
    private AlternativesSet alternativesSet;

    public AlternativesToAlternativeSetPK(Alternative alternative, AlternativesSet alternativesSet) {
        this.alternative = alternative;
        this.alternativesSet = alternativesSet;
    }

    public AlternativesToAlternativeSetPK() {
    }

    @ManyToOne
    public Alternative getAlternative() {
        return alternative;
    }

    public void setAlternative(Alternative alternative) {
        this.alternative = alternative;
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

        AlternativesToAlternativeSetPK that = (AlternativesToAlternativeSetPK) o;

        if (alternativesSet != null ? !alternativesSet.equals(that.alternativesSet) : that.alternativesSet != null)
            return false;
        return alternative != null ? alternative.equals(that.alternative) : that.alternative == null;
    }

    @Override
    public int hashCode() {
        int result = alternativesSet != null ? alternativesSet.hashCode() : 0;
        result = 31 * result + (alternative != null ? alternative.hashCode() : 0);
        return result;
    }
}
