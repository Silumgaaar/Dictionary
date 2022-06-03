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
    public MenuDictionaryController(MenuDictionaryService menuDictionaryService) {
        this.menuDictionaryService = menuDictionaryService;
    }

    @GetMapping("")
    public String startMenu(@RequestParam("id") String id, Model model) {
        model.addAttribute("id", id);
        return "menu/dictionary";
    }

    @GetMapping("/add")
    public String add(@RequestParam(value = "id") String id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("form", new FormAdd());

        return "menu/command/add";
    }

    @PostMapping("/add")
    public String addPair(@ModelAttribute FormAdd formAdd, Model model) {

        model.addAttribute("id", formAdd.getId());
        model.addAttribute("result", menuDictionaryService.addPair(formAdd.getId(), formAdd.getKey(), formAdd.getValue()));
        model.addAttribute("form", formAdd);

        return "menu/command/add";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id") String id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("form", new FormDelete());
        return "menu/command/delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute FormDelete formDelete, Model model) {
        model.addAttribute("id", formDelete.getId());
        model.addAttribute("answer", menuDictionaryService.deletePairByKey(formDelete.getId(), formDelete.getKey()));
        model.addAttribute("form", formDelete);

        return "menu/command/delete";
    }

    @GetMapping("/view")
    public String view(@RequestParam("id") String id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("menus", menuDictionaryService.findAllPairDictionary(id));
        return "menu/command/view";
    }

    @GetMapping("/search")
    public String search(@RequestParam("id") String id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("form", new FormSearch());
        return "menu/command/search";
    }

    @GetMapping("/search/result")
    public String search(@ModelAttribute FormSearch formSearch, Model model) {
        model.addAttribute("id", formSearch.getId());
        model.addAttribute("form", formSearch);
        model.addAttribute("result", menuDictionaryService.findPairDictionaryByKey(formSearch.getId(), formSearch.getKey()));
        return "menu/command/search";
    }
}
