package ena.api.zitona.repositorys;

import ena.api.zitona.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findUserByEmail(String email);

    @Query("SELECT p.user.id FROM Parcelle p WHERE p.id = :parcelleId")
    Long findUserIdByParcelleId(@Param("parcelleId") Long parcelleId);
}
