package controllers;

import models.AlternativesSet;
import models.Expert;
import models.ExpertsToAlternativeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.AlternativesSetService;
import services.ExpertService;
import services.ExpertsToAlternativeSetService;

import java.util.List;

@RestController
public class ExpertsToAlternativeSetController {

    @Autowired
    private ExpertsToAlternativeSetService expertsToAlternativeSetService;

    @Autowired
    ExpertService expertService;

    @Autowired
    AlternativesSetService alternativesSetService;

    @GetMapping(path = "/expertsToAlternativesSet")
    public List getExpertsToAlternativesSet() {
        return expertsToAlternativeSetService.getAll();
    }

    @GetMapping(path = "/expertsToAlternativesSet/expertsByAlternativeSetId/{id}")
    public ResponseEntity getExpertsByAlternativeSetId(@PathVariable("id") Integer id) {
        List<Expert> experts = expertsToAlternativeSetService.getExpertsByAlternativeSetId(id);
        if (experts.isEmpty()) {
            return new ResponseEntity("No experts found for Alternative Set with ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(experts, HttpStatus.OK);
    }

    @GetMapping(path = "/expertsToAlternativesSet/alternativesSetsByExpertId/{id}")
    public ResponseEntity getAlternativesSetsByExpertId(@PathVariable("id") Integer id) {
        List<AlternativesSet> alternativesSets = expertsToAlternativeSetService.getAlternativesSetsByExpertId(id);
        if (alternativesSets.isEmpty()) {
            return new ResponseEntity("No alternative sets found for expert with ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(alternativesSets, HttpStatus.OK);
    }

    @PostMapping(value = "/expertsToAlternativesSet")
    public ResponseEntity createExpertsToAlternativesSet(@RequestParam("alternativeSetId") Integer alternativeSetId,
                                                         @RequestParam("expertId") Integer expertId) {

        AlternativesSet alternativesSet = alternativesSetService.get(alternativeSetId);
        if (alternativesSet == null) return new ResponseEntity("No alternative set found", HttpStatus.NOT_FOUND);
        Expert expert = expertService.get(expertId);
        if (expert == null) return new ResponseEntity("No expert found", HttpStatus.NOT_FOUND);

        ExpertsToAlternativeSet expertsToAlternativeSet = new ExpertsToAlternativeSet(alternativesSet, expert);
        expertsToAlternativeSetService.addOrUpdate(expertsToAlternativeSet);
        return new ResponseEntity(expertsToAlternativeSet, HttpStatus.OK);
    }
}
