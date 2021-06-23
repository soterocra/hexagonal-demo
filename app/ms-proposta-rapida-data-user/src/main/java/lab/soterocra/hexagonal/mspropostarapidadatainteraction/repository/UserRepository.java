package lab.soterocra.hexagonal.mspropostarapidadatainteraction.repository;

import lab.soterocra.hexagonal.mspropostarapidadatainteraction.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    List<UserEntity> findByRoleIgnoreCase(String role);

}
