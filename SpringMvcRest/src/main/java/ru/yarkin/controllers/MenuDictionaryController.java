package ru.yarkin.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yarkin.models.form.FormAdd;
import ru.yarkin.models.form.FormDelete;
import ru.yarkin.models.form.FormSearch;
import ru.yarkin.service.MenuDictionaryService;

@Controller
@RequestMapping("/dictionary")
public class MenuDictionaryController {
    private final MenuDictionaryService menuDictionaryService;

    @Autowired
    public MenuDictionaryController(MenuDictionaryService menuDictionaryService){
        this.menuDictionaryService = menuDictionaryService;
    }

    @GetMapping("")
    public String startMenu(@RequestParam("id") Long id, Model model){
        model.addAttribute("id",id);
        return "menu/dictionary";
    }

    @GetMapping("/add")
    public String add(@RequestParam("id") Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("form", new FormAdd());
        model.addAttribute("name", menuDictionaryService.findDictionaryById(id));
        model.addAttribute("sourceRule", menuDictionaryService.getSourceLanguageName(id));
        model.addAttribute("targetRule", menuDictionaryService.getTargetLanguageName(id));

        return "menu/command/add";
    }
    @PostMapping("/add")
    public String addPair(@ModelAttribute FormAdd formAdd, Model model){

        model.addAttribute("id",formAdd.getId());
        model.addAttribute("name", menuDictionaryService.findDictionaryById(formAdd.getId()));
        model.addAttribute("result", menuDictionaryService.addPair(formAdd.getId(),formAdd.getKey(),formAdd.getValue()));
        model.addAttribute("form", formAdd);
        model.addAttribute("sourceRule", menuDictionaryService.getSourceLanguageName(formAdd.getId()));
        model.addAttribute("targetRule", menuDictionaryService.getTargetLanguageName(formAdd.getId()));
        return "menu/command/add";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id") Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("form", new FormDelete());
        model.addAttribute("name", menuDictionaryService.findDictionaryById(id));
        return "menu/command/delete";
    }


    @PostMapping("/delete")
    public String delete(@ModelAttribute FormDelete formDelete, Model model){
        model.addAttribute("id", formDelete.getId());
        model.addAttribute("name", menuDictionaryService.findDictionaryById(formDelete.getId()));
        model.addAttribute("answer", menuDictionaryService.deletePair(formDelete.getId(),formDelete.getKey()));
        model.addAttribute("form", formDelete);

        return "menu/command/delete";
    }

    @GetMapping("/view")
    public String view(@RequestParam("id") Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("menus", menuDictionaryService.findAllPairDictionary(id));
        return "menu/command/view";
    }

    @GetMapping("/search")
    public String search(@RequestParam("id") Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("name", menuDictionaryService.findDictionaryById(id));
        model.addAttribute("form", new FormSearch());
        return "menu/command/search";
    }
    @GetMapping("/search/result")
    public String search(@ModelAttribute FormSearch formSearch, Model model){
        model.addAttribute("id", formSearch.getId());
        System.out.println(formSearch.getId());
        model.addAttribute("name", menuDictionaryService.findDictionaryById(formSearch.getId()));
        model.addAttribute("form", formSearch);
        model.addAttribute("result", menuDictionaryService.searchPair(formSearch.getId(),formSearch.getKey()));
        return "menu/command/search";
    }
}
