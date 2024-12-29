package travel.travel.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExeptionResponse {
    private HttpStatus httpStatus;
     private String esceptionClassName;
     private String massage;
}
