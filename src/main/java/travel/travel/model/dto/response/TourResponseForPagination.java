package travel.travel.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
public class TourResponseForPagination {
    private Long id;
    private String tourName;
    private String aboutTour;
    private int daysByCategory;
    private int nights;
    private int price;
    private String pax;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private List<String> tourImages;
}