package travel.travel.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourResponseCategoryPage {
    private Long id;
    private String tourName;
    private String category;
    private String aboutTour;
    private int days;
    private int nights;
    private int price;
    private String pax;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
