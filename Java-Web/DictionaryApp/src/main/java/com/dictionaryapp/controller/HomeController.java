package com.dictionaryapp.controller;

import com.dictionaryapp.model.view.WordViewModel;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final WordService wordService;

    public HomeController(LoggedUser loggedUser, WordService wordService) {
        this.loggedUser = loggedUser;
        this.wordService = wordService;
    }

    @GetMapping("/")
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        List<WordViewModel> allWords = wordService.getAllWords();

        List<WordViewModel> germanWords = new ArrayList<>();
        List<WordViewModel> spanishWords = new ArrayList<>();
        List<WordViewModel> frenchWords = new ArrayList<>();
        List<WordViewModel> italianWords = new ArrayList<>();

        for (WordViewModel word : allWords) {
            switch (word.getLanguage()) {
                case "GERMAN" -> germanWords.add(word);
                case "SPANISH" -> spanishWords.add(word);
                case "FRENCH" -> frenchWords.add(word);
                case "ITALIAN" -> italianWords.add(word);
            }
        }

        model.addAttribute("germanWords", germanWords)
                .addAttribute("spanishWords", spanishWords)
                .addAttribute("frenchWords", frenchWords)
                .addAttribute("italianWords", italianWords)
                .addAttribute("size", allWords.size());

        return "home";
    }

}
