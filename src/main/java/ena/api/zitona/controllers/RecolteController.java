package ena.api.zitona.controllers;

import ena.api.zitona.entitys.Recolte;
import ena.api.zitona.response.ResponseData;
import ena.api.zitona.services.RecolteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recoltes")
public class RecolteController {

    private final RecolteService recolteService;

    public RecolteController(RecolteService recolteService) {
        this.recolteService = recolteService;
    }

    @PostMapping
    public ResponseEntity<ResponseData<Recolte>> createRecolte(@RequestBody Recolte recolte) {
        Recolte savedRecolte = recolteService.saveRecolte(recolte);
        ResponseData<Recolte> responseData = new ResponseData<>(savedRecolte, HttpStatus.CREATED, "Recolte created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<Recolte>>> getAllRecoltes() {
        List<Recolte> recoltes = recolteService.findAllRecoltes();
        ResponseData<List<Recolte>> responseData = new ResponseData<>(recoltes, HttpStatus.OK, "Retrieved all recoltes successfully");
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Recolte>> getRecolteById(@PathVariable Long id) {
        Recolte recolte = recolteService.findRecolteById(id);
        if (recolte != null) {
            ResponseData<Recolte> responseData = new ResponseData<>(recolte, HttpStatus.OK, "Retrieved recolte by ID successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<Recolte> responseData = new ResponseData<>(null, HttpStatus.OK, "Recolte not found");
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Recolte>> updateRecolte(@PathVariable Long id, @RequestBody Recolte recolte) {
        recolte.setId(id);
        Recolte updatedRecolte = recolteService.updateRecolte(recolte);
        if (updatedRecolte != null) {
            ResponseData<Recolte> responseData = new ResponseData<>(updatedRecolte, HttpStatus.OK, "Recolte updated successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<Recolte> responseData = new ResponseData<>(null, HttpStatus.OK, "Recolte not found");
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Recolte>> deleteRecolte(@PathVariable Long id) {
        Recolte recolte = recolteService.findRecolteById(id);
        if (recolte != null) {
            recolteService.deleteRecolte(recolte);
            ResponseData<Recolte> responseData = new ResponseData<>(recolte, HttpStatus.OK, "Recolte deleted successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<Recolte> responseData = new ResponseData<>(null, HttpStatus.OK, "Recolte not found");
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
    }

    @GetMapping("/parcelle/{parcelleId}")
    public ResponseEntity<ResponseData<List<Recolte>>> getRecoltesByParcelleId(@PathVariable Long parcelleId) {
        List<Recolte> recoltes = recolteService.findAllByParcelleId(parcelleId);
        if (!recoltes.isEmpty()) {
            ResponseData<List<Recolte>> responseData = new ResponseData<>(recoltes, HttpStatus.OK, "Retrieved recoltes by Parcelle ID successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<List<Recolte>> responseData = new ResponseData<>(null, HttpStatus.OK, "No recoltes found for the given Parcelle ID");
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
    }
}
