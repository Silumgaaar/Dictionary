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

    public DictionaryRestController(MenuDictionaryService menuDictionaryService, MenuDictionariesService menuDictionariesService){
        this.menuDictionaryService = menuDictionaryService;
        this.menuDictionariesService = menuDictionariesService;
    }

    @GetMapping
    public Map<Long, String> findDictionaries(){
        return menuDictionariesService.getStartMenu();
    }

    @GetMapping(value = "/{id}")
    public String findDictionary(@PathVariable("id") Long id) {
        return menuDictionaryService.findDictionaryById(id);
    }

    @GetMapping(value = "/{id}/word")
    public Map<String, String> findAllPair(@PathVariable("id") Long id){
        return menuDictionaryService.findAllPairDictionary(id);
    }

    @PutMapping(value = "/{id}/word")
    public List<String> findWord(@PathVariable("id") Long id, @RequestBody FormSearch formSearch){
        return menuDictionaryService.searchPair(id, formSearch.getKey());
    }

    @PostMapping(value = "/{id}/word")
    public List<String> createPair(@RequestBody FormAdd formAdd, @PathVariable("id") Long id){
        return menuDictionaryService.addPair(id, formAdd.getKey(), formAdd.getValue());
    }

    @DeleteMapping(value = "/{id}/word")
    public List<String> deletePair(@RequestBody FormDelete formDelete, @PathVariable("id") Long id){
        return menuDictionaryService.deletePair(id,formDelete.getKey());
    }



}
