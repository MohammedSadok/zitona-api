package ena.api.zitona.services;
import ena.api.zitona.entitys.Parcelle;

import java.util.List;
import java.util.Optional;

public interface ParcelleService {
    Parcelle saveParcelle(Parcelle parcelle);
    List<Parcelle> findAllParcelles();
    Parcelle findParcelleById(Long id);
    Parcelle deleteParcelle(Parcelle parcelle);
    Parcelle updateParcelle(Parcelle parcelle);
    public List<Parcelle> findAllByUserId (long Id);

}
