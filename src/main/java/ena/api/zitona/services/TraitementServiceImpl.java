package ena.api.zitona.services;

import ena.api.zitona.entitys.Traitement;
import ena.api.zitona.repositorys.TraitementRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TraitementServiceImpl implements TraitementService{
    private final TraitementRepository traitementRepository;

    public TraitementServiceImpl(TraitementRepository traitementRepository) {
        this.traitementRepository = traitementRepository;
    }

    @Override
    public Traitement saveTraitement(Traitement traitement) {
        return traitementRepository.save(traitement);
    }



    @Override
    public Traitement getTraitementById(Long id) {
        return traitementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Traitement not found with id: " + id));
    }

    @Override
    public Traitement updateTraitement(Traitement traitement) {
        return traitementRepository.save(traitement);
    }

    @Override
    public void deleteTraitement(Long id) {
        Traitement existingTraitement = getTraitementById(id);
        traitementRepository.delete(existingTraitement);
    }

    @Override
    public List<Traitement> getAllTraitements() {
        return traitementRepository.findAll();
    }

    @Override
    public List<Traitement> findAllByParcelleId(Long id) {
        return traitementRepository.findAllByParcelleId(id);
    }
}
