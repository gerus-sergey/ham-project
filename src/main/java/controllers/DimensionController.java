package controllers;

import models.Dimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.DimensionService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DimensionController {

    @Autowired
    private DimensionService dimensionService;

    @GetMapping(path = "/dimensions")
    public List getDimensions() {
        return dimensionService.getAll();
    }

    @GetMapping(path = "/dimensions/{id}")
    public ResponseEntity getDimension(@PathVariable("id") Integer id) {
        Dimension dimension = dimensionService.get(id);
        if (dimension == null) {
            return new ResponseEntity("No dimensions found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(dimension, HttpStatus.OK);
    }

    @PostMapping(value = "/dimensions")
    public ResponseEntity createDimension(@RequestBody Dimension dimension) {
        dimensionService.addOrUpdate(dimension);
        return new ResponseEntity(dimension, HttpStatus.OK);
    }

    @DeleteMapping("/dimensions/{id}")
    public ResponseEntity deleteDimension(@PathVariable("id") Integer id) {
        if (dimensionService.get(id) == null) {
            return new ResponseEntity("No dimension found for ID" + id, HttpStatus.NOT_FOUND);
        }
        dimensionService.delete(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @GetMapping(path = "/dimensions/expert/{expertId}")
    public ResponseEntity getDimensionByExpertId(@PathVariable("expertId") Integer expertId) {
        ArrayList<Dimension> dimensions = dimensionService.getDimensionByExpertId(expertId);
        if (dimensions == null) {
            return new ResponseEntity("No dimensions found for expertId " + expertId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(dimensions, HttpStatus.OK);
    }
}
