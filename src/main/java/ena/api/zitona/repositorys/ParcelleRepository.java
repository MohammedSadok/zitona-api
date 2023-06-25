package ena.api.zitona.repositorys;

import ena.api.zitona.entitys.Parcelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParcelleRepository extends JpaRepository<Parcelle,Long> {
    public List<Parcelle> findAllByUserId (long Id);
    @Query("SELECT p.user.id FROM Parcelle p WHERE p.id = :parcelleId")
    Long findUserIdByParcelleId(@Param("parcelleId") Long parcelleId);


}
