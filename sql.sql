CREATE TABLE public.service (
    numero integer NOT NULL,
    libelle character varying(20),
    PRIMARY KEY (numero)
);

CREATE TABLE public.type (
    numero integer NOT NULL,
    libelle character varying(20),
    PRIMARY KEY (numero)
);

CREATE TABLE public.personne (
    matricule integer NOT NULL,
    nom character varying(20),
    prenom character varying(20),
    mdp character varying(50),
    telephone integer,
    "noService" integer,
    role character varying(20),
    PRIMARY KEY (matricule),
    FOREIGN KEY ("noService") REFERENCES public.service(numero)
);

CREATE TABLE public.vehicule (
    marque character varying,
    modele character varying,
    "noType" integer,
    immat character varying NOT NULL,
    PRIMARY KEY (immat),
    FOREIGN KEY ("noType") REFERENCES public.type(numero)
);

CREATE TABLE public.demande (
    datereserv date NOT NULL,
    numero integer NOT NULL,
    datedebut date,
    matricule integer,
    notype integer,
    duree integer,
    dateretoureffectif date,
    etat character varying(50),
    immat character varying(10),
    PRIMARY KEY (datereserv, numero),
    FOREIGN KEY (matricule) REFERENCES public.personne(matricule),
    FOREIGN KEY (notype) REFERENCES public.type(numero),
    FOREIGN KEY (immat) REFERENCES public.vehicule(immat)
);
