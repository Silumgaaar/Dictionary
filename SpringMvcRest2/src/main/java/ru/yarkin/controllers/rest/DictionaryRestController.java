package ru.yarkin.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.yarkin.dictionary.Dictionary;
import ru.yarkin.dictionary.Pair;
import ru.yarkin.models.form.FormAdd;
import ru.yarkin.models.form.FormDelete;
import ru.yarkin.models.form.FormSearch;
import ru.yarkin.service.DictionaryService;

import java.util.List;


@RestController
@RequestMapping("/library")
public class DictionaryRestController {

    private final DictionaryService dictionaryService;

    @Autowired
    public DictionaryRestController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping
    public List<Dictionary> findDictionaries() {
        return dictionaryService.findAllDictionaries();
    }

    @GetMapping(value = "/{idDictionary}")
    public Dictionary findAllWordsByLanguageId(@PathVariable("idDictionary") String idDictionary) {
        return dictionaryService.findDictionaryById(idDictionary);
    }

    @GetMapping(value = "/{idDictionary}/word")
    public List<Pair> findAllWordsById(@PathVariable("idDictionary") String idDictionary) {
        return dictionaryService.findAllPairsDictionary(dictionaryService.findDictionaryById(idDictionary));
    }

    @PutMapping(value = "/{idDictionary}/word")
    public List<Pair> findWord(@PathVariable("idDictionary") String idDictionary, @RequestBody FormSearch formSearch) {
        return dictionaryService.findPairDictionaryByKey(dictionaryService.findDictionaryById(idDictionary), formSearch.getKey());
    }

    @PostMapping(value = "/{idDictionary}/word")
    public List<String> createPair(@RequestBody FormAdd formAdd, @PathVariable("idDictionary") String idDictionary) {
        return dictionaryService.addPair(dictionaryService.findDictionaryById(idDictionary), formAdd.getKey(), formAdd.getValue());
    }

    @DeleteMapping(value = "/{idDictionary}/word")
    public List<String> deletePair(@RequestBody FormDelete formDelete, @PathVariable("idDictionary") String idDictionary) {
        return dictionaryService.deletePairByKey(dictionaryService.findDictionaryById(idDictionary), formDelete.getKey());
    }


}
