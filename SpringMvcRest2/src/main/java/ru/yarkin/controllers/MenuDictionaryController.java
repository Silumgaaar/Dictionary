package ru.yarkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.yarkin.dictionary.Dictionary;
import ru.yarkin.models.form.FormAdd;
import ru.yarkin.models.form.FormDelete;
import ru.yarkin.models.form.FormSearch;
import ru.yarkin.service.DictionaryService;

@Controller
@RequestMapping("/dictionary")
public class MenuDictionaryController {
    private final DictionaryService dictionaryService;
    private final Dictionary dictionary;

    @Autowired
    public MenuDictionaryController(DictionaryService dictionaryService, Dictionary dictionary) {
        this.dictionaryService = dictionaryService;
        this.dictionary = dictionary;
    }

    @GetMapping("")
    public String startMenu(@RequestParam("sourceLanguage") Long sourceLanguageId, @RequestParam("targetLanguage") Long targetLanguageId, Model model) {
        dictionaryService.findDictionaryById(sourceLanguageId + "-" + targetLanguageId, dictionary);
        model.addAttribute("dictionary", dictionary);
        return "menu/dictionary";
    }

    @GetMapping("/view")
    public String view(@RequestParam("sourceLanguage") Long sourceLanguageId, @RequestParam("targetLanguage") Long targetLanguageId, Model model) {
        dictionaryService.findDictionaryById(sourceLanguageId + "-" + targetLanguageId, dictionary);
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("menus", dictionaryService.findAllPairsDictionary(dictionary));
        return "menu/command/view";
    }

    @GetMapping("/search")
    public String search(@RequestParam("sourceLanguage") Long sourceLanguageId, @RequestParam("targetLanguage") Long targetLanguageId, Model model) {
        dictionaryService.findDictionaryById(sourceLanguageId + "-" + targetLanguageId, dictionary);
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("form", new FormSearch());
        return "menu/command/search";
    }

    @GetMapping("/search/result")
    public String search(@ModelAttribute FormSearch formSearch, Model model) {
        dictionaryService.findDictionaryById(formSearch.getSourceLanguage() + "-" + formSearch.getTargetLanguage(), dictionary);
        model.addAttribute("form", formSearch);
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("result", dictionaryService.findPairDictionaryByKey(dictionary, formSearch.getKey()));
        return "menu/command/search";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("sourceLanguage") Long sourceLanguageId, @RequestParam("targetLanguage") Long targetLanguageId, Model model) {
        dictionaryService.findDictionaryById(sourceLanguageId + "-" + targetLanguageId, dictionary);
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("form", new FormDelete());
        return "menu/command/delete";
    }

    @GetMapping("/add")
    public String add(@RequestParam("sourceLanguage") Long sourceLanguageId, @RequestParam("targetLanguage") Long targetLanguageId, Model model) {
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("sourceLanguage", sourceLanguageId);
        model.addAttribute("targetLanguage", targetLanguageId);
        model.addAttribute("form", new FormAdd());
        return "menu/command/add";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute FormDelete formDelete, Model model) {
        dictionaryService.findDictionaryById(formDelete.getSourceLanguage() + "-" + formDelete.getTargetLanguage(), dictionary);
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("answer", dictionaryService.deletePairByKey(dictionary, formDelete.getKey()));
        model.addAttribute("form", formDelete);
        return "menu/command/delete";
    }

    @PostMapping("/add")
    public String addPair(@ModelAttribute FormAdd formAdd, Model model) {
        dictionaryService.findDictionaryById(formAdd.getSourceLanguage() + "-" + formAdd.getTargetLanguage(), dictionary);
        model.addAttribute("result", dictionaryService.addPair(dictionary, formAdd.getKey(), formAdd.getValue()));
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("form", formAdd);
        return "menu/command/add";
    }
}
