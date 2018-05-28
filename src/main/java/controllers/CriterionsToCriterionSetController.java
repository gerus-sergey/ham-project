package controllers;

import models.Criterion;
import models.CriterionsSet;
import models.CriterionsToCriterionSet;
import models.helpers.CriterionsToCriterionSetPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.CriterionService;
import services.CriterionsSetService;
import services.CriterionsToCriterionSetService;

import java.util.List;

@RestController
public class CriterionsToCriterionSetController {

    @Autowired
    private CriterionsToCriterionSetService criterionsToCriterionSetService;

    @Autowired
    private CriterionsSetService criterionsSetService;

    @Autowired
    private CriterionService criterionService;

    @GetMapping(path = "/criterionsToCriterionSet")
    public List getCriterionsToCriterionSet() {
        return criterionsToCriterionSetService.getAll();
    }

    @GetMapping(path = "/criterionsToCriterionSet/criterionsByCriterionSetId/{id}")
    public ResponseEntity getCriterionsByCriterionSetId(@PathVariable("id") Integer id) {
        List<Criterion> criterions = criterionsToCriterionSetService.getCriterionsByCriterionSetId(id);
        if (criterions.isEmpty()) {
            return new ResponseEntity("No Users found for criterion sets with ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(criterions, HttpStatus.OK);
    }

    @PostMapping(value = "/criterionsToCriterionSet")
    public ResponseEntity createCriterionsToCriterionSet(@RequestParam("criterionSetId") Integer criterionSetId,
                                                         @RequestParam("criterionId") Integer criterionId) {
        CriterionsSet criterionsSet = criterionsSetService.get(criterionSetId);
        if (criterionsSet == null) return new ResponseEntity("No criterions set found", HttpStatus.NOT_FOUND);
        Criterion criterion = criterionService.get(criterionId);
        if (criterion == null) return new ResponseEntity("No criterion found", HttpStatus.NOT_FOUND);

        CriterionsToCriterionSet criterionsToCriterionSet = new CriterionsToCriterionSet(criterionsSet, criterion);
        criterionsToCriterionSetService.addOrUpdate(criterionsToCriterionSet);
        return new ResponseEntity(criterionsToCriterionSet, HttpStatus.OK);
    }

    @DeleteMapping(value = "/criterionsToCriterionSet")
    public ResponseEntity deleteCriterionsToCriterionSet(@RequestParam("criterionSetId") Integer criterionSetId,
                                                         @RequestParam("criterionId") Integer criterionId) {
        CriterionsSet criterionsSet = criterionsSetService.get(criterionSetId);
        if (criterionsSet == null) return new ResponseEntity("No criterions set found", HttpStatus.NOT_FOUND);
        Criterion criterion = criterionService.get(criterionId);
        if (criterion == null) return new ResponseEntity("No criterion found", HttpStatus.NOT_FOUND);

        CriterionsToCriterionSetPK criterionsToCriterionSet = new CriterionsToCriterionSetPK(criterionsSet, criterion);
        criterionsToCriterionSetService.delete(criterionsToCriterionSet);
        return new ResponseEntity("Ok", HttpStatus.OK);
    }

}
