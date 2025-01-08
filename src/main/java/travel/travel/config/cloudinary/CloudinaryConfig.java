package travel.travel.config.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class CloudinaryConfig {
    private final Cloudinary cloudinary;
    public CloudinaryConfig() {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dzlehgenv",   // Замените на ваши данные
                "api_key", "995124799835397",
                "api_secret", "zgysPKntCCZBoSmFPoF5WKy1te8"
        ));
    }

}
