package ena.api.zitona.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraitementPhytosanitaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Maladie maladie;

    private String nomProduit; // Nom du produit utilisé pour le traitement
    private String dose; // Dose recommandée pour le traitement
    private int frequence; // Fréquence d'application du traitement (en jours/semaines/mois)
    private String duree; // Durée du traitement (sous forme de chaîne de caractères)
}
