package ru.yarkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.yarkin.service.DictionaryService;

@Controller
@RequestMapping("/start")
public class StartMenuController {

    private final DictionaryService dictionaryService;

    @Autowired
    public StartMenuController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/menu")
    public String startMenu(Model model) {
        model.addAttribute("nameDictionary", dictionaryService.findAllDictionaries());
        return "menu/dictionaries";
    }
}
