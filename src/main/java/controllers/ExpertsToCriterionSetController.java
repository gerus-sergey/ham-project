package controllers;

import models.CriterionsSet;
import models.Expert;
import models.ExpertsToCriterionSet;
import models.helpers.ExpertsToCriterionSetPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.CriterionsSetService;
import services.ExpertService;
import services.ExpertsToCriterionSetService;

import java.util.List;

@RestController
public class ExpertsToCriterionSetController {

    @Autowired
    private ExpertsToCriterionSetService expertsToCriterionSetService;

    @Autowired
    private ExpertService expertService;

    @Autowired
    private CriterionsSetService criterionsSetService;

    @GetMapping(path = "/expertsToCriterionSet")
    public List getExpertsToCriterionSet() {
        return expertsToCriterionSetService.getAll();
    }

    @GetMapping(path = "/expertsToCriterionSet/expertsByCriterionSetId/{id}")
    public ResponseEntity getExpertsByCriterionSetId(@PathVariable("id") Integer id) {
        List<Expert> experts = expertsToCriterionSetService.getExpertsByCriterionSetId(id);
        if (experts.isEmpty()) {
            return new ResponseEntity("No experts found for Criterion Set with ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(experts, HttpStatus.OK);
    }

    @GetMapping(path = "/expertsToCriterionSet/CriterionSetsByExpertId/{id}")
    public ResponseEntity getCriterionSetsByExpertId(@PathVariable("id") Integer id) {
        List<CriterionsSet> criterionsSets = expertsToCriterionSetService.getCriterionSetsByExpertId(id);
        if (criterionsSets.isEmpty()) {
            return new ResponseEntity("No criterions sets found for expert with ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(criterionsSets, HttpStatus.OK);
    }

    @PostMapping(value = "/expertsToCriterionSet")
    public ResponseEntity createExpertsToCriterionSet(@RequestParam("criterionSetId") Integer criterionSetId,
                                                      @RequestParam("expertId") Integer expertId) {

        CriterionsSet criterionsSet = criterionsSetService.get(criterionSetId);
        if (criterionsSet == null) return new ResponseEntity("No criterions set found", HttpStatus.NOT_FOUND);
        Expert expert = expertService.get(expertId);
        if (expert == null) return new ResponseEntity("No expert found", HttpStatus.NOT_FOUND);

        ExpertsToCriterionSet expertsToCriterionSet = new ExpertsToCriterionSet(criterionsSet, expert);
        expertsToCriterionSetService.addOrUpdate(expertsToCriterionSet);
        return new ResponseEntity(expertsToCriterionSet, HttpStatus.OK);
    }

    @DeleteMapping(value = "/expertsToCriterionSet")
    public ResponseEntity deleteExpertsToCriterionSet(@RequestParam("criterionSetId") Integer criterionSetId,
                                                      @RequestParam("expertId") Integer expertId) {

        CriterionsSet criterionsSet = criterionsSetService.get(criterionSetId);
        if (criterionsSet == null) return new ResponseEntity("No criterions set found", HttpStatus.NOT_FOUND);
        Expert expert = expertService.get(expertId);
        if (expert == null) return new ResponseEntity("No expert found", HttpStatus.NOT_FOUND);

        ExpertsToCriterionSetPK expertsToCriterionSet = new ExpertsToCriterionSetPK(criterionsSet, expert);
        expertsToCriterionSetService.delete(expertsToCriterionSet);
        return new ResponseEntity("OK", HttpStatus.OK);
    }
}
