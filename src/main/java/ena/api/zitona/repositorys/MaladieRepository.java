package ena.api.zitona.repositorys;

import ena.api.zitona.entitys.Maladie;
import ena.api.zitona.entitys.ParcelleMalade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaladieRepository extends JpaRepository<Maladie, Long> {

}
