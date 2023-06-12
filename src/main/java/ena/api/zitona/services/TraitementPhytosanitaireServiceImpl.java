package ena.api.zitona.services;

import ena.api.zitona.entitys.TraitementPhytosanitaire;
import ena.api.zitona.repositorys.TraitementPhytosanitaireRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TraitementPhytosanitaireServiceImpl implements TraitementPhytosanitaireService {

    private final TraitementPhytosanitaireRepository traitementPhytosanitaireRepository;

    public TraitementPhytosanitaireServiceImpl(TraitementPhytosanitaireRepository traitementPhytosanitaireRepository) {
        this.traitementPhytosanitaireRepository = traitementPhytosanitaireRepository;
    }

    @Override
    public TraitementPhytosanitaire saveTraitementPhytosanitaire(TraitementPhytosanitaire traitementPhytosanitaire) {
        return traitementPhytosanitaireRepository.save(traitementPhytosanitaire);
    }

    @Override
    public List<TraitementPhytosanitaire> findAllTraitementPhytosanitaires() {
        return traitementPhytosanitaireRepository.findAll();
    }

    @Override
    public TraitementPhytosanitaire findTraitementPhytosanitaireById(Long id) {
        Optional<TraitementPhytosanitaire> traitementOptional = traitementPhytosanitaireRepository.findById(id);
        return traitementOptional.orElse(null);
    }

    @Override
    public TraitementPhytosanitaire updateTraitementPhytosanitaire(TraitementPhytosanitaire traitementPhytosanitaire) {
        return traitementPhytosanitaireRepository.save(traitementPhytosanitaire);
    }

    @Override
    public List<TraitementPhytosanitaire> findAllByMaladieId(Long id) {
        return traitementPhytosanitaireRepository.findAllByMaladieId(id);
    }

    @Override
    public TraitementPhytosanitaire deleteTraitementPhytosanitaire(TraitementPhytosanitaire traitementPhytosanitaire) {
        traitementPhytosanitaireRepository.delete(traitementPhytosanitaire);
        return traitementPhytosanitaire;
    }
}

