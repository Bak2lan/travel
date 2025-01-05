package travel.travel.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.travel.model.entity.AboutKyrgyzstan;

@Repository
@Transactional
public interface AboutKyrgyzstanRepository extends JpaRepository<AboutKyrgyzstan, Long> {
}
