package fileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
    @RequestMapping({"/", ""})
    public String viewForm() {
        return "form";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(
        @RequestParam("email") String email,
        @RequestParam("file1") MultipartFile file1,
        @RequestParam("file2") MultipartFile file2
    ) {
        System.out.println(email);
        System.out.println(file1.getOriginalFilename());
        System.out.println(file2.getOriginalFilename());

        return "form";
    }
}
