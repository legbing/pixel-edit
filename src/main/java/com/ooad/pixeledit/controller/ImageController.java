package com.ooad.pixeledit.controller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ooad.pixeledit.service.FilesStorageService;

@Controller
public class ImageController {

  @Autowired
    FilesStorageService storageService;

    
    @GetMapping("/images")
    public String newImage(Model model) {
      return "upload_form";
    }
    @PostMapping("/images/upload")
  public String uploadImage(Model model, @RequestParam("file") MultipartFile file) {
    String message = "";

    try {
      storageService.save(file);

      message = "Uploaded the image successfully: " + file.getOriginalFilename();
      model.addAttribute("message", message);
    } catch (Exception e) {
      message = "Could not upload the image: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
      model.addAttribute("message", message);
    }

    return "upload_form";
  }


    
}
