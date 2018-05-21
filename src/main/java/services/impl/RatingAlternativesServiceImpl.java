package services.impl;

import models.RatingAlternative;
import models.RatingCriterion;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.RatingAlternativeRepository;
import repositories.RatingCriterionRepository;
import services.RatingAlternativesService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatingAlternativesServiceImpl implements RatingAlternativesService {

    @Autowired
    private RatingAlternativeRepository ratingAlternativeRepository;

    @Autowired
    private RatingCriterionRepository ratingCriterionRepository;

    @Transactional
    public RatingAlternative addOrUpdate(RatingAlternative obj) {
        return ratingAlternativeRepository.saveAndFlush(obj);
    }

    @Transactional
    public List<RatingAlternative> getAll() {
        return ratingAlternativeRepository.findAll();
    }

    @Transactional
    public void delete(Integer id) {
        ratingAlternativeRepository.delete(id);
    }

    @Transactional
    public RatingAlternative get(Integer id) {
        return ratingAlternativeRepository.findOne(id);
    }

    @Transactional
    public ArrayList<RatingAlternative> getRatingAlternativeByDimensionId(Integer dimensionId){
        return ratingAlternativeRepository.getRatingAlternativeByDimensionId(dimensionId);
    }

    @Override
    public ArrayList<RatingAlternative> calculateRatingAlternatives(Integer dimensionId, ArrayList<RatingAlternative> ratingAlternatives) {

        if (!ratingAlternatives.isEmpty()) {
            ArrayList<RatingCriterion> ratingCriterions = ratingCriterionRepository.getRatingCriterionByDimensionId(dimensionId);
            String weights = "";
            for (RatingAlternative ratingAlternative : ratingAlternatives) {
                weights = weights.concat(ratingAlternative.getWeights().concat(","));
            }
            String[] weightAlternativesArray = weights.split(",");

            int countAlternatives = 0;
            Double weightAlternatives[][] = new Double[ratingAlternatives.size()][ratingCriterions.size()];

            for (int i = 0; i < ratingAlternatives.size(); i++) {
                for (int j = 0; j < ratingCriterions.size(); j++) {
                    weightAlternatives[i][j] = Double.valueOf(weightAlternativesArray[countAlternatives]);
                    countAlternatives++;
                }
            }

            Double rank = 1.0;
            Double geometricMean[] = new Double[ratingAlternatives.size()];
            int m = 0;

            for (int i = 0; i < ratingAlternatives.size(); i++) {
                for (int j = 0; j < ratingCriterions.size() + 1; j++) {
                    if (j != ratingCriterions.size()) {
                        rank *= Math.pow(weightAlternatives[i][j], ratingCriterions.get(j).getRating());
                    } else {
                        geometricMean[m] = rank;
                        m++;
                        rank = 1.0;
                    }
                }
            }

            Double sumGeometric = 0.0;

            for (int a = 0; a < geometricMean.length; a++) {
                sumGeometric += geometricMean[a];
            }

            for (int b = 0; b < geometricMean.length; b++) {
                ratingAlternatives.get(b).setDimensionId(dimensionId);
                ratingAlternatives.get(b).setRating(geometricMean[b] / sumGeometric);
                addOrUpdate(ratingAlternatives.get(b));
            }
            return ratingAlternatives;
        }
        return null;
    }
}