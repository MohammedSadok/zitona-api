package ena.api.zitona.repositorys;

import ena.api.zitona.entitys.Traitement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TraitementRepository extends JpaRepository<Traitement, Long> {
    public List<Traitement> findAllByParcelleId(Long id);
    @Query("SELECT SUM(r.cout) FROM Traitement r WHERE r.parcelle.id = :parcelleId")
    float calculateTotalCoutByParcelleId(@Param("parcelleId") Long parcelleId);
}
