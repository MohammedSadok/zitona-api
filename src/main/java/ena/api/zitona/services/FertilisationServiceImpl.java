package ena.api.zitona.services;

import ena.api.zitona.entitys.Fertilisation;
import ena.api.zitona.repositorys.FertilisationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FertilisationServiceImpl implements FertilisationService {

    private final FertilisationRepository fertilisationRepository;

    public FertilisationServiceImpl(FertilisationRepository fertilisationRepository) {
        this.fertilisationRepository = fertilisationRepository;
    }

    @Override
    public List<Fertilisation> findAllFertilisations() {
        return this.fertilisationRepository.findAll();
    }

    @Override
    public Fertilisation saveFertilisation(Fertilisation fertilisation) {
        return fertilisationRepository.save(fertilisation);
    }

    @Override
    public Fertilisation findFertilisationById(Long id) {
        return fertilisationRepository.findById(id).orElse(null);
    }

    @Override
    public Fertilisation updateFertilisation(Fertilisation fertilisation) {
        return fertilisationRepository.save(fertilisation);
    }

    @Override
    public void deleteFertilisation(Fertilisation fertilisation) {
        fertilisationRepository.delete(fertilisation);
    }

    @Override
    public List<Fertilisation> findAllByParcelleId(Long id) {
        return fertilisationRepository.findAllByParcelleId(id);
    }

    @Override
    public float calculateTotalCoutByParcelleId(Long id) {
        return fertilisationRepository.calculateTotalCoutByParcelleId(id);
    }
}
