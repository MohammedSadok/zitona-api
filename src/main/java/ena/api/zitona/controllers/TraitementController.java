package ena.api.zitona.controllers;

import ena.api.zitona.entitys.Recolte;
import ena.api.zitona.entitys.Traitement;
import ena.api.zitona.response.ResponseData;
import ena.api.zitona.services.TraitementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/traitements")
public class TraitementController {
    private final TraitementService traitementService;

    public TraitementController(TraitementService traitementService) {
        this.traitementService = traitementService;
    }

    @PostMapping
    public ResponseEntity<ResponseData<Traitement>> saveTraitement(@RequestBody Traitement traitement) {
        Traitement savedTraitement = traitementService.saveTraitement(traitement);
        ResponseData<Traitement> responseData = new ResponseData<>(savedTraitement, HttpStatus.OK, "Traitement saved successfully");
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Traitement>> getTraitementById(@PathVariable Long id) {
        Traitement traitement = traitementService.getTraitementById(id);
        if (traitement != null) {
            ResponseData<Traitement> responseData = new ResponseData<>(traitement, HttpStatus.OK, "Traitement retrieved successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<Traitement> responseData = new ResponseData<>(null, HttpStatus.NOT_FOUND, "Recolte not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Traitement>> updateTraitement(@PathVariable Long id,@RequestBody Traitement traitement) {
        traitement.setId(id);
        Traitement updatedTraitement = traitementService.updateTraitement(traitement);
        if (updatedTraitement != null) {
            ResponseData<Traitement> responseData = new ResponseData<>(updatedTraitement, HttpStatus.OK, "Traitement updated successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<Traitement> responseData = new ResponseData<>(null, HttpStatus.NOT_FOUND, "Traitement not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Traitement>> deleteTraitement(@PathVariable Long id) {
        Traitement traitement = traitementService.getTraitementById(id);
        if (traitement != null) {
            traitementService.deleteTraitement(id);
            ResponseData<Traitement> responseData = new ResponseData<>(traitement, HttpStatus.OK, "Traitement deleted successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<Traitement> responseData = new ResponseData<>(null, HttpStatus.NOT_FOUND, "Traitement not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<Traitement>>> getAllTraitements() {
        List<Traitement> traitements = traitementService.getAllTraitements();
        ResponseData<List<Traitement>> responseData = new ResponseData<>(traitements, HttpStatus.OK, "All traitements retrieved successfully");
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/parcelle/{parcelleId}")
    public ResponseEntity<ResponseData<List<Traitement>>> getRecoltesByParcelleId(@PathVariable Long parcelleId) {
        List<Traitement> traitements = traitementService.findAllByParcelleId(parcelleId);
        if (!traitements.isEmpty()) {
            ResponseData<List<Traitement>> responseData = new ResponseData<>(traitements, HttpStatus.OK, "Retrieved recoltes by Parcelle ID successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<List<Traitement>> responseData = new ResponseData<>(null, HttpStatus.NOT_FOUND, "No recoltes found for the given Parcelle ID");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }
}
