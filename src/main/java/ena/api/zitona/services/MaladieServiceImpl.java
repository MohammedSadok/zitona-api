package ena.api.zitona.services;

import ena.api.zitona.entitys.Maladie;
import ena.api.zitona.repositorys.MaladieRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class MaladieServiceImpl implements MaladieService{

    private final MaladieRepository maladieRepository;

    public MaladieServiceImpl(MaladieRepository maladieRepository) {
        this.maladieRepository = maladieRepository;
    }

    @Override
    public Maladie saveMaladie(Maladie maladie) {
        return maladieRepository.save(maladie);
    }

    @Override
    public List<Maladie> findAllMaladies() {
        return maladieRepository.findAll();
    }

    @Override
    public Maladie findMaladieById(Long id) {
        Optional<Maladie> maladieOptional = maladieRepository.findById(id);
        return maladieOptional.orElse(null);
    }

    @Override
    public Maladie updateMaladie(Maladie maladie) {
        return maladieRepository.save(maladie);
    }

    @Override
    public Maladie deleteMaladie(Maladie maladie) {
        maladieRepository.delete(maladie);
        return maladie;
    }
}
