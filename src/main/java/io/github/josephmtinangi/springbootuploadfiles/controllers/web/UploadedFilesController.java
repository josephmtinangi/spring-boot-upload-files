package io.github.josephmtinangi.springbootuploadfiles.controllers.web;

import io.github.josephmtinangi.springbootuploadfiles.repositories.UploadedFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping(path = "/files")
public class UploadedFilesController {

    private static final Logger log = LoggerFactory.getLogger(UploadedFilesController.class);

    @Value("${ROOT_STORAGE}")
    private String rootStorage;

    @Autowired
    private UploadedFileRepository uploadedFileRepository;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("files", uploadedFileRepository.findAll());

        return "uploaded-files/index";
    }

    @RequestMapping(path = "/{location}/{filename:.+}")
    public ResponseEntity<Resource> download(@PathVariable String location, @PathVariable String filename) throws IOException {

        log.info("location = " + location);

        log.info("filename = " + filename);

        String filePath = rootStorage + "/" + location + "/" + filename;

        log.info("filePath = " + filePath);

        File file = new File(filePath);

        Path path = Paths.get(file.getAbsolutePath());

        log.info("path = " + path);

        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());

        if (mimeType == null) {
            mimeType = "application/octect-stream";
        }

        return ResponseEntity.ok().contentLength(file.length()).contentType(MediaType.parseMediaType(mimeType)).body(resource);
    }
}
