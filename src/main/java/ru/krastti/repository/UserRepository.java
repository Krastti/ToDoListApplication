package ru.krastti.repository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.krastti.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    default User getUserById(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Пользователя с таким ID не существует"));
    }

}

