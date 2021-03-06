package io.github.josephmtinangi.springbootuploadfiles.controllers.web;

import com.pusher.rest.Pusher;
import io.github.josephmtinangi.springbootuploadfiles.models.UploadedFile;
import io.github.josephmtinangi.springbootuploadfiles.repositories.UploadedFileRepository;
import io.github.josephmtinangi.springbootuploadfiles.utilities.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

@Controller
@RequestMapping(path = "/")
public class WelcomeController {

    @Value("${ROOT_STORAGE}")
    private String rootStorage;

    @Value("${PUSHER_APP_ID}")
    private String appId;

    @Value("${PUSHER_APP_KEY}")
    private String appKey;

    @Value("${PUSHER_APP_SECRET}")
    private String appSecret;

    @Autowired
    private UploadedFileRepository uploadedFileRepository;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("files", uploadedFileRepository.findAll());

        return "welcome/index";
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<?> store(@RequestParam("file") MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        String path = Helper.saveFile(file, "uploads", rootStorage);

        UploadedFile uploadedFile = new UploadedFile();
        uploadedFile.setOriginalName(file.getOriginalFilename());
        uploadedFile.setPath(path);
        uploadedFileRepository.save(uploadedFile);

        Pusher pusher = new Pusher(appId, appKey, appSecret);
        pusher.setCluster("ap2");
        pusher.setEncrypted(true);

        pusher.trigger("stats", "file-uploaded", Collections.singletonMap("message", "File uploaded"));

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
