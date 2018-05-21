package controllers;

import models.RatingCriterion;
import org.springframework.web.bind.annotation.*;
import services.RatingCriterionsService;
import services.impl.RatingCriterionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RatingCriterionsController {

    @Autowired
    private RatingCriterionsService ratingCriterionsService;

    @PostMapping(value = "/rating-criterions/{dimensionId}")
    public ResponseEntity calculateRatingCriterions(@PathVariable("dimensionId") Integer id,
                                                    @RequestBody ArrayList<RatingCriterion> ratingCriterion) {
         return ratingCriterionsService.calculateRatingCriterions(id, ratingCriterion);
    }

    @GetMapping(path = "/rating-criterions")
    public List getRatingCriterions() {
        return ratingCriterionsService.getAll();
    }

    @GetMapping(path = "/rating-criterions/{id}")
    public ResponseEntity getAlternative(@PathVariable("id") Integer id) {
        RatingCriterion ratingCriterion = ratingCriterionsService.get(id);
        if (ratingCriterion == null) {
            return new ResponseEntity("No Alternative found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(ratingCriterion, HttpStatus.OK);
    }

    @PostMapping(value = "/rating-criterions")
    public ResponseEntity createRatingCriterion(@RequestBody RatingCriterion ratingCriterion) {
        ratingCriterionsService.addOrUpdate(ratingCriterion);
        return new ResponseEntity(ratingCriterion, HttpStatus.OK);
    }

    @DeleteMapping("/rating-criterions/{id}")
    public ResponseEntity deleteRatingCriterion(@PathVariable("id") Integer id) {
        if (ratingCriterionsService.get(id) == null) {
            return new ResponseEntity("No Alternative found for ID" + id, HttpStatus.NOT_FOUND);
        }
        ratingCriterionsService.delete(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @GetMapping(path = "/rating-criterions/dimension/{dimensionId}")
    public ResponseEntity getRatingCriterionByDimensionId(@PathVariable("dimensionId") Integer dimensionId) {
        ArrayList<RatingCriterion> ratingCriterion = ratingCriterionsService.getRatingCriterionByDimensionId(dimensionId);
        if (ratingCriterion == null) {
            return new ResponseEntity("No rating-criterions found for dimensionId " + dimensionId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(ratingCriterion, HttpStatus.OK);
    }
}
