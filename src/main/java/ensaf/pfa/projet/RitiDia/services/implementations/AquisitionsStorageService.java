package ensaf.pfa.projet.RitiDia.services.implementations;

import ensaf.pfa.projet.RitiDia.services.interfaces.IAquisitionService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class AquisitionsStorageService implements IAquisitionService {
    private final Path root = Paths.get("uploads");
    @Override
    public void init() {
        try{
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file) {

        try {
            Files.copy(file.getInputStream(),
                            this.root.resolve(file.getOriginalFilename()));

        } catch (IOException e) {
            throw new RuntimeException("could not store the file. Error: "+e.getMessage()+" caused by: "+e.getCause());
        }
    }

    @Override
    public Resource load(String filename) {

        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }
}
