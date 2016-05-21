package pl.agh.student.persistence.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import pl.agh.student.persistence.model.User;

@Component
public class UserDao extends AbstractDao {

    public void saveUser(User user) {
        persist(user);
    }
    
    public User findById(Long id) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        return (User) criteria.uniqueResult();
    }

}
