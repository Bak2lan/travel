package travel.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.travel.model.entity.AboutKyrgyzstan;
@Repository
public interface AboutKyrgyzstanRepository extends JpaRepository<AboutKyrgyzstan, Long> {
}
