package com.apiodkpointage.apiodkpointage;

import com.apiodkpointage.apiodkpointage.administrateurs.Administrateur;
import com.apiodkpointage.apiodkpointage.administrateurs.Libelle;
import com.apiodkpointage.apiodkpointage.administrateurs.Profile;
import com.apiodkpointage.apiodkpointage.administrateurs.repositories.AdministrateurRepository;
import com.apiodkpointage.apiodkpointage.administrateurs.repositories.ProfileRepository;
import com.apiodkpointage.apiodkpointage.promotions.Promotion;
import com.apiodkpointage.apiodkpointage.promotions.repositories.PromotionRepository;
import com.apiodkpointage.apiodkpointage.utilisateurs.groupe.Groupe;
import com.apiodkpointage.apiodkpointage.utilisateurs.groupe.Repository.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
public class ApiodkpointageApplication implements CommandLineRunner {

	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	AdministrateurRepository administrateurRepository;

	@Autowired
	PromotionRepository promotionRepository;
	@Autowired
	GroupeRepository groupeRepository;

	public static void main(String[] args) {

		SpringApplication.run(ApiodkpointageApplication.class, args);
	}

	public void run(String... args) throws Exception {
		//Add SuperAdmin profile
		Profile profile = new Profile(Libelle.SUPERADMIN, "Super Administrateur du système !");
		//Save Profile
		profileRepository.save(profile);
		//Add  profile Admin
		Profile profile2 = new Profile(Libelle.ADMIN, " Administrateur du système ");
		//Save Profile
		profileRepository.save(profile2);


		//Add a superAdmin

		Administrateur superAdmin = new Administrateur("MK", "sm", "Bamako", "masculin", 90302635, "mk@gmail.com", "mk", "qwerty", LocalDate.now(), null, Etat.ACTIVER, false,"http://localhost/images/samassekou.png");

		//Assign profile to admin
		superAdmin.setProfile(profile);
		superAdmin.setGenre("HOMME");
		//Save a SuperAdmin
		administrateurRepository.save(superAdmin);


		//Add default promotion
		Promotion promotion = new Promotion("Promotion 1", 2021, LocalDate.of(2021, 11, 3), LocalDate.of(2022, 2, 26),
				39, 1, LocalTime.of(9, 0), LocalTime.of(17, 0));
		//Get total apprenant
		int totalApprenant = promotion.getNombreFemmes() + promotion.getNombreHommes();
		promotion.setTotalApprenants(totalApprenant);
		//Save promotion
		promotionRepository.save(promotion);

		Groupe groupe = new Groupe("Groupe 1",Etat.ACTIVER, LocalDate.now(),9);
		groupeRepository.save(groupe);
	}
}
