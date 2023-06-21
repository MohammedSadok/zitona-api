package ena.api.zitona.repositorys;

import ena.api.zitona.entitys.Recolte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RecolteRepository extends JpaRepository<Recolte, Long> {
    List<Recolte> findAllByParcelleId(Long parcelleId);

    // New method to update Recolte entity without deleting it
    @Modifying
    @Query("UPDATE Recolte r SET r.commentaire = :commentaire, r.cout = :cout,r.date=:date" +
            ",r.methode=:methode,r.qualite=:qualite,r.quantite=:quantite WHERE r.id = :id")
    void updateRecolte(@Param("id") Long id, @Param("commentaire") String commentaire
            , @Param("cout") float cout, @Param("date") Date date, @Param("methode") String methode
            , @Param("qualite") String qualite, @Param("quantite") double quantite);

    @Query("SELECT SUM(r.quantite) FROM Recolte r WHERE r.parcelle.id = :parcelleId")
    double calculateTotalQuantiteByParcelleId(@Param("parcelleId") Long parcelleId);
}
