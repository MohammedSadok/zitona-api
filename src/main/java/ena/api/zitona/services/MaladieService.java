package ena.api.zitona.services;

import ena.api.zitona.entitys.Maladie;

import java.util.List;

public interface MaladieService {
    Maladie saveMaladie(Maladie maladie);

    List<Maladie> findAllMaladies();

    Maladie findMaladieById(Long id);

    Maladie updateMaladie(Maladie maladie);

    Maladie deleteMaladie(Maladie maladie);
}
