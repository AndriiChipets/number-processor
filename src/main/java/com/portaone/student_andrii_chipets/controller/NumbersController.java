package com.portaone.student_andrii_chipets.controller;

import com.portaone.student_andrii_chipets.dto.NumbersStatisticDto;
import com.portaone.student_andrii_chipets.service.NumbersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class NumbersController {

    private NumbersService service;

    @GetMapping("/")
    public String homepage() {
        return "index";
    }

    @PostMapping("/process")
    public String processFile(Model model, @RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to process.");
            return "redirect:/";
        }

        NumbersStatisticDto result = service.processNumbers(file);
        System.out.println(result);
        model.addAttribute("result", result);
        attributes.addFlashAttribute("message", "You successfully processed numbers!");

        return "index";
    }
}
