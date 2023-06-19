package ena.api.zitona.controllers;

import ena.api.zitona.entitys.TraitementPhytosanitaire;
import ena.api.zitona.response.ResponseData;
import ena.api.zitona.services.TraitementPhytosanitaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/traitements_phytosanitaire")
public class TraitementPhytosanitaireController {

    private final TraitementPhytosanitaireService traitementPhytosanitaireService;

    public TraitementPhytosanitaireController(TraitementPhytosanitaireService traitementPhytosanitaireService) {
        this.traitementPhytosanitaireService = traitementPhytosanitaireService;
    }

    @PostMapping
    public ResponseEntity<ResponseData<TraitementPhytosanitaire>> createTraitementPhytosanitaire(@RequestBody TraitementPhytosanitaire traitementPhytosanitaire) {
        TraitementPhytosanitaire savedTraitement = traitementPhytosanitaireService.saveTraitementPhytosanitaire(traitementPhytosanitaire);
        ResponseData<TraitementPhytosanitaire> responseData = new ResponseData<>(savedTraitement, HttpStatus.CREATED, "Traitement phytosanitaire created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<TraitementPhytosanitaire>>> getAllTraitementPhytosanitaires() {
        List<TraitementPhytosanitaire> traitements = traitementPhytosanitaireService.findAllTraitementPhytosanitaires();
        ResponseData<List<TraitementPhytosanitaire>> responseData = new ResponseData<>(traitements, HttpStatus.OK, "Retrieved all traitements phytosanitaires successfully");
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<TraitementPhytosanitaire>> getTraitementPhytosanitaireById(@PathVariable Long id) {
        TraitementPhytosanitaire traitement = traitementPhytosanitaireService.findTraitementPhytosanitaireById(id);
        if (traitement != null) {
            ResponseData<TraitementPhytosanitaire> responseData = new ResponseData<>(traitement, HttpStatus.OK, "Retrieved traitement phytosanitaire by ID successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<TraitementPhytosanitaire> responseData = new ResponseData<>(null, HttpStatus.OK, "Traitement phytosanitaire not found");
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<TraitementPhytosanitaire>> updateTraitementPhytosanitaire(@PathVariable Long id, @RequestBody TraitementPhytosanitaire traitementPhytosanitaire) {
        traitementPhytosanitaire.setId(id);
        TraitementPhytosanitaire updatedTraitement = traitementPhytosanitaireService.updateTraitementPhytosanitaire(traitementPhytosanitaire);
        if (updatedTraitement != null) {
            ResponseData<TraitementPhytosanitaire> responseData = new ResponseData<>(updatedTraitement, HttpStatus.OK, "Traitement phytosanitaire updated successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<TraitementPhytosanitaire> responseData = new ResponseData<>(null, HttpStatus.OK, "Traitement phytosanitaire not found");
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<TraitementPhytosanitaire>> deleteTraitementPhytosanitaire(@PathVariable Long id) {
        TraitementPhytosanitaire traitement = traitementPhytosanitaireService.findTraitementPhytosanitaireById(id);
        if (traitement != null) {
            traitementPhytosanitaireService.deleteTraitementPhytosanitaire(traitement);
            ResponseData<TraitementPhytosanitaire> responseData = new ResponseData<>(traitement, HttpStatus.OK, "Traitement phytosanitaire deleted successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<TraitementPhytosanitaire> responseData = new ResponseData<>(null, HttpStatus.OK, "Traitement phytosanitaire not found");
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
    }

    @GetMapping("/maladie/{id}")
    public ResponseEntity<ResponseData<List<TraitementPhytosanitaire>>> findAllByMaladieId(@PathVariable Long id) {
        List<TraitementPhytosanitaire> traitements = traitementPhytosanitaireService.findAllByMaladieId(id);
        ResponseData<List<TraitementPhytosanitaire>> responseData = new ResponseData<>(traitements, HttpStatus.OK, "Retrieved all traitements phytosanitaires successfully");
        return ResponseEntity.ok(responseData);
    }
}

