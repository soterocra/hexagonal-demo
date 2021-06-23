package lab.soterocra.hexagonal.mspropostarapidadatainteraction.service;

import lab.soterocra.hexagonal.mspropostarapidadatainteraction.entity.UserEntity;
import lab.soterocra.hexagonal.mspropostarapidadatainteraction.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserEntity save(UserEntity entity) {
        return repository.save(entity);
    }

    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    public List<UserEntity> findAllSupervisors() {
        return repository.findByRoleIgnoreCase("supervisor");
    }

    public List<UserEntity> findAllEmployees() {
        return repository.findByRoleIgnoreCase("employee");
    }

    public void delete(String userId) {
        repository.deleteById(userId);
    }

    public UserEntity findById(String userId) {
        return repository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }
}
