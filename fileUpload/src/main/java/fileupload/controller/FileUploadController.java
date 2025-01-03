package fileupload.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fileupload.service.FileUploadService;

@Controller
public class FileUploadController {
    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @RequestMapping({"/", ""})
    public String viewForm() {
        return "form";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(
        @RequestParam("email") String email,
        @RequestParam("file") MultipartFile file,
        Model model
    ) throws IOException {
        System.out.println(email);

        model.addAttribute("url", fileUploadService.restore(file));

        return "result";
    }
}
