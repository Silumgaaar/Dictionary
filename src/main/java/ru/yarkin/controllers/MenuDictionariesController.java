package ru.yarkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yarkin.dao.interfaces.LibrariesDao;


@Controller
@RequestMapping("/start")
public class MenuDictionariesController {

    private final LibrariesDao menuDictionaries;

    @Autowired
    public MenuDictionariesController(LibrariesDao menuDictionaries){
        this.menuDictionaries = menuDictionaries;
    }
    @GetMapping("/menu")
    public String startMenu(Model model){
        model.addAttribute("menus", menuDictionaries.findAllDictionary());
        return "menu/dictionaries";
    }
}
