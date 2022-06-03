package ru.yarkin.controllers.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.yarkin.models.form.FormAdd;
import ru.yarkin.models.form.FormDelete;
import ru.yarkin.models.form.FormSearch;
import ru.yarkin.service.MenuDictionariesService;
import ru.yarkin.service.MenuDictionaryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/library")
public class DictionaryRestController {

    private final MenuDictionaryService menuDictionaryService;
    private final MenuDictionariesService menuDictionariesService;

    public DictionaryRestController(MenuDictionaryService menuDictionaryService, MenuDictionariesService menuDictionariesService) {
        this.menuDictionaryService = menuDictionaryService;
        this.menuDictionariesService = menuDictionariesService;
    }

    @GetMapping
    public Map<String, String> findDictionaries() {
        return menuDictionariesService.findAllDictionaries();
    }

    @GetMapping(value = "/{id}")
    public String findDictionary(@PathVariable("id") String id) {
        return menuDictionaryService.findDictionaryById(id);
    }

    @GetMapping(value = "/{id}/word")
    public List<String> findAllPair(@PathVariable("id") String id) {
        return menuDictionaryService.findAllPairDictionary(id);
    }

    @PutMapping(value = "/{id}/word")
    public List<String> findWord(@PathVariable("id") String id, @RequestBody FormSearch formSearch) {
        return menuDictionaryService.findPairDictionaryByKey(id, formSearch.getKey());
    }

    @PostMapping(value = "/{id}/word")
    public List<String> createPair(@RequestBody FormAdd formAdd, @PathVariable("id") String id) {
        return menuDictionaryService.addPair(id, formAdd.getKey(), formAdd.getValue());
    }

    @DeleteMapping(value = "/{id}/word")
    public List<String> deletePair(@RequestBody FormDelete formDelete, @PathVariable("id") String id) {
        return menuDictionaryService.deletePairByKey(id, formDelete.getKey());
    }

}
