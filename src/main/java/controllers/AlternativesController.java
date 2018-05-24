package controllers;

import models.Alternative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.AlternativesService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AlternativesController {

    @Autowired
    private AlternativesService alternativesService;

    @GetMapping(path = "/alternatives")
    public List getAlternatives() {
        return alternativesService.getAll();
    }

    @GetMapping(path = "/alternatives/{id}")
    public ResponseEntity getAlternative(@PathVariable("id") Integer id) {
        Alternative alternative = alternativesService.get(id);
        if (alternative == null) {
            return new ResponseEntity("No Alternative found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(alternative, HttpStatus.OK);
    }

    @PostMapping(value = "/alternatives")
    public ResponseEntity createAlternative(@RequestBody Alternative alternative) {
        alternativesService.addOrUpdate(alternative);
        return new ResponseEntity(alternative, HttpStatus.OK);
    }

    @DeleteMapping("/alternatives/{id}")
    public ResponseEntity deleteAlternative(@PathVariable("id") Integer id) {
        if (alternativesService.get(id) == null) {
            return new ResponseEntity("No Alternative found for ID" + id, HttpStatus.NOT_FOUND);
        }
        alternativesService.delete(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }
}
