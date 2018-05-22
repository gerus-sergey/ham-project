package controllers;

import models.CriterionsSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.CriterionsSetService;

import java.util.List;

@RestController
public class CriterionsSetController {

    @Autowired
    private CriterionsSetService criterionsSetService;

    @GetMapping(path = "/criterion-set")
    public List getCriterionSets() {
        return criterionsSetService.getAll();
    }

    @GetMapping(path = "/criterion-set/{id}")
    public ResponseEntity getCriterionSet(@PathVariable("id") Integer id) {
        CriterionsSet criterionsSet = criterionsSetService.get(id);
        if (criterionsSet == null) {
            return new ResponseEntity("No criterion set found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(criterionsSet, HttpStatus.OK);
    }

    @PostMapping(value = "/criterion-set")
    public ResponseEntity createCriterionSet(@RequestBody CriterionsSet criterionsSet) {
        criterionsSetService.addOrUpdate(criterionsSet);
        return new ResponseEntity(criterionsSet, HttpStatus.OK);
    }

    @DeleteMapping("/criterion-set/{id}")
    public ResponseEntity deleteCriterionSet(@PathVariable("id") Integer id) {
        if (criterionsSetService.get(id) == null) {
            return new ResponseEntity("No criterion set found for ID " + id, HttpStatus.NOT_FOUND);
        }
        criterionsSetService.delete(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }
}
