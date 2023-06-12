package ena.api.zitona.controllers;

import ena.api.zitona.entitys.Maladie;
import ena.api.zitona.response.ResponseData;
import ena.api.zitona.services.MaladieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maladies")
public class MaladieController {
    private final MaladieService maladieService;

    public MaladieController(MaladieService maladieService) {
        this.maladieService = maladieService;
    }

    @PostMapping
    public ResponseEntity<ResponseData<Maladie>> createMaladie(@RequestBody Maladie maladie) {
        Maladie savedMaladie = maladieService.saveMaladie(maladie);
        ResponseData<Maladie> responseData = new ResponseData<>(savedMaladie, HttpStatus.CREATED, "Maladie created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<Maladie>>> getAllMaladies() {
        List<Maladie> maladies = maladieService.findAllMaladies();
        ResponseData<List<Maladie>> responseData = new ResponseData<>(maladies, HttpStatus.OK, "Retrieved all maladies successfully");
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Maladie>> getMaladieById(@PathVariable Long id) {
        Maladie maladie = maladieService.findMaladieById(id);
        if (maladie != null) {
            ResponseData<Maladie> responseData = new ResponseData<>(maladie, HttpStatus.OK, "Retrieved maladie by ID successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<Maladie> responseData = new ResponseData<>(null, HttpStatus.NOT_FOUND, "Maladie not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Maladie>> updateMaladie(@PathVariable Long id, @RequestBody Maladie maladie) {
        maladie.setId(id);
        Maladie updatedMaladie = maladieService.updateMaladie(maladie);
        if (updatedMaladie != null) {
            ResponseData<Maladie> responseData = new ResponseData<>(updatedMaladie, HttpStatus.OK, "Maladie updated successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<Maladie> responseData = new ResponseData<>(null, HttpStatus.NOT_FOUND, "Maladie not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Maladie>> deleteMaladie(@PathVariable Long id) {
        Maladie maladie = maladieService.findMaladieById(id);
        if (maladie != null) {
            maladieService.deleteMaladie(maladie);
            ResponseData<Maladie> responseData = new ResponseData<>(maladie, HttpStatus.OK, "Maladie deleted successfully");
            return ResponseEntity.ok(responseData);
        } else {
            ResponseData<Maladie> responseData = new ResponseData<>(null, HttpStatus.NOT_FOUND, "Maladie not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }
}
