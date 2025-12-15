#!/bin/bash

# Script de compilation et création du JAR avec Java 11

echo "=== Compilation de l'application ==="

# Nettoyage du dossier bin
rm -rf bin/*
mkdir -p bin

# Compilation avec Java 11
javac --release 11 -cp "lib/*" -d bin src/*.java

if [ $? -eq 0 ]; then
    echo "✓ Compilation réussie"
    
    # Création du manifest
    echo "=== Création du fat JAR (uber-jar) ==="

    # Manifest (Main-Class only)
    cat > bin/MANIFEST.MF << EOF
Manifest-Version: 1.0
Main-Class: App
EOF

    # Préparer un répertoire temporaire qui contiendra toutes les classes
    TMPDIR="tmpjar"
    rm -rf "$TMPDIR"
    mkdir -p "$TMPDIR"

    # Extraire le contenu du jar PostgreSQL dans tmp
    if [ -f "lib/postgresql-42.7.4.jar" ]; then
        (cd "$TMPDIR" && jar xf "../lib/postgresql-42.7.4.jar")
    else
        echo "⚠️  lib/postgresql-42.7.4.jar introuvable — vérifiez le dossier lib/"
    fi

    # Copier les classes compilées dans tmp
    cp -r bin/* "$TMPDIR/"

    # Créer le fat JAR à la racine du projet
    jar cfm reservationvehicule.jar bin/MANIFEST.MF -C "$TMPDIR" .

    RET=$?
    # Nettoyage
    rm -rf "$TMPDIR"

    if [ $RET -eq 0 ]; then
        echo "✓ Fat JAR créé avec succès : reservationvehicule.jar"
        echo ""
        echo "Pour exécuter l'application :"
        echo "  java -jar reservationvehicule.jar"
    else
        echo "✗ Erreur lors de la création du fat JAR"
    fi
else
    echo "✗ Erreur de compilation"
fi
