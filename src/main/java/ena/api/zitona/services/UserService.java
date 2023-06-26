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
    public void updateNomAndPrenom (long id,String nom,String prenom);
    public void updatePassword (long id,String password);
    public void updateEmail(long id,String email);
    public void updateNumTel(long id,String telephone);
    public Optional<User> findUserByEmail (String email);
}
