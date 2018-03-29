package io.github.josephmtinangi.springbootuploadfiles.controllers.api;

import io.github.josephmtinangi.springbootuploadfiles.repositories.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

@Controller
@RequestMapping(path = "/api")
public class StatsController {

    @Autowired
    private UploadedFileRepository uploadedFileRepository;

    @RequestMapping(path = "/stats", method = RequestMethod.GET)
    public ResponseEntity<?> index() {

        HashMap<String, Object> stats = new HashMap<>();
        stats.put("total_files", uploadedFileRepository.count());

        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
