package ena.api.zitona.repositorys;

import ena.api.zitona.entitys.ParcelleMalade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParcelleMaladeRepository extends JpaRepository<ParcelleMalade,Long> {
    public List<ParcelleMalade> findAllByParcelleId (Long id);
}
