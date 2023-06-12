package ena.api.zitona.repositorys;

import ena.api.zitona.entitys.TraitementPhytosanitaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TraitementPhytosanitaireRepository extends JpaRepository<TraitementPhytosanitaire,Long> {
    public List<TraitementPhytosanitaire> findAllByMaladieId(Long id);
}
