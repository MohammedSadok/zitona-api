package ena.api.zitona.services;

import ena.api.zitona.entitys.Recolte;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecolteService {
    Recolte saveRecolte(Recolte recolte);
    List<Recolte> findAllRecoltes();
    Recolte findRecolteById(Long id);
    Recolte deleteRecolte(Recolte recolte);
    Recolte updateRecolte(Recolte recolte);
    List<Recolte> findAllByParcelleId(Long parcelleId);
    double calculateTotalQuantiteByParcelleId(Long parcelleId);
    double calculateTotalCoutByParcelleId (Long parcelleId);
}
