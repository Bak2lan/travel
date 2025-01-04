package travel.travel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import travel.travel.model.dto.response.CategoryPagination;
import travel.travel.model.dto.response.CategoryResponseForPagination;
import travel.travel.model.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("select new travel.travel.model.dto.response.CategoryResponseForPagination(c.id,c.dayTour,c.image) from Category c")
    Page<CategoryResponseForPagination>getAllCategory(Pageable pageable);
}