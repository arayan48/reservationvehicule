# Guide d'Utilisation - Système de Réservation de Véhicules

## 🚀 Lancement de l'Application

```bash
java -jar reservationvehicule.jar
```

## 📋 Fonctionnalités Complètes

### 1. **Connexion**
- Entrez votre matricule (numérique)
- Entrez votre mot de passe
- Le système vous identifie et affiche votre nom

### 2. **Menu Principal**
```
1. Faire une réservation
2. Vérifier la disponibilité d'un véhicule
3. Modifier une réservation
4. Voir mes réservations
0. Quitter
```

### 3. **Faire une Réservation**
- Choisissez la date de réservation (appuyez sur Entrée pour aujourd'hui)
- Indiquez la date de début d'utilisation
- Spécifiez la durée en jours
- Sélectionnez le type de véhicule parmi la liste affichée
- Choisissez un véhicule disponible
- La réservation est automatiquement enregistrée en base de données

### 4. **Vérifier la Disponibilité**
- Entrez la marque du véhicule
- Entrez le modèle
- Spécifiez le numéro du type
- Entrez l'immatriculation
- Le système vérifie si le véhicule est disponible

### 5. **Modifier une Réservation**
- Entrez le numéro de réservation
- Confirmez la date de réservation
- Modifiez les champs souhaités :
  - Date de début
  - Matricule
  - Type de véhicule
  - Immatriculation
  - Durée
  - Date de retour effectif
  - État de la réservation

### 6. **Voir Mes Réservations**
- Affiche toutes vos réservations avec :
  - Numéro de réservation
  - Dates
  - Durée
  - Véhicule réservé
  - État

## 🗄️ Base de Données

### Configuration PostgreSQL
- **Hôte** : 192.168.1.245:5432
- **Base** : slam2026_AP_rayanayyoubaymane
- **Tables utilisées** :
  - `personne` : utilisateurs du système
  - `vehicule` : véhicules disponibles
  - `type` : types de véhicules
  - `demande` : réservations
  - `service` : services de l'hôpital

## 📦 Compilation

Pour recompiler l'application :

```bash
bash compile.sh
```

Le script :
- Compile tous les fichiers Java avec Java 11
- Intègre le driver PostgreSQL dans le JAR
- Crée un fat JAR exécutable unique

## ✨ Architecture

### Classes Principales

1. **App.java** : Point d'entrée de l'application
2. **Menu.java** : Interface utilisateur et navigation
3. **Passerelle.java** : Accès à la base de données (DAO)
4. **Demande.java** : Modèle de réservation
5. **Personne.java** : Modèle utilisateur
6. **Vehicule.java** : Modèle véhicule
7. **Type.java** : Modèle type de véhicule
8. **Service.java** : Modèle service hospitalier

## 🔧 Dépendances

- **Java 11+** (compilé avec --release 11)
- **PostgreSQL JDBC Driver 42.7.4** (intégré dans le JAR)

## 📝 Notes Importantes

- Toutes les dates doivent être au format **AAAA-MM-JJ** (ex: 2025-12-15)
- Les réservations sont automatiquement créées avec l'état "En attente"
- Le matricule de l'utilisateur connecté est mémorisé pour la session
- Le JAR contient tous les drivers nécessaires (pas besoin de classpath externe)

## 🎯 États des Réservations

- **En attente** : Réservation créée, en attente de validation
- **Validée** : Réservation approuvée
- **En cours** : Véhicule actuellement utilisé
- **Terminée** : Véhicule retourné
- **Annulée** : Réservation annulée

## 🆘 Support

Pour toute question ou problème :
1. Vérifiez que PostgreSQL est accessible
2. Vérifiez les identifiants de connexion dans Passerelle.java
3. Assurez-vous d'avoir Java 11 ou supérieur installé
