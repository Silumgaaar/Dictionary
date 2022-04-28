package ru.yarkin.dao.interfaces;

import ru.yarkin.models.demobase.Languages;
import ru.yarkin.models.demobase.Rules;

public interface RulesDao {
    Rules getRule(Languages languages);
}
