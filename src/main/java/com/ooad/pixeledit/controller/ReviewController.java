package com.ooad.pixeledit.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.ooad.pixeledit.models.Review;   
import com.ooad.pixeledit.repository.ReviewRepository;
import java.util.Map;
// import substring from String
import java.lang.String;
import java.util.ArrayList;
//import javax.servlet.http.HttpServletRequest;

import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    // Function to see all images needing review
    @GetMapping("/review_images")
    public String reviewImages(Model model) {
        List<Review> pendingReviews = reviewRepository.findByIsApprovedIsNull();
    model.addAttribute("reviews", pendingReviews);
        return "review_images";
    }
    /*
    // the number of parameters passed is unkmown and depends on number of items which has isApproved as No in reviewrepository
    @PostMapping("/review")
  public String submitReviews(HttpServletRequest request) {
    String[] reviewIds = request.getParameterValues("reviewId");
    if (reviewIds != null) {
      for (String reviewId : reviewIds) {
        Review review = reviewRepository.findById(Long.parseLong(reviewId)).orElseThrow();
        review.setIsApproved(Boolean.parseBoolean(request.getParameter("isApproved-" + reviewId)));
        reviewRepository.save(review);
      }
    }
    return "redirect:/reviewImages";

        
    }
    */

   @GetMapping("/review")
  public String submitReviews(Model model, @RequestParam Map<String, String> requestParams) {
    System.out.println("inside");
    List<String> reviewIds = new ArrayList<>();
    for (String key: requestParams.keySet()) {
            // add to reviewIds list
            // check if the key starts with reviewId
            if (key.startsWith("reviewId")) {
                reviewIds.add(key);
            }
            
        }
    //String[] reviewIds = requestParams.get("reviewId");
    List<Review> pendingReviews = reviewRepository.findByIsApprovedIsNull();
    int i = 0;
    if (reviewIds != null) {
      for (String reviewId : reviewIds) {
        //reviewId = "reviewId" + "1"
        // get only the number from the string
        System.out.println(reviewId.substring(9));
        Review review = reviewRepository.findById(Long.parseLong(reviewId.substring(9))).orElseThrow();
        review.setIsApproved(Boolean.parseBoolean(requestParams.get("isApproved-" + review.getId())));
        reviewRepository.save(review);
        System.out.println(review.getId());
      }
    }
    return "redirect:/reviewImages";

        
    }

    }
