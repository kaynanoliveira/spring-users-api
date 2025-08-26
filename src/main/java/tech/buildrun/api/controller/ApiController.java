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
    private final List<User> users = new ArrayList<>();

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (user.getName().trim().isEmpty() || user.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Nome e email devem ser informados");
        } else {
            for (User u : users) {
                if (u.getId() == user.getId()) {
                    return ResponseEntity.badRequest().body("Id já existe");
                }
            }
            users.add(user);
            return ResponseEntity.ok("Usúario adicionado com sucesso!");
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        if (id >= 0 && id < users.size()) {
            users.set(id, user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUsers() {
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            users.clear();
            return ResponseEntity.ok("Usuários deletados com sucesso!");
        }
    }
}
