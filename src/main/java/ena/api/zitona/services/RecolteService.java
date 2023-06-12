package ena.api.zitona.services;

import ena.api.zitona.entitys.Recolte;

import java.util.List;

public interface RecolteService {
    Recolte saveRecolte(Recolte recolte);
    List<Recolte> findAllRecoltes();
    Recolte findRecolteById(Long id);
    Recolte deleteRecolte(Recolte recolte);
    Recolte updateRecolte(Recolte recolte);
    List<Recolte> findAllByParcelleId(Long parcelleId);
}
