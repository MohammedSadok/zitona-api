package ena.api.zitona.controllers;

import ena.api.zitona.entitys.ParcelleMalade;
import ena.api.zitona.entitys.Recolte;
import ena.api.zitona.response.ResponseData;
import ena.api.zitona.services.ParcelleMaladeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parcelles-malades")
public class ParcelleMaladeController {

    private final ParcelleMaladeService parcelleMaladeService;

    public ParcelleMaladeController(ParcelleMaladeService parcelleMaladeService) {
        this.parcelleMaladeService = parcelleMaladeService;
    }

    @PostMapping
    public ResponseEntity<ResponseData<ParcelleMalade>> createParcelleMalade(@RequestBody ParcelleMalade parcelleMalade) {
        ParcelleMalade savedParcelleMalade = parcelleMaladeService.saveParcelleMalade(parcelleMalade);
        ResponseData<ParcelleMalade> responseData = new ResponseData<>(savedParcelleMalade, HttpStatus.CREATED, "Parcelle malade created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<ParcelleMalade>>> getAllParcellesMalades() {
        List<ParcelleMalade> parcellesMalades = parcelleMaladeService.findAllParcelleMalades();
        ResponseData<List<ParcelleMalade>> responseData = new ResponseData<>(parcellesMalades, HttpStatus.OK, "Retrieved all parcelles malades successfully");
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<ParcelleMalade>> getParcelleMaladeById(@PathVariable Long id) {
        ParcelleMalade parcelleMalade = parcelleMaladeService.findParcelleMaladeById(id);
        if (parcelleMalade != null) {
            ResponseData<ParcelleMalade> responseData = new ResponseData<>(parcelleMalade, HttpStatus.OK, "Retrieved parcelle malade by ID successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<ParcelleMalade> responseData = new ResponseData<>(null, HttpStatus.OK, "Parcelle malade not found");
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<ParcelleMalade>> updateParcelleMalade(@PathVariable Long id, @RequestBody ParcelleMalade parcelleMalade) {
        parcelleMalade.setId(id);
        ParcelleMalade updatedParcelleMalade = parcelleMaladeService.updateParcelleMalade(parcelleMalade);
        if (updatedParcelleMalade != null) {
            ResponseData<ParcelleMalade> responseData = new ResponseData<>(updatedParcelleMalade, HttpStatus.OK, "Parcelle malade updated successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<ParcelleMalade> responseData = new ResponseData<>(null, HttpStatus.OK, "Parcelle malade not found");
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<ParcelleMalade>> deleteParcelleMalade(@PathVariable Long id) {
        ParcelleMalade parcelleMalade = parcelleMaladeService.findParcelleMaladeById(id);
        if (parcelleMalade != null) {
            parcelleMaladeService.deleteParcelleMalade(parcelleMalade);
            ResponseData<ParcelleMalade> responseData = new ResponseData<>(parcelleMalade, HttpStatus.OK, "Parcelle malade deleted successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<ParcelleMalade> responseData = new ResponseData<>(null, HttpStatus.OK, "Parcelle malade not found");
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
    }

    @GetMapping("/parcelle/{parcelleId}")
    public ResponseEntity<ResponseData<List<ParcelleMalade>>> getRecoltesByParcelleId(@PathVariable Long parcelleId) {
        List<ParcelleMalade> parcelleMalades = parcelleMaladeService.findAllHistoryMalades(parcelleId);
        if (!parcelleMalades.isEmpty()) {
            ResponseData<List<ParcelleMalade>> responseData = new ResponseData<>(parcelleMalades, HttpStatus.OK, "Retrieved parcelleMalade by Parcelle ID successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<List<ParcelleMalade>> responseData = new ResponseData<>(null, HttpStatus.OK, "No parcelleMalade found for the given Parcelle ID");
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
    }
}

