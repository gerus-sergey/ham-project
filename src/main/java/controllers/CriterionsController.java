package controllers;

        import models.Criterion;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import services.CriterionService;
        import org.springframework.beans.factory.annotation.Autowired;

        import java.util.List;

@RestController
public class CriterionsController {

    @Autowired
    private CriterionService criterionService;

    @GetMapping(path = "/criterions")
    public List getCriterions() {
        return criterionService.getAll();
    }

    @GetMapping(path = "/criterions/{id}")
    public ResponseEntity getCriterion(@PathVariable("id") Integer id) {
        Criterion criterion = criterionService.get(id);
        if (criterion == null) {
            return new ResponseEntity("No Criterion found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(criterion, HttpStatus.OK);
    }

    @PostMapping(value = "/criterions")
    public ResponseEntity createCriterion(@RequestBody Criterion criterion) {
        criterionService.addOrUpdate(criterion);
        return new ResponseEntity(criterion, HttpStatus.OK);
    }

    @DeleteMapping("/criterions/{id}")
    public ResponseEntity deleteCriterion(@PathVariable("id") Integer id) {
        if (criterionService.get(id) == null) {
            return new ResponseEntity("No criterion found for ID " + id, HttpStatus.NOT_FOUND);
        }
        criterionService.delete(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }
}
