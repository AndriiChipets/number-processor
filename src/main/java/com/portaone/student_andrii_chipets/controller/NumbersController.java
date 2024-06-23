package com.portaone.student_andrii_chipets.controller;

import com.portaone.student_andrii_chipets.dto.NumbersStatisticDto;
import com.portaone.student_andrii_chipets.service.NumbersService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@Log4j2
public class NumbersController {

    private final static String EMPTY_FILE_MESSAGE = "Please select the file to process";
    private NumbersService service;

    @GetMapping("/")
    public String homepage() {
        return "index";
    }

    @PostMapping("/process")
    public String processFile(Model model, @RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", EMPTY_FILE_MESSAGE);
            log.warn("Empty file: " + "{}", file.getOriginalFilename());
            return "redirect:/";
        }

        NumbersStatisticDto result = service.processNumbers(file);
        model.addAttribute("result", result);
        log.info("File processed successfully : " + "{}", file.getOriginalFilename());

        return "index";
    }
}
