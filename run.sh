#!/bin/bash

# Script de lancement de l'application de réservation

echo "========================================="
echo "  Démarrage de l'application..."
echo "========================================="
echo ""

# Vérifier que Java est installé
if ! command -v java &> /dev/null; then
    echo "❌ ERREUR: Java n'est pas installé ou n'est pas dans le PATH"
    echo "Veuillez installer Java 11 ou supérieur"
    exit 1
fi

# Afficher la version de Java
JAVA_VERSION=$(java -version 2>&1 | head -n 1)
echo "✓ $JAVA_VERSION"
echo ""

# Vérifier que le JAR existe
if [ ! -f "reservationvehicule.jar" ]; then
    echo "❌ ERREUR: reservationvehicule.jar n'existe pas"
    echo "Exécutez d'abord: bash compile.sh"
    exit 1
fi

echo "✓ JAR trouvé: reservationvehicule.jar"
echo ""

# Lancer l'application
java -jar reservationvehicule.jar

# Code de sortie
EXIT_CODE=$?
echo ""
echo "========================================="
if [ $EXIT_CODE -eq 0 ]; then
    echo "✓ Application terminée normalement"
else
    echo "❌ Application terminée avec erreur (code: $EXIT_CODE)"
fi
echo "========================================="

exit $EXIT_CODE
