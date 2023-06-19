package ena.api.zitona.controllers;

import ena.api.zitona.entitys.Parcelle;
import ena.api.zitona.entitys.ParcelleMalade;
import ena.api.zitona.services.ParcelleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ena.api.zitona.response.ResponseData;
import java.util.List;

@RestController
@RequestMapping("/parcelles")
public class ParcelleController {

    private final ParcelleService parcelleService;

    public ParcelleController(ParcelleService parcelleService) {
        this.parcelleService = parcelleService;
    }

    @PostMapping
    public ResponseEntity<ResponseData<Parcelle>> createParcelle(@RequestBody Parcelle parcelle) {
        Parcelle savedParcelle = parcelleService.saveParcelle(parcelle);
        ResponseData<Parcelle> responseData = new ResponseData<>(savedParcelle, HttpStatus.CREATED, "Parcelle created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<Parcelle>>> getAllParcelles() {
        List<Parcelle> parcelles = parcelleService.findAllParcelles();
        ResponseData<List<Parcelle>> responseData = new ResponseData<>(parcelles, HttpStatus.OK, "Parcelles retrieved successfully");
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Parcelle>> getParcelleById(@PathVariable Long id) {
        Parcelle parcelle = parcelleService.findParcelleById(id);
        if (parcelle != null) {
            ResponseData<Parcelle> responseData = new ResponseData<>(parcelle, HttpStatus.OK, "Parcelle retrieved successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<Parcelle> responseData = new ResponseData<>(null, HttpStatus.OK, "Parcelle not found");
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Parcelle>> updateParcelle(@PathVariable Long id, @RequestBody Parcelle parcelle) {
        parcelle.setId(id);
        Parcelle updatedParcelle = parcelleService.updateParcelle(parcelle);
        if (updatedParcelle != null) {
            ResponseData<Parcelle> responseData = new ResponseData<>(updatedParcelle, HttpStatus.OK, "Parcelle updated successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<Parcelle> responseData = new ResponseData<>(null, HttpStatus.OK, "Parcelle not found");
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Parcelle>> deleteParcelle(@PathVariable Long id) {
        Parcelle parcelle = parcelleService.findParcelleById(id);
        if (parcelle != null) {
            parcelleService.deleteParcelle(parcelle);
            ResponseData<Parcelle> responseData = new ResponseData<>(parcelle, HttpStatus.OK, "Parcelle deleted successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<Parcelle> responseData = new ResponseData<>(null, HttpStatus.OK, "Parcelle not found");
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
    }

    @GetMapping("/user/{parcelleId}")
    public ResponseEntity<ResponseData<List<Parcelle>>> getRecoltesByParcelleId(@PathVariable Long parcelleId) {
        List<Parcelle> parcelles = parcelleService.findAllByUserId(parcelleId);
        if (!parcelles.isEmpty()) {
            ResponseData<List<Parcelle>> responseData = new ResponseData<>(parcelles, HttpStatus.OK, "Retrieved All parcelles by User ID successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<List<Parcelle>> responseData = new ResponseData<>(null, HttpStatus.OK, "No parcelle found for the given User ID");
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
    }

}
