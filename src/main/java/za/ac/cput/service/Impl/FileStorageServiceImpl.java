package za.ac.cput.service.Impl;

// za.ac.cput.service.Impl.FileStorageServiceImpl (Example)

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.service.IFileStorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements IFileStorageService {

    // Define a root directory for file uploads
    private final Path fileStorageLocation = Paths.get("./uploads/documents").toAbsolutePath().normalize();

    public FileStorageServiceImpl() {
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String storeFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        // Normalize file name and resolve the path
        Path targetLocation = this.fileStorageLocation.resolve(fileName);

        // Copy file to the target location
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        // Return the full path (or a URL/reference for a production application)
        return targetLocation.toString();
    }
}
