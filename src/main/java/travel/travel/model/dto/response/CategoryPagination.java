package travel.travel.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPagination {
    private int currentPage;
    private int pageSize;
    private List<CategoryResponseForGetAll> categories;
}
