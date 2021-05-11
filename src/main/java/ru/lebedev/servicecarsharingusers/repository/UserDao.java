package ru.lebedev.servicecarsharingusers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lebedev.servicecarsharingusers.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
}
