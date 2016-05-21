package pl.agh.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.agh.student.persistence.dao.UserDao;
import pl.agh.student.persistence.model.Tweet;
import pl.agh.student.persistence.model.User;

@Service("userService")
@Transactional
public class UserService {

    @Autowired
    private UserDao dao;

    public void saveUser(User user) {
        dao.saveUser(user);
    }
    
    public User getById(long i) {
        return dao.findById(i);
    }

}
