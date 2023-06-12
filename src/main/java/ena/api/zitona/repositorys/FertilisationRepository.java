package ena.api.zitona.repositorys;

import ena.api.zitona.entitys.Fertilisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FertilisationRepository extends JpaRepository<Fertilisation, Long> {
    public List<Fertilisation> findAllByParcelleId(Long id);
}
