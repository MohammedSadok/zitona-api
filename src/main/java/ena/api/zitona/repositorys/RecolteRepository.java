package ena.api.zitona.repositorys;

import ena.api.zitona.entitys.Recolte;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecolteRepository extends JpaRepository<Recolte,Long> {
    List<Recolte> findAllByParcelleId(Long parcelleId);
}
