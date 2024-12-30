package travel.travel.model.dto;

import lombok.*;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SimpleResponse {
    private HttpStatus httpStatus;
    private String message;
}
