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
import ru.yarkin.service.DictionaryService;

@Controller
@RequestMapping("/dictionary")
public class MenuDictionaryController {
    private final DictionaryService menuDictionaryService;

    @Autowired
    public MenuDictionaryController(DictionaryService menuDictionaryService) {
        this.menuDictionaryService = menuDictionaryService;
    }

    @GetMapping("")
    public String startMenu(@RequestParam("sourceLanguage") Long sourceLanguageId,
                            @RequestParam("targetLanguage") Long targetLanguageId, Model model) {
        model.addAttribute("sourceLanguage", sourceLanguageId);
        model.addAttribute("targetLanguage", targetLanguageId);
        return "menu/dictionary";
    }

    @GetMapping("/add")
    public String add(@RequestParam("sourceLanguage") Long sourceLanguageId,
                      @RequestParam("targetLanguage") Long targetLanguageId, Model model) {
        model.addAttribute("sourceLanguage", sourceLanguageId);
        model.addAttribute("targetLanguage", targetLanguageId);
        model.addAttribute("form", new FormAdd());

        return "menu/command/add";
    }

    @PostMapping("/add")
    public String addPair(@ModelAttribute FormAdd formAdd, Model model) {

        model.addAttribute("sourceLanguage", formAdd.getSourceLanguage());
        model.addAttribute("targetLanguage", formAdd.getTargetLanguage());
        model.addAttribute("result", menuDictionaryService.addPair(formAdd.getSourceLanguage(), formAdd.getTargetLanguage(), formAdd.getKey(), formAdd.getValue()));
        model.addAttribute("form", formAdd);

        return "menu/command/add";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("sourceLanguage") Long sourceLanguageId,
                         @RequestParam("targetLanguage") Long targetLanguageId, Model model) {
        model.addAttribute("sourceLanguage", sourceLanguageId);
        model.addAttribute("targetLanguage", targetLanguageId);
        model.addAttribute("form", new FormDelete());
        return "menu/command/delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute FormDelete formDelete, Model model) {
        model.addAttribute("sourceLanguage", formDelete.getSourceLanguage());
        model.addAttribute("targetLanguage", formDelete.getTargetLanguage());
        model.addAttribute("answer", menuDictionaryService.deletePairByKey(formDelete.getSourceLanguage(), formDelete.getTargetLanguage(), formDelete.getKey()));
        model.addAttribute("form", formDelete);

        return "menu/command/delete";
    }

    @GetMapping("/view")
    public String view(@RequestParam("sourceLanguage") Long sourceLanguageId,
                       @RequestParam("targetLanguage") Long targetLanguageId, Model model) {
        model.addAttribute("sourceLanguage", sourceLanguageId);
        model.addAttribute("targetLanguage", targetLanguageId);
        model.addAttribute("menus", menuDictionaryService.findAllPairsDictionary(sourceLanguageId, targetLanguageId));
        return "menu/command/view";
    }

    @GetMapping("/search")
    public String search(@RequestParam("sourceLanguage") Long sourceLanguageId,
                         @RequestParam("targetLanguage") Long targetLanguageId, Model model) {
        model.addAttribute("sourceLanguage", sourceLanguageId);
        model.addAttribute("targetLanguage", targetLanguageId);
        model.addAttribute("form", new FormSearch());
        return "menu/command/search";
    }

    @GetMapping("/search/result")
    public String search(@ModelAttribute FormSearch formSearch, Model model) {
        model.addAttribute("sourceLanguage", formSearch.getSourceLanguage());
        model.addAttribute("targetLanguage", formSearch.getTargetLanguage());
        model.addAttribute("form", formSearch);
        model.addAttribute("result", menuDictionaryService.findPairDictionaryByKey(formSearch.getSourceLanguage(), formSearch.getTargetLanguage(), formSearch.getKey()));
        return "menu/command/search";
    }
}
