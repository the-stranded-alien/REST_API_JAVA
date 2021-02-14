package com.sahil.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    // GET /users
    // retrieveAllUsers
    @GetMapping("/users")
    public List<User> retrieveAllUser() {
        return service.findAll();
    }

    // GET /users/{id}
    // retrieveUser(int id)
    @GetMapping("users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return service.findOne(id);
    }

    // CREATED
    // input - details of user
    // output - CREATED & Return the created URI
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User savedUser = service.save(user);
        // CREATED
        // URI : /user/{id} = /user/savedUser.getId() (URI)
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
