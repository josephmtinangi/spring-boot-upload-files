package io.github.josephmtinangi.springbootuploadfiles.controllers;

import io.github.josephmtinangi.springbootuploadfiles.utilities.Helper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "/")
public class WelcomeController {

    @Value("${settings.rootStorage}")
    private String rootStorage;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("files", new ArrayList<>());

        return "welcome/index";
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<?> store(@RequestParam("file") MultipartFile file) {

        if (file != null && !file.isEmpty()) {
            Helper.saveFile(file, "uploads", rootStorage);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
