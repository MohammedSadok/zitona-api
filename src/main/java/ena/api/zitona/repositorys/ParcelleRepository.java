package ena.api.zitona.repositorys;

import ena.api.zitona.entitys.Parcelle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParcelleRepository extends JpaRepository<Parcelle,Long> {
    public List<Parcelle> findAllByUserId (long Id);
}
