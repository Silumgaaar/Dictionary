package ru.yarkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.yarkin.service.MenuDictionariesService;

@Controller
@RequestMapping("/start")
public class MenuDictionariesController {

    private final MenuDictionariesService menuDictionariesService;

    @Autowired
    public MenuDictionariesController(MenuDictionariesService menuDictionariesService) {
        this.menuDictionariesService = menuDictionariesService;
    }

    @GetMapping("/menu")
    public String startMenu(Model model) {
        model.addAttribute("nameDictionary", menuDictionariesService.findAllDictionaries());
        return "menu/dictionaries";
    }
}
