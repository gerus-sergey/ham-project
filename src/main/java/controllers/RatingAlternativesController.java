package controllers;

import models.RatingAlternative;
import models.RatingCriterion;
import org.springframework.web.bind.annotation.*;
import services.RatingAlternativesService;
import services.impl.RatingAlternativesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RatingAlternativesController {

    @Autowired
    private RatingAlternativesService ratingAlternativesService;

    @PostMapping(value = "/rating-alternatives/{dimensionId}")
    public ResponseEntity calculateRatingAlternatives(@PathVariable("dimensionId") Integer id,
                                                      @RequestBody ArrayList<RatingAlternative> ratingAlternatives) {
        ArrayList<RatingAlternative> ratingAlternativesResult = ratingAlternativesService.calculateRatingAlternatives(id, ratingAlternatives);
        if (ratingAlternativesResult != null) {
            return new ResponseEntity(ratingAlternativesResult, HttpStatus.OK);
        } else {
            return new ResponseEntity("Error ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/rating-alternatives")
    public List getRatingAlternatives() {
        return ratingAlternativesService.getAll();
    }

    @GetMapping(path = "/rating-alternatives/{id}")
    public ResponseEntity getRatingAlternative(@PathVariable("id") Integer id) {
        RatingAlternative ratingAlternative = ratingAlternativesService.get(id);
        if (ratingAlternative == null) {
            return new ResponseEntity("No Alternative found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(ratingAlternative, HttpStatus.OK);
    }

    @PostMapping(value = "/rating-alternatives")
    public ResponseEntity createRatingAlternative(@RequestBody RatingAlternative ratingAlternative) {
        ratingAlternativesService.addOrUpdate(ratingAlternative);
        return new ResponseEntity(ratingAlternative, HttpStatus.OK);
    }

    @DeleteMapping("/rating-alternatives/{id}")
    public ResponseEntity deleteRatingAlternative(@PathVariable("id") Integer id) {
        if (ratingAlternativesService.get(id) == null) {
            return new ResponseEntity("No Alternative found for ID" + id, HttpStatus.NOT_FOUND);
        }
        ratingAlternativesService.delete(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @GetMapping(path = "/rating-alternatives/dimension/{dimensionId}")
    public ResponseEntity getRatingAlternativeByDimensionId(@PathVariable("dimensionId") Integer dimensionId) {
        ArrayList<RatingAlternative> ratingAlternatives = ratingAlternativesService.getRatingAlternativeByDimensionId(dimensionId);
        if (ratingAlternatives == null) {
            return new ResponseEntity("No rating-alternatives found for dimensionId " + dimensionId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(ratingAlternatives, HttpStatus.OK);
    }
}
