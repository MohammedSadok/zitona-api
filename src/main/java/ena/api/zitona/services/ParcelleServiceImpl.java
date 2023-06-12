package ena.api.zitona.services;

import ena.api.zitona.entitys.Parcelle;
import ena.api.zitona.repositorys.ParcelleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParcelleServiceImpl implements ParcelleService {

    private final ParcelleRepository parcelleRepository;

    public ParcelleServiceImpl(ParcelleRepository parcelleRepository) {
        this.parcelleRepository = parcelleRepository;
    }

    @Override
    public Parcelle saveParcelle(Parcelle parcelle) {
        return parcelleRepository.save(parcelle);
    }

    @Override
    public List<Parcelle> findAllParcelles() {
        return parcelleRepository.findAll();
    }

    @Override
    public Parcelle findParcelleById(Long id) {
        return parcelleRepository.findById(id).orElse(null);
    }

    @Override
    public Parcelle deleteParcelle(Parcelle parcelle) {
        parcelleRepository.delete(parcelle);
        return parcelle;
    }

    @Override
    public Parcelle updateParcelle(Parcelle parcelle) {
        return parcelleRepository.save(parcelle);
    }

    @Override
    public List<Parcelle> findAllByUserId(long id) {
        return parcelleRepository.findAllByUserId(id);
    }


}
