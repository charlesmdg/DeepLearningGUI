package common;
/**
 * La classe de tous les messages du projet
 */
public class Message {
    public static final String CSV_READ_ERROR = "Erreur dans la lecture du fichier csv.";
    public static final String INVALID_CSV_HEADER_ERROR = "Entête de fichier CSV non valide.";
    public static final String UNSUFFICIENT_DATA_ERROR = "Nombre de lignes de fichier insuffisant.";
    public static final String INCOMPATIBLE_FIELD_COUNT = "Nombre de champs incompatible avec l'entête à la ligne n° " +
                                        Constants.STRING_TO_REPLACE +".";

    public static final String INVALID_FIELD_COUNT = "Nombre de champs non valide.";
    public static final String INVALID_ROW_LENGTH = "Taille de ligne non valide.";
    public static final String INVALID_DATA_ROW = "Données non valide à la ligne n° " + Constants.STRING_TO_REPLACE + ".";
    public static final String TOO_MANY_DIFFERENT_VALUES = "Trop de valeur différentes pour la variable cible : " +
                                                                    Constants.STRING_TO_REPLACE + ".";
    public static final String TRAINING_ERROR = "Erreur dans l'entraînement du modèle.";
    public static final String ARCHITECTURE_AND_DATASET_DISCORDANCY = "Incompatiblité entre le type de prédiction, le jeu de données et l'architecture.";
    public static final String WRONG_PROPORTION = "Le champ suivant doit être un nombre décimal " +
                                                    "strictement compris ente 0 et 1 : " + Constants.STRING_TO_REPLACE + ".";
    public static final String OVERFITTING_MODEL = "Le modéle surajuste le jeu de données d'entraînement.";
    public static final String NOT_IMPLEMENTED = "La fonctionnalité suivante n'est encore encore implémentée : " + Constants.STRING_TO_REPLACE + ".";
    public static final String TRAINING_IS_RUNNING = "L'entraînemement est en cours.";
}
