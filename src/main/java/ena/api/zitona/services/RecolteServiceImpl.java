package ena.api.zitona.services;

import ena.api.zitona.entitys.Recolte;
import ena.api.zitona.repositorys.RecolteRepository;
import ena.api.zitona.repositorys.TraitementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
@Transactional
public class RecolteServiceImpl implements RecolteService{
    private final RecolteRepository recolteRepository;
    private final TraitementRepository traitementRepository;

    public RecolteServiceImpl(RecolteRepository recolteRepository,
                              TraitementRepository traitementRepository) {
        this.recolteRepository = recolteRepository;
        this.traitementRepository = traitementRepository;
    }

    @Override
    public Recolte saveRecolte(Recolte recolte) {
        return recolteRepository.save(recolte);
    }

    @Override
    public List<Recolte> findAllRecoltes() {
        return recolteRepository.findAll();
    }

    @Override
    public Recolte findRecolteById(Long id) {
        return recolteRepository.findById(id).orElse(null);
    }

    @Override
    public Recolte deleteRecolte(Recolte recolte) {
        recolteRepository.delete(recolte);
        return recolte;
    }

    @Override
    public Recolte updateRecolte(Recolte recolte) {
        return recolteRepository.save(recolte);
    }

    @Override
    public List<Recolte> findAllByParcelleId(Long parcelleId) {
        return recolteRepository.findAllByParcelleId(parcelleId);
    }

    @Override
    public double calculateTotalQuantiteByParcelleId(Long parcelleId) {
        return recolteRepository.calculateTotalQuantiteByParcelleId(parcelleId);
    }

    @Override
    public double calculateTotalCoutByParcelleId(Long id) {
        return recolteRepository.calculateTotalCoutByParcelleId(id);
    }


}
