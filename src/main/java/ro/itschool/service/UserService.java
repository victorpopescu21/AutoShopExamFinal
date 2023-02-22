package ro.itschool.service;

import org.springframework.stereotype.Service;
import ro.itschool.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    User findUserByEmail(String email);

    User findUserByUserName(String username);

    User findUserByRandomToken(String randomToken);

    boolean findUserByUserNameAndPassword(String userName, String password);

    List<User> findAll();

    void deleteById(long id);

    User saveUser(User u);

    User updateUser(User user);

    Optional<User> findById(Long id);

    List<User> searchUser(String keyword);

}