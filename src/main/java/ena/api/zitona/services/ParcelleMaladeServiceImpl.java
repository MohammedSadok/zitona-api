package ena.api.zitona.services;

import ena.api.zitona.entitys.ParcelleMalade;
import ena.api.zitona.repositorys.ParcelleMaladeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelleMaladeServiceImpl implements ParcelleMaladeService {

    private final ParcelleMaladeRepository parcelleMaladeRepository;

    public ParcelleMaladeServiceImpl(ParcelleMaladeRepository parcelleMaladeRepository) {
        this.parcelleMaladeRepository = parcelleMaladeRepository;
    }

    @Override
    public ParcelleMalade saveParcelleMalade(ParcelleMalade parcelleMalade) {
        return parcelleMaladeRepository.save(parcelleMalade);
    }

    @Override
    public List<ParcelleMalade> findAllParcelleMalades() {
        return parcelleMaladeRepository.findAll();
    }

    @Override
    public List<ParcelleMalade> findAllHistoryMalades(Long id) {
        return parcelleMaladeRepository.findAllByParcelleId(id);
    }

    @Override
    public ParcelleMalade findParcelleMaladeById(Long id) {
        return parcelleMaladeRepository.findById(id).orElse(null);
    }

    @Override
    public ParcelleMalade updateParcelleMalade(ParcelleMalade parcelleMalade) {
        return parcelleMaladeRepository.save(parcelleMalade);
    }

    @Override
    public ParcelleMalade deleteParcelleMalade(ParcelleMalade parcelleMalade) {
        parcelleMaladeRepository.delete(parcelleMalade);
        return parcelleMalade;
    }
}

