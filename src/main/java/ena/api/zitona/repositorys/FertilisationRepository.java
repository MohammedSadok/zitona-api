package ena.api.zitona.repositorys;

import ena.api.zitona.entitys.Fertilisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FertilisationRepository extends JpaRepository<Fertilisation, Long> {
    public List<Fertilisation> findAllByParcelleId(Long id);

    @Query("SELECT COALESCE(SUM(r.cout), 0) FROM Fertilisation r WHERE r.parcelle.id = :parcelleId")
    float calculateTotalCoutByParcelleId(@Param("parcelleId") Long parcelleId);
}
