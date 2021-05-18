package cz.fel.cvut.hamrasan.gardener.seeder;

import cz.fel.cvut.hamrasan.gardener.dao.*;
import cz.fel.cvut.hamrasan.gardener.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class DbSeeder implements
        ApplicationListener<ContextRefreshedEvent> {

    private PlantDao plantDao;
    private UserDao userDao;
    private GardenDao gardenDao;
    private PlantCategoryDao plantCategoryDao;
    private RainDao rainDao;
    private SubcategoryDao subcategoryDao;
    private UserPlantDao userPlantDao;

    @Autowired
    public DbSeeder(PlantDao plantDao, UserDao userDao, PlantCategoryDao plantCategorydao, GardenDao gardenDao, RainDao rainDao, SubcategoryDao subcategoryDao, UserPlantDao userPlantDao) {

        this.plantDao = plantDao;
        this.userDao = userDao;
        this.plantCategoryDao = plantCategorydao;
        this.subcategoryDao= subcategoryDao;
        this.gardenDao = gardenDao;
        this.rainDao = rainDao;
        this.userPlantDao = userPlantDao;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //TODO - vykona sa hned po spusteni
        System.out.println("Vypis po stupusteni aplikacie.");
        createUsers();
        createCategories();
        createSubcategories();
        createPlants();
        createGarden();
        createUserPlant();
        createRain();
    }

    @Transactional
    void createPlants(){

//        Subcategory subcategory2 = new Subcategory("None", plantCategoryDao.find((long) 2), plants);
//        subcategoryDao.persist(subcategory2);

        for (Subcategory sub : subcategoryDao.findAll()) {
            if(sub.getName().equals("Rajčiny") && sub.getCategory().getName().equals("zelenina")) {
                Plant plant = new Plant("Rajčina veľká", "rajciny.jpg", 12, 35,  "Leto", sub);
                plantDao.persist(plant);
                Plant plant2 = new Plant("Rajčina cherry", "cherry.jpg", 10, 35,  "Leto", sub);
                plantDao.persist(plant2);
            }

            if(sub.getName().equals("Jahody") && sub.getCategory().getName().equals("ovocie")) {
                Plant plant2 = new Plant("Jahoda celoročná", "jahody.jpg", 12, 40,  "Celoročne", sub );
                plantDao.persist(plant2);
            }

            if(sub.getName().equals("Kôstkoviny") && sub.getCategory().getName().equals("ovocie")) {
                Plant plant2 = new Plant("Marhuľa", "marhule.jpg", -12, 40,  "Júl-August", sub );
                plantDao.persist(plant2);
                Plant plant12 = new Plant("Broskyňa", "broskyna.jpg", -12, 40,  "Júl-August", sub );
                plantDao.persist(plant12);
            }

            if(sub.getName().equals("None") && sub.getCategory().getName().equals("ovocie")){
                Plant plant5 = new Plant("Banánovník", "banany.jpg", 20, 30,  "Leto", sub);
                plantDao.persist(plant5);
                Plant plant6 = new Plant("Granátovník", "granatovejablko.jpg", 0, 35,  "Jeseň", sub);
                plantDao.persist(plant6);
            }

            if(sub.getName().equals("Jablone") && sub.getCategory().getName().equals("ovocie")){
                Plant plant5 = new Plant("Golden Delicious", "goldenJablko.jpg",-30 , 30,  "Október", sub);
                plantDao.persist(plant5);
                Plant plant6 = new Plant("Jonagold", "jonagold.jpg",-30 , 30,  "Október", sub);
                plantDao.persist(plant6);
            }

            if(sub.getName().equals("Citrusy") && sub.getCategory().getName().equals("ovocie")){
                Plant plant5 = new Plant("Pomarančovník čínsky", "pomaranc.jpg", -4, 35,  "Júl", sub);
                plantDao.persist(plant5);
                Plant plant6 = new Plant("Citrónovník", "citron.jpg", -4, 35,  "Júl", sub);
                plantDao.persist(plant6);
            }

            if(sub.getName().equals("Bobuľoviny") && sub.getCategory().getName().equals("ovocie")) {
                Plant plant12 = new Plant("Čučoriedka kanadská", "blueberries.jpg", -30, 40,  "Celoročne", sub );
                plantDao.persist(plant12);
                Plant plant13 = new Plant("Ostružina malinová", "maliny2.jpg", -20, 30,  "Celoročne", sub );
                plantDao.persist(plant13);
            }

            if(sub.getName().equals("Koreňová zelenina") && sub.getCategory().getName().equals("zelenina")) {
                Plant plant3 = new Plant("Reďkev siata pravá", "redkvicka.jpg", 2, 35,  "Apríl", sub);
                plantDao.persist(plant3);
                Plant plant4 = new Plant("Mrkva obyčajná", "mrkva.jpg", 3, 35,  "Leto", sub);
                plantDao.persist(plant4);
            }

            if(sub.getName().equals("Cibuľová zelenina") && sub.getCategory().getName().equals("zelenina")) {
                Plant plant3 = new Plant("Cibuľa žltá", "cibulaZlta.jpg", 15, 35,  "Júl-August", sub);
                plantDao.persist(plant3);
                Plant plant4 = new Plant("Cibuľa červená", "cibulacervena.jpg", 15, 35,  "Júl-August", sub);
                plantDao.persist(plant4);
                Plant plant5 = new Plant("Cesnak kuchynský ", "cesnak.jpg", 0, 15,  "Jún-Júl", sub);
                plantDao.persist(plant5);
            }

            if(sub.getName().equals("None") && sub.getCategory().getName().equals("zelenina")) {
                Plant plant7 = new Plant("Uhorka šalátová", "uhorkaSalat.jpg", 15, 30,  "Jún-Júl", sub);
                plantDao.persist(plant7);
                Plant plant8 = new Plant("Paprika červená", "paprikaCervenaSiroka.jpg", 21, 35,  "Júl/August", sub);
                plantDao.persist(plant8);
                Plant plant9 = new Plant("Ľuľok zemiakový", "zemiak.jpg", 7, 25,  "Jún-September", sub);
                plantDao.persist(plant9);
                Plant plant19 = new Plant("Chilly paprička", "chilly.jpg", 20, 35,  "August-November", sub);
                plantDao.persist(plant19);
                Plant plant2 = new Plant("Artyčok", "articoky.jpg", 10, 35,  "Júl-August", sub);
                plantDao.persist(plant2);
                Plant plant3 = new Plant("Avokádo", "avokado.jpg", -4, 40,  "Celoročne", sub);
                plantDao.persist(plant3);
            }

            if(sub.getName().equals("None") && sub.getCategory().getName().equals("bylinky")) {
                Plant plant4 = new Plant("Bazalka pravá", "bazalka.jpg", 13, 35,  "Leto", sub);
                plantDao.persist(plant4);
            }

            if(sub.getName().equals("None") && sub.getCategory().getName().equals("kvety")) {
                Plant plant4 = new Plant("Krokus", "crocus.jpg", -5, 15,  "Jar/Jeseň", sub);
                plantDao.persist(plant4);
                Plant plant5 = new Plant("Tulipán", "tulipany.jpg", -5, 22,  "Apríl-Máj", sub);
                plantDao.persist(plant5);
                Plant plant15 = new Plant("Levanduľa úzkolistá", "levandula.jpg", -20, 32,  "Jún-Júl", sub);
                plantDao.persist(plant15);
                Plant plant16 = new Plant("Ľalia ázijská", "lalia.jpg", 10, 18,  "Máj-September", sub);
                plantDao.persist(plant16);
                Plant plant17 = new Plant("Hortenzia kalinolistá", "hortenzia.jpg", -5, 30,  "Leto", sub);
                plantDao.persist(plant17);
            }

        }
    }

    @Transactional
    void createUsers(){
        User user = new User("Jozef", "Pročko", BCrypt.hashpw("hesloo",BCrypt.gensalt()), "jozef@gmail.com", Gender.MAN);
        userDao.persist(user);
        User user1 = new User("Polina", "Nazarenko", BCrypt.hashpw("hesloo",BCrypt.gensalt()), "polina@gmail.com", Gender.WOMAN);
        userDao.persist(user1);
    }

    @Transactional
    void createGarden(){
        List<User> users= userDao.findAll();

        List<UserPlant> userPlants = new ArrayList<UserPlant>();

        if (users.size()>0){
            Garden garden = new Garden("Záhrada za domom","Praha",users.get(0),userPlants);
            gardenDao.persist(garden);
        }
    }


    @Transactional
    void createUserPlant(){
        List<Plant> plants = plantDao.findAll();

        UserPlant userPlant = new UserPlant(LocalDate.now(),plants.get(0).getMinTemperature(), plants.get(0).getMaxTemperature(), plants.get(0).getSeason()
                ,plants.get(0), gardenDao.findAll().get(0));
        userPlantDao.persist(userPlant);

    }

    @Transactional
    void createCategories(){
        List<Subcategory> subcategories = new ArrayList<Subcategory>();
        PlantCategory category = new PlantCategory("zelenina", subcategories );
        plantCategoryDao.persist(category);

        PlantCategory category2 = new PlantCategory("ovocie", new ArrayList<>() );
        plantCategoryDao.persist(category2);

        PlantCategory category3 = new PlantCategory("bylinky", new ArrayList<>() );
        plantCategoryDao.persist(category3);

        PlantCategory category4 = new PlantCategory("kvety", new ArrayList<>() );
        plantCategoryDao.persist(category4);
    }

    @Transactional
    void createSubcategories(){

        for (PlantCategory category: plantCategoryDao.findAll()) {
            Subcategory subcategory = new Subcategory("None", category, new ArrayList<>());
            subcategoryDao.persist(subcategory);

            if(category.getName().equals("ovocie")){
                Subcategory subcategory1 = new Subcategory("Jahody", category, new ArrayList<>());
                Subcategory subcategory2 = new Subcategory("Citrusy", category, new ArrayList<>());
                Subcategory subcategory3 = new Subcategory("Bobuľoviny", category, new ArrayList<>());
                Subcategory subcategory4 = new Subcategory("Jablone", category, new ArrayList<>());
                Subcategory subcategory5 = new Subcategory("Kôstkoviny", category, new ArrayList<>());
                subcategoryDao.persist(subcategory1);
                subcategoryDao.persist(subcategory2);
                subcategoryDao.persist(subcategory3);
                subcategoryDao.persist(subcategory4);
                subcategoryDao.persist(subcategory5);
            }
            else if(category.getName().equals("zelenina")){
                Subcategory subcategory1 = new Subcategory("Koreňová zelenina", category, new ArrayList<>());
                Subcategory subcategory2 = new Subcategory("Rajčiny", category, new ArrayList<>());
                Subcategory subcategory3 = new Subcategory("Cibuľová zelenina", category, new ArrayList<>());
                subcategoryDao.persist(subcategory1);
                subcategoryDao.persist(subcategory2);
                subcategoryDao.persist(subcategory3);
            }
        }
    }

    @Transactional
    void createRain(){
        rainDao.persist(new Rain(LocalDateTime.now(), false, gardenDao.find((long) 1)));
    }

}
