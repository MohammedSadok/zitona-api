package ena.api.zitona.services;

import ena.api.zitona.entitys.ParcelleMalade;

import java.util.List;

public interface ParcelleMaladeService {
    ParcelleMalade saveParcelleMalade(ParcelleMalade ParcelleMalade);
    List<ParcelleMalade> findAllParcelleMalades();
    List<ParcelleMalade> findAllHistoryMalades(Long id);
    ParcelleMalade findParcelleMaladeById(Long id);
    ParcelleMalade deleteParcelleMalade(ParcelleMalade ParcelleMalade);
    ParcelleMalade updateParcelleMalade(ParcelleMalade ParcelleMalade);
}
