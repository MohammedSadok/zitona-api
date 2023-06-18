package ena.api.zitona.controllers;
import ena.api.zitona.entitys.Fertilisation;
import ena.api.zitona.response.ResponseData;
import ena.api.zitona.services.FertilisationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fertilisations")
public class FertilisationController {

    private final FertilisationService fertilisationService;

    public FertilisationController(FertilisationService fertilisationService) {
        this.fertilisationService = fertilisationService;
    }

    @PostMapping
    public ResponseEntity<ResponseData<Fertilisation>> createFertilisation(@RequestBody Fertilisation fertilisation) {
        Fertilisation savedFertilisation = fertilisationService.saveFertilisation(fertilisation);
        ResponseData<Fertilisation> responseData = new ResponseData<>(savedFertilisation, HttpStatus.CREATED, "Fertilisation created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }
    @GetMapping
    public ResponseEntity<ResponseData<List<Fertilisation>>> getAllMaladies() {
        List<Fertilisation> fertilisations = fertilisationService.findAllFertilisations();
        ResponseData<List<Fertilisation>> responseData = new ResponseData<>(fertilisations, HttpStatus.OK, "Retrieved all Fertilisation successfully");
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Fertilisation>> getFertilisationById(@PathVariable Long id) {
        Fertilisation fertilisation = fertilisationService.findFertilisationById(id);
        if (fertilisation != null) {
            ResponseData<Fertilisation> responseData = new ResponseData<>(fertilisation, HttpStatus.OK, "Fertilisation found");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<Fertilisation> responseData = new ResponseData<>(null, HttpStatus.NOT_FOUND, "Fertilisation not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Fertilisation>> updateFertilisation(@PathVariable Long id, @RequestBody Fertilisation fertilisation) {
        fertilisation.setId(id);
        Fertilisation updatedFertilisation = fertilisationService.updateFertilisation(fertilisation);
        ResponseData<Fertilisation> responseData = new ResponseData<>(updatedFertilisation, HttpStatus.OK, "Fertilisation updated successfully");
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Fertilisation>> deleteFertilisation(@PathVariable Long id) {
        Fertilisation fertilisation = fertilisationService.findFertilisationById(id);
        if (fertilisation != null) {
            fertilisationService.deleteFertilisation(fertilisation);
            ResponseData<Fertilisation> responseData = new ResponseData<>(fertilisation, HttpStatus.OK, "Fertilisation deleted successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<Fertilisation> responseData = new ResponseData<>(null, HttpStatus.NOT_FOUND, "Fertilisation not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }

    @GetMapping("/parcelle/{parcelleId}")
    public ResponseEntity<ResponseData<List<Fertilisation>>> findAllByParcelleId(@PathVariable Long parcelleId) {
        List<Fertilisation> fertilisations = fertilisationService.findAllByParcelleId(parcelleId);
        if (!fertilisations.isEmpty()) {

            ResponseData<List<Fertilisation>> responseData = new ResponseData<>(fertilisations, HttpStatus.OK, "Retrieved Fertilisation by Parcelle ID successfully");
            return ResponseEntity.ok(responseData);

        } else {
            ResponseData<List<Fertilisation>> responseData = new ResponseData<>(null, HttpStatus.NOT_FOUND, "No Fertilisation found for the given Parcelle ID");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }
}

