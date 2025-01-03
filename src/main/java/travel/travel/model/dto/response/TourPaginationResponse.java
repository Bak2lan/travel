package travel.travel.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TourPaginationResponse {
    private List<TourResponseForPagination> tourResponses;
    private int currentPage;
    private int pageSize;
}
