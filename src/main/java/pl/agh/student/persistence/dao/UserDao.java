package pl.agh.student.persistence.dao;

import org.springframework.stereotype.Repository;
import pl.agh.student.persistence.model.User;

@Repository("userDao")
public class UserDao extends AbstractDao {

    public void saveUser(User user) {
        persist(user);
    }

}
