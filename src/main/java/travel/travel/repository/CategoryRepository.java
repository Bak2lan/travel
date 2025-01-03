package travel.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.travel.model.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}