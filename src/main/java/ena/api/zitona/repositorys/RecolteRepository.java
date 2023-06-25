package ena.api.zitona.repositorys;

import ena.api.zitona.entitys.Recolte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RecolteRepository extends JpaRepository<Recolte, Long> {
    List<Recolte> findAllByParcelleId(Long parcelleId);

    @Query("SELECT SUM(r.quantite) FROM Recolte r WHERE r.parcelle.id = :parcelleId")
    double calculateTotalQuantiteByParcelleId(@Param("parcelleId") Long parcelleId);

    @Query("SELECT SUM(r.cout) FROM Recolte r WHERE r.parcelle.id = :parcelleId")
    float calculateTotalCoutByParcelleId(@Param("parcelleId") Long parcelleId);
}
