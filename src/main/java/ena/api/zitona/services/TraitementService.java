package ena.api.zitona.services;

import ena.api.zitona.entitys.Traitement;

import java.util.List;

public interface TraitementService {
    Traitement saveTraitement(Traitement traitement);
    Traitement getTraitementById(Long id);
    Traitement updateTraitement(Traitement traitement);
    void deleteTraitement(Long id);
    List<Traitement> getAllTraitements();
    public List<Traitement> findAllByParcelleId(Long id);
}
