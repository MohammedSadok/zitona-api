package ena.api.zitona.services;

import ena.api.zitona.entitys.User;
import ena.api.zitona.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        // Check if the email already exists
        Optional<User> existingUser = userRepository.findUserByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User deleteUser(User user) {
        userRepository.delete(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        // Check if the email already exists for a different user
        Optional<User> existingUser = userRepository.findUserByEmail(user.getEmail());
        if (existingUser.isPresent() && !existingUser.get().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Email already exists for another user");
        }
        return userRepository.save(user);
    }

    @Override
    public void updateNomAndPrenom(long id, String nom, String prenom) {
        userRepository.updateNomAndPrenom(id, nom, prenom);
    }

    @Override
    public void updatePassword(long id, String password) {
        userRepository.updatePassword(id, password);
    }

    @Override
    public void updateEmail(long id, String email) {
        Optional<User> existingUser = userRepository.findUserByEmail(email);
        if (existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
            throw new IllegalArgumentException("Email already exists for another user");
        }
        userRepository.updateEmail(id, email);
    }

    @Override
    public void updateNumTel(long id, String telephone) {
        userRepository.updateNumTel(id, telephone);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);

    }
}
