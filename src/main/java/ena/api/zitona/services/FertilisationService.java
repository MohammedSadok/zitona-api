package ena.api.zitona.services;

import ena.api.zitona.entitys.Fertilisation;
import java.util.List;

public interface FertilisationService {

    public List<Fertilisation> findAllFertilisations();
    Fertilisation saveFertilisation(Fertilisation fertilisation);

    Fertilisation findFertilisationById(Long id);

    Fertilisation updateFertilisation(Fertilisation fertilisation);

    void deleteFertilisation(Fertilisation fertilisation);

    public List<Fertilisation> findAllByParcelleId(Long id);

    float calculateTotalCoutByParcelleId (Long id);
}

