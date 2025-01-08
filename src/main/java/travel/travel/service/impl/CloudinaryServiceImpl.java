package travel.travel.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import travel.travel.config.cloudinary.CloudinaryConfig;
import travel.travel.exception.BadRequestExeption;
import travel.travel.service.CloudinaryService;

import java.io.File;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(CloudinaryConfig config) {
        this.cloudinary = config.getCloudinary();
    }
    @Override
    public Map<String, Object> uploadFile(String filePath) throws Exception {
        if (filePath == null || filePath.isBlank()) {
            throw new BadRequestExeption("Path to file cannot be empty.");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new BadRequestExeption("File not found: " + filePath);
        }

        // Проверяем формат файла
        String fileExtension = getFileExtension(file);
        if (!isValidFileExtension(fileExtension)) {
            throw new BadRequestExeption("Invalid file format. Allowed formats: jpg, jpeg, png.");
        }

        return cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndex = name.lastIndexOf(".");
        return (lastIndex > 0) ? name.substring(lastIndex + 1).toLowerCase() : "";
    }

    private boolean isValidFileExtension(String fileExtension) {
        return fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png");
    }

    @Override
    public Map<String, Object> getFileDetails(String publicId) throws Exception {
        if (publicId == null || publicId.isBlank()) {
            throw new BadRequestExeption("id can not be empty or null.");
        }
        return cloudinary.api().resource(publicId, ObjectUtils.emptyMap());
    }

    @Override
    public Map<String, Object> deleteFile(String publicId) throws Exception {
        if (publicId == null || publicId.isBlank()) {
            throw new BadRequestExeption("id can not be empty or null.");
        }
        return cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
}
