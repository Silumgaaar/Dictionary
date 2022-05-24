package ru.yarkin.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ru.yarkin.models.demobase.Libraries;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LibrariesDao extends SessionUtil{
    private static final String DICTIONARY_NOT_FOUND = "Dictionary not found";

    public LibrariesDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Long> findAllIdDictionaries(){
        List<Long> result = new ArrayList<>();
        var libraries = sessionFactory.getCurrentSession().createQuery("from Libraries",Libraries.class).getResultList();
        for (Libraries lib: libraries) {
            result.add(lib.getId());
        }
        return result;
    }
    public List<String> findAllNameDictionaries() {
        List<String> namesDictionaries = new ArrayList<>();
        var libraries = sessionFactory.getCurrentSession().createQuery("from Libraries",Libraries.class).getResultList();
        for (Libraries lib: libraries) {
            namesDictionaries.add(lib.getName());
        }
        return namesDictionaries;
    }

    public Libraries findDictionaryById(Long id){
            return currentSession().createQuery("from Libraries where id=:id", Libraries.class)
                    .setParameter("id", id)
                    .getSingleResult();
    }


    public String findNameDictionaryById(Long id) {
        try {
            Libraries dictionary = currentSession().createQuery("from Libraries where id=:id", Libraries.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return dictionary.getName();
        }catch (NoResultException e) {
            return DICTIONARY_NOT_FOUND;
        }
    }
    public String findSourceIdLanguage(Long id){
        Libraries libraries = findDictionaryById(id);
        return libraries.getSourceLanguageId().getLanguage();
    }
    public String findTargetIdLanguage(Long id){
        Libraries libraries = findDictionaryById(id);
        return libraries.getTargetLanguageId().getLanguage();
    }
}
