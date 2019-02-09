package org.launchcode.foodday.models.data;


import org.launchcode.foodday.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {

    ArrayList<User> findByName(String name);
}
