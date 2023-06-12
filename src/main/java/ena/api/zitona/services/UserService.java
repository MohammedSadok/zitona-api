package ena.api.zitona.services;

import ena.api.zitona.entitys.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User saveUser (User User);
    public List<User> findAllUsers ();
    public User findUserById(Long id);
    public User deleteUser(User User);
    public User updateUser (User User);
    public Optional<User> findUserByEmail (String email);
}
