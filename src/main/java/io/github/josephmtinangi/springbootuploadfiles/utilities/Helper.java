package io.github.josephmtinangi.springbootuploadfiles.utilities;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class Helper {

    public static String saveFile(MultipartFile file, String destination, String rootStorage) {

        String fileName = "";
        String fileHashName = "";

        new File(rootStorage + "/" + destination).mkdirs();

        try {
            byte[] bytes = file.getBytes();

            fileName = file.getOriginalFilename();

            String fileNameTokens[] = fileName.split("\\.");

            fileHashName = destination + "/" + UUID.randomUUID().toString();

            if (fileNameTokens.length > 1) {
                fileHashName += "." + fileNameTokens[fileNameTokens.length - 1];
            }

            Path path = Paths.get(rootStorage + "/" + fileHashName);

            Files.write(path, bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileHashName;
    }
}
