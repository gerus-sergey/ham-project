package controllers;

import models.Alternative;
import models.AlternativesSet;
import models.AlternativesToAlternativeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.AlternativesService;
import services.AlternativesSetService;
import services.AlternativesToAlternativeSetService;

import java.util.List;

@RestController
public class AlternativesToAlternativeSetController {

    @Autowired
    private AlternativesToAlternativeSetService alternativesToAlternativeSetService;

    @Autowired
    private AlternativesService alternativesService;

    @Autowired
    private AlternativesSetService alternativesSetService;

    @GetMapping(path = "/alternativesToAlternativeSet")
    public List getAlternativesToAlternativeSet() {
        return alternativesToAlternativeSetService.getAll();
    }

    @GetMapping(path = "/alternativesToAlternativeSet/alternativesByAlternativeSetId/{id}")
    public ResponseEntity getAlternativesByAlternativeSetId(@PathVariable("id") Integer id) {
        List<Alternative> alternatives = alternativesToAlternativeSetService.getAlternativesByAlternativeSetId(id);
        if (alternatives.isEmpty()) {
            return new ResponseEntity("No alternatives found for alternative set with ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(alternatives, HttpStatus.OK);
    }

    @PostMapping(value = "/alternativesToAlternativeSet")
    public ResponseEntity createAlternativesToAlternativeSet(@RequestParam("alternativesSetId") Integer alternativesSetId,
                                                             @RequestParam("alternativeId") Integer alternativeId) {

        AlternativesSet alternativesSet = alternativesSetService.get(alternativesSetId);
        if (alternativesSet == null) return new ResponseEntity("No alternatives set found", HttpStatus.NOT_FOUND);

        Alternative alternative = alternativesService.get(alternativeId);
        if (alternative == null) return new ResponseEntity("No alternative found", HttpStatus.NOT_FOUND);

        AlternativesToAlternativeSet alternativesToAlternativeSet = new AlternativesToAlternativeSet(alternativesSet, alternative);
        alternativesToAlternativeSetService.addOrUpdate(alternativesToAlternativeSet);
        return new ResponseEntity(alternativesToAlternativeSet, HttpStatus.OK);
    }

}
