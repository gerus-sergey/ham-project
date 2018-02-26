package controllers;

import models.RatingCriterions;
import services.impl.RatingCriterionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class RatingCriterionsController {

    @Autowired
    private RatingCriterionsServiceImpl ratingCriterionsService;

    @PostMapping(value = "/rating-criterions/{dimensionId}")
    public ResponseEntity calculateRatingCriterions(@PathVariable("dimensionId") Integer id,
                                                    @RequestHeader("criterionNames") String criterionNames,
                                                    @RequestHeader("criterionWeights") String criterionWeights) {
        ArrayList<RatingCriterions> ratingCriterions = ratingCriterionsService.calculateRatingCriterions(id, criterionNames, criterionWeights);
        if (ratingCriterions != null) {
            return new ResponseEntity(ratingCriterions, HttpStatus.OK);
        } else {
            return new ResponseEntity("Error ", HttpStatus.NOT_FOUND);
        }
    }
}
