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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.nio.file.Paths;
import com.ooad.pixeledit.models.Review;
import com.ooad.pixeledit.repository.ReviewRepository;

@Controller
public class ImageController {

  @Autowired
    FilesStorageService storageService;

  @Autowired
  ReviewRepository reviewRepository;

    
    @GetMapping("/images")
    public String newImage(Model model) {
      return "upload_form";
    }
    @PostMapping("/images/upload")
  public String uploadImage(Model model, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
    String message = "";

    try {
      storageService.save(file);

      message = "Uploaded the image successfully: " + file.getOriginalFilename();
      model.addAttribute("message", message);
      // create review tih isApproved as null
      Review review = new Review(file.getOriginalFilename());
      reviewRepository.save(review);
      System.out.println(review.getId());
      System.out.println(review.getIsApproved());
      System.out.println(review.getImageTitle());

    } catch (Exception e) {
      message = "Could not upload the image: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
      model.addAttribute("message", message);
      System.out.println(message);
    }
    
    String path = Paths.get("./uploads") + "//" + file.getOriginalFilename();
    redirectAttributes.addFlashAttribute("flashAttr", file.getOriginalFilename());
    return "redirect:/editimage";
  }

@GetMapping("/download")
  public ResponseEntity<Resource> getImage(@RequestParam String path) {
    path = path.substring(11);
    System.out.println(path);
    Resource file = storageService.load(path);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }

    
}
