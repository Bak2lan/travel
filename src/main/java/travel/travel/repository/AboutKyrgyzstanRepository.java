package travel.travel.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import travel.travel.model.entity.AboutKyrgyzstan;

import java.util.List;

@Repository
@Transactional
public interface AboutKyrgyzstanRepository extends JpaRepository<AboutKyrgyzstan, Long> {
    @Query("select c from AboutKyrgyzstan c where c.type= 'CULTURE'")
    List<AboutKyrgyzstan> getCulture();

    @Query("select c from AboutKyrgyzstan c where c.type= 'TRADITIONAL'")
    List<AboutKyrgyzstan> getTradition();

    @Query("select c from AboutKyrgyzstan c where c.type= 'HISTORICAL_PLACES'")
    List<AboutKyrgyzstan> getHistoricalPlaces();
}
