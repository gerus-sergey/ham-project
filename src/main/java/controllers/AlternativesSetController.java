package controllers;

import models.AlternativesSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.AlternativesSetService;

import java.util.List;

@RestController
public class AlternativesSetController {

    @Autowired
    private AlternativesSetService alternativesSetService;

    @GetMapping(path = "/alternatives-set")
    public List getAlternativesSets() {
        return alternativesSetService.getAll();
    }

    @GetMapping(path = "/alternatives-set/{id}")
    public ResponseEntity getAlternativesSet(@PathVariable("id") Integer id) {
        AlternativesSet alternativesSet = alternativesSetService.get(id);
        if (alternativesSet == null) {
            return new ResponseEntity("No alternatives set found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(alternativesSet, HttpStatus.OK);
    }

    @PostMapping(value = "/alternatives-set")
    public ResponseEntity createAlternativesSet(@RequestBody AlternativesSet alternativesSet) {
        alternativesSetService.addOrUpdate(alternativesSet);
        return new ResponseEntity(alternativesSet, HttpStatus.OK);
    }

    @DeleteMapping("/alternatives-set/{id}")
    public ResponseEntity deleteAlternativesSet(@PathVariable("id") Integer id) {
        if (alternativesSetService.get(id) == null) {
            return new ResponseEntity("No alternatives set found for ID " + id, HttpStatus.NOT_FOUND);
        }
        alternativesSetService.delete(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }
}
