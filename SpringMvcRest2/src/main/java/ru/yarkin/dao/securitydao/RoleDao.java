package ru.yarkin.dao.securitydao;

import org.springframework.stereotype.Repository;
import ru.yarkin.dao.AbstractHibernateDao;
import ru.yarkin.models.database.Role;

@Repository
public class RoleDao extends AbstractHibernateDao<Role> {
    public RoleDao() {
        setClazz(Role.class);
    }
}
