# 🚗 Système de Réservation de Véhicules - Centre Hospitalier

Application Java pour la gestion des réservations de véhicules dans un centre hospitalier.

## 🎯 Fonctionnalités

- ✅ **Connexion sécurisée** avec matricule et mot de passe
- ✅ **Création de réservations** avec sélection de véhicules
- ✅ **Consultation** de vos réservations
- ✅ **Modification** de réservations existantes
- ✅ **Vérification de disponibilité** des véhicules
- ✅ **Gestion complète** avec base de données PostgreSQL

## 🚀 Démarrage Rapide

### Prérequis
- Java 11 ou supérieur
- PostgreSQL en cours d'exécution
- Base de données `slam_reservation_vehicule` configurée

### Installation et Lancement

1. **Compiler l'application** :
```bash
bash compile.sh
```

2. **Lancer l'application** :
```bash
bash run.sh
```

Ou directement :
```bash
java -jar reservationvehicule.jar
```

## ⚙️ Configuration

### Base de Données

Modifiez les paramètres dans `src/Passerelle.java` :

```java
private String url = "jdbc:postgresql://192.168.1.46:5432/slam_reservation_vehicule";
private String user = "rayan";
private String passwd = "rayan789";
```

Puis recompilez :
```bash
bash compile.sh
```

## 📖 Utilisation

### Connexion
1. Lancez l'application
2. Entrez votre matricule (numérique)
3. Entrez votre mot de passe
4. Tapez `quit` pour annuler

### Menu Principal
```
1. Faire une réservation
2. Vérifier la disponibilité d'un véhicule
3. Modifier une réservation
4. Voir mes réservations
0. Quitter
```

### Format des Dates
Toutes les dates doivent être au format : **AAAA-MM-JJ**
- Exemple : `2025-12-15`

## ❌ Résolution de Problèmes

### Erreur de Connexion à la Base

Si vous voyez :
```
❌ ERREUR - Impossible de se connecter à la base de données
```

**Solutions** :
1. Vérifiez que PostgreSQL est démarré
2. Vérifiez l'adresse IP et le port dans `Passerelle.java`
3. Vérifiez que la base existe
4. Vérifiez les credentials (user/password)
5. Vérifiez `pg_hba.conf` pour les autorisations

Test de connexion :
```bash
psql -h 192.168.1.46 -p 5432 -U rayan -d slam_reservation_vehicule
```

## 📝 Développement

### Recompilation après modifications

```bash
bash compile.sh
```

---

**Version** : 1.0  
**Java** : 11+  
**PostgreSQL** : 12+
