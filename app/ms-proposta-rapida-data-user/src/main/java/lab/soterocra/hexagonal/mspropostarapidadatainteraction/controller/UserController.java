package lab.soterocra.hexagonal.mspropostarapidadatainteraction.controller;

import lab.soterocra.hexagonal.mspropostarapidadatainteraction.entity.UserEntity;
import lab.soterocra.hexagonal.mspropostarapidadatainteraction.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    private ResponseEntity<UserEntity> save(@RequestBody UserEntity payload) {
        return ResponseEntity.ok(service.save(payload));
    }

    @GetMapping
    private ResponseEntity<List<UserEntity>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{userId}")
    private ResponseEntity<UserEntity> findById(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(service.findById(userId));
    }

    @GetMapping("supervisors")
    private ResponseEntity<List<UserEntity>> findAllSupervisors() {
        return ResponseEntity.ok(service.findAllSupervisors());
    }

    @GetMapping("employees")
    private ResponseEntity<List<UserEntity>> findAllEmployees() {
        return ResponseEntity.ok(service.findAllEmployees());
    }

    @DeleteMapping("{userId}")
    private ResponseEntity<UserEntity> delete(@PathVariable("userId") String userId) {
        service.delete(userId);
        return ResponseEntity.noContent().build();
    }

}
