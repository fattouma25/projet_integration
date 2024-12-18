package com.example.user.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.example.user.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByEmail(String email);

    public User findByUsername(String username);

    public User findByEmailAndPassword(String email, String password);

    public List<User> findProfileByEmail(String email);

}
