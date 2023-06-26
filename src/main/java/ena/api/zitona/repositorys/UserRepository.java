package ena.api.zitona.repositorys;

import ena.api.zitona.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findUserByEmail(String email);

    @Query("SELECT p.user.id FROM Parcelle p WHERE p.id = :parcelleId")
    Long findUserIdByParcelleId(@Param("parcelleId") Long parcelleId);

    @Modifying
    @Query("UPDATE User u SET u.nom = :nom, u.prenom = :prenom WHERE u.id = :userId")
    void updateNomAndPrenom(@Param("userId") Long userId, @Param("nom") String nom, @Param("prenom") String prenom);

    @Modifying
    @Query("UPDATE User u SET  u.password = :password WHERE u.id = :userId")
    void updatePassword(@Param("userId") Long userId, @Param("password") String password);
    @Modifying
    @Query("UPDATE User u SET u.email = :email WHERE u.id = :userId")
    void updateEmail(@Param("userId") Long userId, @Param("email") String email);

    @Modifying
    @Query("UPDATE User u SET u.telephone = :telephone WHERE u.id = :userId")
    void updateNumTel(@Param("userId") Long userId, @Param("telephone") String telephone);
}
