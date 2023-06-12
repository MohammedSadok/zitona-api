package ena.api.zitona.services;

import ena.api.zitona.entitys.TraitementPhytosanitaire;
import ena.api.zitona.entitys.TraitementPhytosanitaire;

import java.util.List;

public interface TraitementPhytosanitaireService {
    TraitementPhytosanitaire saveTraitementPhytosanitaire(TraitementPhytosanitaire traitementPhytosanitaire);

    List<TraitementPhytosanitaire> findAllTraitementPhytosanitaires();

    TraitementPhytosanitaire findTraitementPhytosanitaireById(Long id);

    TraitementPhytosanitaire deleteTraitementPhytosanitaire(TraitementPhytosanitaire traitementPhytosanitaire);

    TraitementPhytosanitaire updateTraitementPhytosanitaire(TraitementPhytosanitaire traitementPhytosanitaire);

     List<TraitementPhytosanitaire> findAllByMaladieId(Long id);
}
