package io.github.josephmtinangi.springbootuploadfiles.controllers.web;

import io.github.josephmtinangi.springbootuploadfiles.repositories.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/files")
public class UploadedFilesController {

    @Autowired
    private UploadedFileRepository uploadedFileRepository;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("files", uploadedFileRepository.findAll());

        return "uploaded-files/index";
    }
}
