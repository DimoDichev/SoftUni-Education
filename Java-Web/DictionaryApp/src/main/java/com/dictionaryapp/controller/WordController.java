package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.WordAddDto;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/words")
public class WordController {

    private final LoggedUser loggedUser;
    private final WordService wordService;


    public WordController(LoggedUser loggedUser, WordService wordService) {
        this.loggedUser = loggedUser;
        this.wordService = wordService;
    }

    @GetMapping("/add")
    public String add() {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        return "word-add";
    }

    @PostMapping("add")
    public String add(@Valid WordAddDto wordAddDto,
                      BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("wordAddDto", wordAddDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.wordAddDto", bindingResult);
            return "redirect:add";
        }

        wordService.add(wordAddDto);

        return "redirect:/home";
    }

    @GetMapping("remove/{id}")
    public String delete(@PathVariable Long id) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        wordService.remove(id);

        return "redirect:/home";
    }

    @GetMapping("removeAll")
    public String delete() {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        wordService.removeAll();
        return "redirect:/home";
    }

    @ModelAttribute
    public WordAddDto wordAddDto() {
        return new WordAddDto();
    }
}
