package controllers;

import models.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.RolesService;

import java.util.List;

@RestController
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping(path = "/roles")
    public List getRoles() {
        return rolesService.getAll();
    }

    @GetMapping(path = "/roles/{id}")
    public ResponseEntity getRole(@PathVariable("id") Integer id) {
        Roles role = rolesService.get(id);
        if (role == null) {
            return new ResponseEntity("No Role found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(role, HttpStatus.OK);
    }

    @PostMapping(value = "/roles")
    public ResponseEntity createRole(@RequestBody Roles role) {
        rolesService.addOrUpdate(role);
        return new ResponseEntity(role, HttpStatus.OK);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity deleteRole(@PathVariable("id") Integer id) {
        if (rolesService.get(id) == null) {
            return new ResponseEntity("No role found for ID" + id, HttpStatus.NOT_FOUND);
        }
        rolesService.delete(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }
}
