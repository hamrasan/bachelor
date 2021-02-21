package cz.fel.cvut.hamrasan.gardener.seeder;

import cz.fel.cvut.hamrasan.gardener.dao.PlantCategoryDao;
import cz.fel.cvut.hamrasan.gardener.dao.PlantDao;
import cz.fel.cvut.hamrasan.gardener.dao.UserDao;
import cz.fel.cvut.hamrasan.gardener.model.Plant;
import cz.fel.cvut.hamrasan.gardener.model.PlantCategory;
import cz.fel.cvut.hamrasan.gardener.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DbSeeder implements
        ApplicationListener<ContextRefreshedEvent> {

    private PlantDao plantDao;
    private UserDao userDao;
    private PlantCategoryDao plantCategoryDao;

    @Autowired
    public DbSeeder(PlantDao plantDao, UserDao userDao, PlantCategoryDao plantCategorydao) {

        this.plantDao = plantDao;
        this.userDao = userDao;
        this.plantCategoryDao = plantCategorydao;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //TODO - vykona sa hned po spusteni
        System.out.println("Vypis po stupusteni aplikacie.");
        createUsers();
        createPlants();
    }

    @Transactional
    void createPlants(){
        List<Plant> plants = new ArrayList<Plant>();
        PlantCategory category = new PlantCategory("zelenina", plants );
        plantCategoryDao.persist(category);
        Plant plant = new Plant("Rajčina veľká", "../../assets/paradajka-lycopersicum-rajciak-semena.jpg", 12, 35, LocalDate.now(), "March", category, userDao.findAll());
        plantDao.persist(plant);
    }

    @Transactional
    void createUsers(){
        User user = new User("Jozef", "Pročko", BCrypt.hashpw("hesloo",BCrypt.gensalt()), "jozef@gmail.com");
        userDao.persist(user);
    }
}
