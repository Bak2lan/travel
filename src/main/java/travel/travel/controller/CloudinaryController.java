package travel.travel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import travel.travel.exception.BadRequestExeption;
import travel.travel.service.CloudinaryService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cloudinary")
@Tag(name = "REST APIs for Files",
        description = "REST APIs to Upload, get and delete files")
public class CloudinaryController {
    private final CloudinaryService cloudinaryService;

    public CloudinaryController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @Operation(summary = "Upload a file to Cloudinary")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> uploadFile(
            @RequestParam("file") MultipartFile multipartFile) {
        try {
            if (multipartFile == null || multipartFile.isEmpty()) {
                throw new BadRequestExeption("File cannot be null or empty.");
            }
            System.out.println("Received file: " + multipartFile.getOriginalFilename());
            Path tempFile = Files.createTempFile(null, multipartFile.getOriginalFilename());
            multipartFile.transferTo(tempFile.toFile());
            Map<String, Object> response = cloudinaryService.uploadFile(tempFile.toString());
            Files.delete(tempFile);
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file: " + e.getMessage());
        }
    }

    @Operation(summary = "Get file by publicId")
    @GetMapping("/getFile/{publicId}")
    public Map<String, Object> getFileDetails(@PathVariable String publicId) throws Exception {
        return cloudinaryService.getFileDetails(publicId);
    }

    @Operation(summary = "Delete file by publicId")
    @DeleteMapping("/deleteFile/{publicId}")
    public Map<String, Object> deleteFile(@PathVariable String publicId) throws Exception {
        return cloudinaryService.deleteFile(publicId);
    }
}
