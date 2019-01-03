package org.launchcode.foodday.models.data;


import org.launchcode.foodday.models.Foodday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface FooddayDao extends CrudRepository<Foodday, Integer> {
}
