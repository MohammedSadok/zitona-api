package ena.api.zitona.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class Parcelle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private int nombreDarbre;
    @Enumerated(EnumType.STRING)
    private Variete varieter;
    private String locatisation;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateDePlantation;
    private String commentaire;
    private float debit;


    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @OneToMany(mappedBy = "parcelle",fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection <Recolte> recoltes;


    @OneToMany(mappedBy = "parcelle",fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection <Traitement> traitements;


    @OneToMany(mappedBy = "parcelle",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection <ParcelleMalade> parcelleMalades;
}
