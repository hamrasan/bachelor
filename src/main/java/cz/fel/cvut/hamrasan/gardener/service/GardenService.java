package cz.fel.cvut.hamrasan.gardener.service;

import cz.fel.cvut.hamrasan.gardener.dao.GardenDao;
import cz.fel.cvut.hamrasan.gardener.dao.UserDao;
import cz.fel.cvut.hamrasan.gardener.dto.GardenDto;
import cz.fel.cvut.hamrasan.gardener.exceptions.AlreadyExistsException;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotAllowedException;
import cz.fel.cvut.hamrasan.gardener.model.Garden;
import cz.fel.cvut.hamrasan.gardener.model.User;
import cz.fel.cvut.hamrasan.gardener.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GardenService {

    private GardenDao gardenDao;
    private UserDao userDao;
    private TranslateService translateService;


    @Autowired
    public GardenService(GardenDao gardenDao, UserDao userDao, TranslateService translateService) {

        this.gardenDao = gardenDao;
        this.userDao = userDao;
        this.translateService = translateService;
    }


    /**
     * Method gets all gardens of user
     * @return List<GardenDto>
     */
    @Transactional
    public List<GardenDto> findAllOfUser(){
        List<GardenDto> gardenDtos = new ArrayList<>();
        User user = userDao.find(SecurityUtils.getCurrentUser().getId());

        for (Garden garden : user.getGardens()) {
            gardenDtos.add(translateService.translateGarden(garden));
        }

        return gardenDtos;
    }


    /**
     * Creating garden
     * @param name - name of new garden
     * @param location - location of new garden
     */
    @Transactional
    public void create(String name, String location) throws AlreadyExistsException {
        User user = SecurityUtils.getCurrentUser();
        if(user != null) {
            if(gardenDao.findByName(name, user) != null) throw new AlreadyExistsException();
            String slug = translateToSlug(name);
            Garden garden = new Garden(name, slug, location, user);
            gardenDao.persist(garden);
        }
    }

    private String translateToSlug(String str){
        String slug = str.replaceAll("\\s","");
        slug = slug.replace('á','a');
        slug = slug.replace('é','e');
        slug = slug.replace('í','i');
        slug = slug.replace('ý','y');
        slug = slug.replace('ž','z');
        slug = slug.replace('š','s');
        slug = slug.replace('ť','t');
        slug = slug.replace('ľ','l');
        slug = slug.replace('ú','u');
        slug = slug.replace('ô','o');
        slug = slug.replace('č','c');
        slug = slug.replace('ř','r');
        slug = slug.replace('ě','e');
        slug = slug.replace('ň','n');
        slug = slug.replace('ó','o');
        slug = slug.replace('ä','a');
        slug = slug.replace('ĺ','l');
        slug = slug.replace('ŕ','r');
        slug = slug.replace('ů','u');
        slug = slug.replace('Ú','U');
        slug = slug.replace('Š','S');
        slug = slug.replace('Č','C');
        slug = slug.replace('Ť','T');
        slug = slug.replace('Ř','R');
        slug = slug.replace('Ž','Z');
        slug = slug.replace('Ň','N');
        slug = slug.replace('Ľ','L');
        return slug;
    }
}
