package tech.buildrun.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.api.service.User;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class ApiController {
    private List<User> users = new ArrayList<>();

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        users.add(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(users);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUsers(){
        users.clear();
        return ResponseEntity.ok("Usuários deletados com sucesso!");
    }
}
