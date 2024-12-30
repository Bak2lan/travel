package travel.travel.model.dto.response;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
public class SimpleResponse {
    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;

}
