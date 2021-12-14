# APIOdkPointage
Ce projet consiste à faire une API pour l'application ODKPointage de Orange Digital Kalanso

# Comment utiliser cette API ?  
## Pour l'entité Administrateur :  
1. Authentification admin => http://localhost:8080/api/admin/authentificationAdmin?login=...&password=...  
   Si l'authentification réussie, il retourne les données de l'admin connecté, sinon ça retourne null.  
2. Ajout Admin: Méthode => POST URL => localhost:8080/api/admin/ajout_admin/{idSuperAdmin}  
   //id = l'id de l'admin qui doit être supprimer  
   //idAdmin = l'id de super admin qui fait l'ajout    
  
{  
   "nom": "Diarra",  
   "prenom": "christophe",  
   "adresse": "adresse",  
   "genre": "Masculin",  
   "telephone": 2000063,  
   "email": "Al@gemail.com",  
   "login": "Diarra",  
   "motDePass": "azerty",
   "imageURL": "www.image.com",  
   "etat": "ACTIVER",  
   "Type": "APPRENANT",  
   "promotion":{  
      "id":1  
   }  
}  

## Pour Afficher la liste des admins  
Méthode => GET URL => localhost:8080/api/admin/afficher_List_admin  

## Pour Afficher admin par ID  
Méthode => GET URL => localhost:8080/api/admin/afficherAdminById/{id}  

## Pour Supprimer un admin par son ID  
Méthode => DELETE URL => localhost:8080/api/admin/supprimerAdmin/{id}&{idSuperAdmin}  
//id = l'admin qui doit être supprimer  
//idSuperAdmin = l'id de super admin qui fait la suppression  
EX: localhost:8080/api/admin/supprimerAdmin/1&2  
  
## Pour modifier un admin par son ID  
Méthode => PUT URL => localhost:8080/api/admin/modifierAdmin/{id}&{idSuperAdmin}  
//id = l'admin qui doit être modifier  
//idSuperAdmin = l'id de super admin qui fait la modification

## Pour que l'admin puisse modifier son mot de passe
Méthode => PUT URL => localhost:8080/api/admin/modifierMotDePassAdmin/{id}?nouveauPassword=votreNouveauMotDePasse
  
# pointage
## pointage par utilisateur    
{  
   "id": 1,  
   "Type": "APPRENANT"  
}  
## ajouter pointage
{   
   "utilisateur": {  
      "id": 1,  
      "Type": "APPRENANT"  
   }  
}  
  
# Partie promotion  
  
## ajouter une promotion  
{
   "nom": "Promotion 1",  
   "annee": "2020-2021",  
   "dateDebut": "2021-11-03",  
   "dateFin": "2021-11-30",  
   "totalApprenants": 47,  
   "nombreFemmes": 8,  
   "nombreHommes": 36,  
   "horaireDebutJournee": "09:00:00",  
   "horaireFinJournee": "17:00:00"
}  
  
## Pour ajouter le profile  
Méthode => POST => localhost:8080/api/admin/addProfile/{idSuperAdmin}  
  
{  
   "libelle":"SUPERADMIN",  
   "description": "Je suis le super admin !"  
}  
  
{  
   "libelle":"ADMIN",  
   "description": "Je suis admin !"  
}  
  
## Lister la liste des profiles  
Méthode => GET => localhost:8080/api/admin/listProfile  
  
## Afficher le profile par ID  
Méthode => GET => localhost:8080/api/admin/getProfileById/{idProfile}  
  
## Supprimer un profile  
Méthode => DELETE => localhost:8080/api/admin/deleteProfile/{id}&{idSuperAdmin}  

//id => L'id du profile à supprimer  
//idSuperAdmin => L'id du super admin qui fait la suppression  
 
Ex : localhost:8080/api/admin/deleteProfile/4&1  
  
## Pour ajouter le profile  
Méthode => POST => localhost:8080/api/admin/addProfile/{idSuperAdmin}

{  
   "libelle":"SUPERADMIN",  
   "description": "Je suis le super admin !"  
}  
    
{  
   "libelle":"ADMIN",  
   "description": "Je suis admin !"  
}  
  
## Lister la liste des profiles  
Méthode => GET => localhost:8080/api/admin/listProfile  

## Afficher le profile par ID  
Méthode => GET => localhost:8080/api/admin/getProfileById/{idProfile}  

## Supprimer un profile  
Méthode => DELETE => localhost:8080/api/admin/deleteProfile/{id}&{idSuperAdmin}  

//id => L'id du profile à supprimer  
//idSuperAdmin => L'id du super admin qui fait la suppression  

Ex : localhost:8080/api/admin/deleteProfile/4&1  

# Utilisateur  
3. Pour la partie Utilisateur  

## URL Principal localhost:8080/api/utilisateurs/  
Ajout Utilisateur: Méthode => POST URL => localhost:8080/api/utilisateurs/ajout_utilisateur/{idAdmin}  

//idAdmin = l'id de l'admin qui fait l'ajout

{  
   "nom": "Diarra",  
   "prenom": "christophe",  
   "adresse": "adresse",  
   "genre": "Masculin",  
   "telephone": 2000063,  
   "email": "Al@gemail.com",  
   "login": "Diarra",  
   "motDePass": "azerty",  
   "etat": "ACTIVER",  
   "Type": "APPRENANT",
   "imageURL": "www.image.com", 
   "promotion":{  
      "id":1 //id de la prommotion  
   }  
}  
  
## Pour Afficher la liste des utilisateur  
Méthode => GET URL => localhost:8080/api/utilisateurs/afficher_list_utilisateur  

## Pour Afficher par son ID un utilisateur  
Méthode => GET URL => localhost:8080/api/utilisateurs/afficher_by_id/{id}  
EX: localhost:8080/api/utilisateurs/afficher_by_id/1  

## Pour Supprimer un utilisateur par son ID  
Méthode => DELETE URL => localhost:8080/api/utilisateurs/supprimerUser/{id}&{idAdmin}  
EX: localhost:8080/api/utilisateurs/supprimerUser/1&1  

## Pour modifier un utilisateur par son ID
Méthode => PUT URL => localhost:8080/api/utilisateurs/modifier_utilisateur/{id}&{idAdmin}  
{idAdmin} // l'id de l'admin qui doit faire la modication

## Pour supprimer un utilisateur par son ID
Méthode => PUT URL => localhost:8080/api/utilisateurs/supprimerUser/{id}&{idAdmin}  
{idAdmin} // l'id de l'admin qui doit faire la modication  

## Pour la connexion d'un utilisateur
Méthode => GET URL => localhost:8080/api/utilisateurs/login  


# Promotion

## URL Principal localhost:8080/api/promotion/

## Pour afficher la liste de la promotion
Méthode => GET URL => localhost:8080/api/promotion/afficherpromotion  

## Pour ajouter une promotion  
Méthode => POST URL => localhost:8080/api/promotion/ajouter  

## Pour modifier une promotion  
Méthode => PUT URL => localhost:8080/api/promotion/modifier/{id}  

## Pour afficher une promotion par son ID
Méthode => PUT URL => localhost:8080/api/promotion/afficherparid/{id}  

## Pour supprimer une promotion par son ID
Méthode => DELETE URL => localhost:8080/api/promotion/supprimer/{id}  
{id} // l'id de la promotion à supprimer  

# Pour la partie log
## URL Principal localhost:8080/api/log

## Pour afficher la liste de tous les logs
Méthode => GET URL => localhost:8080/api/log/listeLog  

## Pour afficher la liste des logs par ID
Méthode => GET URL => localhost:8080/api/log/afficherLogById/{id}  
