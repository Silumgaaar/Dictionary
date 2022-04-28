package ru.yarkin.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.yarkin.dao.interfaces.LibrariesDao;
import ru.yarkin.dao.interfaces.TranslateDao;
import ru.yarkin.models.form.FormAdd;
import ru.yarkin.models.form.FormDelete;
import ru.yarkin.models.form.FormSearch;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dictionary")
public class MenuDictionaryController {
    private static final String SUCCESSFULLY_FOUND = "Пара успешно найдена";
    private static final String NOT_PAIR = "Пара не найдена";
    private static final String SUCCESSFUL_REMOVAL = "Удаление прошло успешно";
    private final LibrariesDao menuDictionary;
    private final TranslateDao translateDao;

    @Autowired
    public MenuDictionaryController(LibrariesDao menuDictionary, TranslateDao translateDao){
        this.menuDictionary = menuDictionary;
        this.translateDao = translateDao;
    }

    @GetMapping("")
    public String startMenu(@RequestParam("id") Long id, Model model){
        model.addAttribute("id",id);
        return "menu/dictionary";
    }

    @GetMapping("/add")
    public String add(@RequestParam("id") Long id, Model model){
        model.addAttribute("form", new FormAdd());
        model.addAttribute("name", menuDictionary.findDictionary(id));

        return "menu/command/add";
    }
    @PostMapping("/add")
    public String addPair(@ModelAttribute FormAdd formAdd,
                          Model model){
        List<String> result = translateDao.add(formAdd);
        model.addAttribute("name", menuDictionary.findDictionary(formAdd.getId()));
        model.addAttribute("result", result);
        model.addAttribute("form", formAdd);
        return "menu/command/add";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id") Long id, Model model){
        model.addAttribute("form", new FormDelete());
        model.addAttribute("name", menuDictionary.findDictionary(id));
        return "menu/command/delete";
    }


    @PostMapping("/delete")
    public String delete(@ModelAttribute FormDelete formDelete,
                         @RequestParam(value = "id") Long id, Model model){

        List<String> answer = new ArrayList<>();

        if(translateDao.delete(formDelete)){
            answer.add(SUCCESSFUL_REMOVAL);
        }
        else {
            answer.add(NOT_PAIR);
        }
        model.addAttribute("name", menuDictionary.findDictionary(formDelete.getId()));
        model.addAttribute("answer", answer);
        model.addAttribute("form", formDelete);

        return "menu/command/delete";
    }

    @GetMapping("/view")
    public String view(@RequestParam("id") Long id, Model model){
        model.addAttribute("name", menuDictionary.findDictionary(id));
        model.addAttribute("menus", translateDao.findAllPair(id));
        return "menu/command/view";
    }

    @GetMapping("/search")
    public String search(@RequestParam("id") Long id, Model model){
        model.addAttribute("name", menuDictionary.findDictionary(id));
        model.addAttribute("form", new FormSearch());
        return "menu/command/search";
    }
    @GetMapping("/search/result")
    public String search(@ModelAttribute FormSearch formSearch,
                         Model model){
        model.addAttribute("name", menuDictionary.findDictionary(formSearch.getId()));
        model.addAttribute("form", formSearch);
        List<String> answer = new ArrayList<>();

        if (translateDao.search(formSearch) != null) {
            answer.add(SUCCESSFULLY_FOUND);
            model.addAttribute("result", translateDao.search(formSearch));
        } else answer.add(NOT_PAIR);
        model.addAttribute("answer", answer);
        return "menu/command/search";
    }
}
