package controllers;

import models.RatingAlternatives;
import services.impl.RatingAlternativesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class RatingAlternativesController {

    @Autowired
    private RatingAlternativesServiceImpl ratingAlternativesService;

    @PostMapping(value = "/rating-alternatives/{dimensionId}")
    public ResponseEntity calculateRatingAlternatives(@PathVariable("dimensionId") Integer id,
                                                      @RequestHeader("criterionNames") String criterionNames,
                                                      @RequestHeader("criterionWeights") String criterionWeights,
                                                      @RequestHeader("alternativeNames") String alternativeNames,
                                                      @RequestHeader("alternativeWeights") String alternativeWeights) {
        ArrayList<RatingAlternatives> ratingAlternatives = ratingAlternativesService.calculateRatingAlternatives(id, criterionNames, criterionWeights, alternativeNames, alternativeWeights);
        if (ratingAlternatives != null) {
            return new ResponseEntity(ratingAlternatives, HttpStatus.OK);
        } else {
            return new ResponseEntity("Error ", HttpStatus.NOT_FOUND);
        }
    }

}
