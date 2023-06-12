package ena.api.zitona.repositorys;

import ena.api.zitona.entitys.Traitement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TraitementRepository extends JpaRepository<Traitement, Long> {
    public List<Traitement> findAllByParcelleId(Long id);
}
