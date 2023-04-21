package com.ooad.pixeledit.controller;

import com.ooad.pixeledit.dto.UserDto;
import com.ooad.pixeledit.models.Filter;
import com.ooad.pixeledit.repository.FilterRepository;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;  
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


@Controller
public class FilterController
{
    @Autowired
    FilterRepository filterRepository;

    @GetMapping("/addfilter")
    public String addFilter(Model model)
    {
        model.addAttribute("filter", new Filter());
        return "addfilter";
    }

    @PostMapping("/add_filter/save")
    public String save(@RequestParam String filterType, @RequestParam String filterName, @RequestParam int kernel_1, @RequestParam int kernel_2, 
    @RequestParam int kernel_3, @RequestParam int kernel_4, @RequestParam int kernel_5, @RequestParam int kernel_6, @RequestParam int kernel_7,
     @RequestParam int kernel_8, @RequestParam int kernel_9,
                               Model model){
        Filter filter = new Filter();
        filter.setFilter_name(filterName);
        filter.setFilter_type(filterType);
        filter.setKernel(kernel_1, kernel_2, kernel_3, kernel_4, kernel_5, kernel_6, kernel_7, kernel_8, kernel_9);
        filterRepository.save(filter);

        return "redirect:/users?success";
    }

    @GetMapping("/editimage")
    public String editImage(Model model, @ModelAttribute("flashAttr") String path)
    {
        // option to apply filters available here
        System.out.println(path);
        // display all filter options
        List<Filter> filters = filterRepository.findAll();
        System.out.println(filters);
        model.addAttribute("filters", filters);
        // check if path starts with uploads
        if(!path.startsWith(".\\uploads"))
        {
            path = Paths.get("./uploads") + "//" + path;
        }
        model.addAttribute("path", path);
        return "edit";
    }

    @GetMapping("/apply")
    public String apply(@RequestParam String filterType, @RequestParam String filterName, @RequestParam String path, Model model)
    {
        // apply filter to image
        System.out.println(path);
        /*List<Filter> filters = filterRepository.findByName(filter_name);
        filters.forEach(filter -> {
            if(filter.getFilter_type().equals(filter_type))
            {
                BufferedImage image = ImageIO.read("1.jpg");
                BufferedImage edited = filter.applyFilter();
                ImageIO.write(edited, "jpg", new File("1.jpg"));
                return "edit";
            }
           
        });
        */
        Filter filter = filterRepository.findByFilterType(filterType);
        if(filter.getFilter_name().equals(filterName))
        {
            
            BufferedImage img = null;
            try {
                //path = Paths.get("./uploads") + "//" + path;
                img = ImageIO.read(new File(path));
            } 
            catch (IOException e) {
            }

            Filter edge = filter.createFilter(filterType, filterName);
            BufferedImage edited = edge.applyFilter(img);
            try {
                ImageIO.write(edited, "jpg", new File(path));

            }
            catch (IOException e) {
            }
            //ImageIO.write(edited, "jpg", new File("1.jpg"));
            
        }
        return "redirect:/editimage?success";
    }
}
