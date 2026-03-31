# Medimenu : Générateur intelligent de menus diététiques
Générer un menu cohérent sur plusieurs jours est un casse-tête combinatoire. Au lieu d'utiliser un algorithme classique, Medimenu s'appuie sur la programmation par contraintes. Le nutritionniste décrit simplement le résultat attendu (apports nutritionnels minimum/maximum, groupes d'aliments autorisés, flexibilité), et le moteur mathématique se charge de calculer la combinaison de repas la plus variée et équilibrée possible.

Travail d'étude et de recherche réalisé sur une année développé en collaboration avec le projet de recherche européen Medigene, cet outil s'adresse aux professionnels de la nutrition pour les aider à concevoir des régimes sur-mesure (par exemple, en contrôlant avec précision la quantité d'acides aminés journalière). Son objectif est de faciliter l'étude de l'impact de l'alimentation sur le métabolisme et les maladies chroniques.

Voici quelques détails sur le fonctionnement :
* Moteur de résolution : Plateforme Choco-solver > https://choco-solver.org/
* Architecture Web : Jakarta EE (avec modèle MVC) à déployé sur un serveur Apache Tomcat.
* Données : Système flexible fonctionnant avec des fichiers CSV, utilisant par défaut la base de données nutritionnelle de référence CIQUAL de l'ANSES.

## Utilisation
Ce projet a été testé avec le logiciel Tomcat 8. Une release avec un simple fichier .war est mis à disposition pour un déploiement rapide.
Il faudra dans ce cas copier le contenu du dossier */data* du dépot dans */medimenu/* à la racine du serveur.

*Llivia LANGEVIN et Areski HIMEUR.*

