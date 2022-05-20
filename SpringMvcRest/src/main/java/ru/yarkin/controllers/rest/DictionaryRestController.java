package ru.yarkin.controllers.rest;

import org.springframework.web.bind.annotation.*;

import ru.yarkin.models.demobase.Libraries;
import ru.yarkin.models.demobase.Words;
import ru.yarkin.models.form.FormAdd;
import ru.yarkin.models.form.FormDelete;
import ru.yarkin.models.form.FormSearch;
import ru.yarkin.service.MenuDictionariesService;
import ru.yarkin.service.MenuDictionaryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/library")
public class DictionaryRestController {

    private static final String DICTIONARY_NOT_FOUND = "Dictionary not found";
    private static final String PAIR_NOT_FOUND = "Pair not found";

    private final MenuDictionaryService menuDictionaryService;
    private final MenuDictionariesService menuDictionariesService;

    public DictionaryRestController(MenuDictionaryService menuDictionaryService, MenuDictionariesService menuDictionariesService){
        this.menuDictionaryService = menuDictionaryService;
        this.menuDictionariesService = menuDictionariesService;
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET)
    public List<String> findDictionaries(){
        List<String> dictionaries = new ArrayList<>();
        for(Libraries dictionary : menuDictionariesService.getStartMenu()){
            dictionaries.add(dictionary.getName());
        }
        return dictionaries;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    public String findDictionary(@PathVariable("id") Long id) {
        try {
            return menuDictionaryService.findDictionaryById(id).getName();
        }catch (NullPointerException e) {
            return DICTIONARY_NOT_FOUND;
        }
    }


    @RequestMapping(value = "/{id}/word",
            method = RequestMethod.GET)
    public Map<String, String> findAllPair(@PathVariable("id") Long id){
        Map<Words,Words> map = menuDictionaryService.findAllPairDictionary(id);
        Map<String,String> result = new HashMap<>();

        for(Map.Entry<Words, Words> entry: map.entrySet()) {
            Words key = entry.getKey();
            Words value = entry.getValue();
            result.put(key.getValue(), value.getValue());
        }

        return result;
    }

    @RequestMapping(value = "/{id}/word/{value}",
            method = RequestMethod.GET)
    public List<String> findWord(@PathVariable("id") Long id, @PathVariable("value") String value ){
        FormSearch formSearch = new FormSearch();
        formSearch.setId(id);
        formSearch.setKey(value);

        List<String> result = new ArrayList<>();
        try {
            result.add(value + " - " + menuDictionaryService.searchPair(formSearch).getTargetWordsId().getValue());
            return result;
        } catch (Exception e){
            result.add(PAIR_NOT_FOUND);
            return result;
        }
    }
    @PostMapping(value = "/{id}/word")
    public List<String> createPair(@RequestBody FormAdd formAdd, @PathVariable("id") Long id){
        formAdd.setId(id);
        return menuDictionaryService.addPair(formAdd);
    }
    @DeleteMapping(value = "/{id}/word")
    public List<String> deletePair(@RequestBody FormDelete formDelete, @PathVariable("id") Long id){
        formDelete.setId(id);
        return menuDictionaryService.deletePair(formDelete);
    }



}
