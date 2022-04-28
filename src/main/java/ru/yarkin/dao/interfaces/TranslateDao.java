package ru.yarkin.dao.interfaces;

import ru.yarkin.models.demobase.Translate;
import ru.yarkin.models.demobase.Words;
import ru.yarkin.models.form.FormAdd;
import ru.yarkin.models.form.FormDelete;
import ru.yarkin.models.form.FormSearch;

import java.util.List;
import java.util.Map;

public interface TranslateDao {
    List<String> add(FormAdd formAdd);
    Map<Words,Words> findAllPair(Long id);
    Translate search(FormSearch formSearch);
    boolean delete(FormDelete formDel);
}
