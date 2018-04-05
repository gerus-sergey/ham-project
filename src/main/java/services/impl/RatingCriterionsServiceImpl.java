package services.impl;

import models.RatingCriterion;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.RatingCriterionRepository;
import services.RatingCriterionsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatingCriterionsServiceImpl implements RatingCriterionsService {

    @Override
    public ArrayList<RatingCriterion> calculateRatingCriterions(Integer dimensionId, ArrayList<RatingCriterion> ratingCriteria) {

          String a = ratingCriteria.get(1).getWeights();
//        if (!criterionNames.isEmpty() || !criterionWeights.isEmpty()) {
//            String nameCriterion[] = criterionNames.split(",");
//            ArrayList<RatingCriterion> ratingCriteria = new ArrayList<RatingCriterion>();
//            Double weight = 1.0;
//            int m = 0;
//            Double geometricMean[] = new Double[nameCriterion.length];
//
//            if (nameCriterion != null) {
//                String weightCriterionArray[] = criterionWeights.split(",");
//                Double weightCriterion[][] = new Double[nameCriterion.length][nameCriterion.length];
//                int countCriterion = 0;
//                Double sumGeometric = 0.0;
//                Double normalizedWeight[] = new Double[nameCriterion.length];
//                for (int i = 0; i < nameCriterion.length; i++) {
//                    for (int j = 0; j < nameCriterion.length; j++) {
//                        weightCriterion[i][j] = Double.valueOf(weightCriterionArray[countCriterion]);
//                        countCriterion++;
//                    }
//                }
//
//                for (int i = 0; i < weightCriterion.length; i++) {
//                    for (int j = 0; j < weightCriterion.length + 1; j++) {
//                        if (j != weightCriterion.length) {
//                            weight *= weightCriterion[i][j];
//                        } else {
//                            Double degree = (double) 1 / nameCriterion.length;
//                            Double rank = Math.pow(weight, degree);
//                            geometricMean[m] = rank;
//                            m++;
//                            weight = 1.0;
//                        }
//                    }
//                }
//
//                for (Double aGeometricMean : geometricMean) {
//                    sumGeometric += aGeometricMean;
//                }
//
//                for (int b = 0; b < geometricMean.length; b++) {
//                    Criterion criterion = new Criterion();
//                    criterion.setCriterionName(nameCriterion[b]);
//                    RatingCriterion ratingCriterion = new RatingCriterion();
//                    ratingCriterion.setCriterion(criterion);
//                    ratingCriterion.setDimensionId(dimensionId);
//                    ratingCriterion.setRating(geometricMean[b] / sumGeometric);
//                    ratingCriteria.add(b, ratingCriterion);
//                }
//                return ratingCriteria;
//            }
//        }
        return null;
    }

    @Autowired
    private RatingCriterionRepository ratingCriterionRepository;

    @Transactional
    public RatingCriterion addOrUpdate(RatingCriterion obj) {
        return ratingCriterionRepository.saveAndFlush(obj);
    }

    @Transactional
    public List<RatingCriterion> getAll() {
        return ratingCriterionRepository.findAll();
    }

    @Transactional
    public void delete(Integer id) {
        ratingCriterionRepository.delete(id);
    }

    @Transactional
    public RatingCriterion get(Integer id) {
        return ratingCriterionRepository.findOne(id);
    }
}