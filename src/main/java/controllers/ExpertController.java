package controllers;

import models.Expert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ExpertService;

import java.util.List;

@RestController
public class ExpertController {

    @Autowired
    private ExpertService expertService;

    @GetMapping(path = "/experts")
    public List getExperts() {
        return expertService.getAll();
    }

    @GetMapping(path = "/experts/{id}")
    public ResponseEntity getExpert(@PathVariable("id") Integer id) {
        Expert expert = expertService.get(id);
        if (expert == null) {
            return new ResponseEntity("No expert found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(expert, HttpStatus.OK);
    }

    @PostMapping(value = "/experts")
    public ResponseEntity createExpert(@RequestBody Expert expert) {
        expertService.addOrUpdate(expert);
        return new ResponseEntity(expert, HttpStatus.OK);
    }

    @DeleteMapping("/experts/{id}")
    public ResponseEntity deleteExpert(@PathVariable("id") Integer id) {
        if (expertService.get(id) == null) {
            return new ResponseEntity("No expert found for ID " + id, HttpStatus.NOT_FOUND);
        }
        expertService.delete(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @GetMapping("/experts/getByEmail/{email:.+}")
    public ResponseEntity getUserByEmail(@PathVariable("email") String email) {
        Expert expert = expertService.getUserByEmail(email.trim());
        if (expert == null) {
            return new ResponseEntity("No expert found for email " + email, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(expert, HttpStatus.OK);
    }
}
