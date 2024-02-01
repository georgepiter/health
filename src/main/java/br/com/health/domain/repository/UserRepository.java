package br.com.health.domain.repository;

import br.com.health.domain.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByName(String userName);

	boolean existsByNameOrEmail(String name, String email);
}
