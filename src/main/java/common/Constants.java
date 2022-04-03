package common;

import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * La classe de toutes les constantes du projet
 */
public class Constants {
    public static final int MAIN_WINDOW_WIDTH = 1100;
    public static final int MAIN_WINDOW_HEIGHT = 750;
    public static final String MAIN_WINDOW_TITLE = "Easy Deep Learning";

    public static final Color SCENE_TOP_LEFT_COLOR = Color.WHITE;
    public static final Color SCENE_BOTTOM_RIGHT_COLOR = Color.web("#7faaf0");

    public static final int NUMERICAL_FIELD_MAX_LENGTH = 5;
    public static final int AREA_PADDING = 3;
    public static final int RADIO_BUTTON_INTERSPACE = 20;
    public static final int RADIO_BUTTON_GROUP_WIDTH = 150;
    public static final int RADIO_BUTTON_GROUP_HEIGHT = 94;

    public static final int SPINNER_WIDTH = 60;
    public static final int COMBOBOX_WIDTH = 190;
    public static final int TEXT_TEXTFIELD_WIDTH = 115;
    public static final int NUM_TEXTFIELD_WIDTH = 50;
    public static final int INPUT_FIELD_MAX_WIDTH = 195;

    public static final int PARAMETER_AREA_WIDTH = 360;
    public static final int DATASET_AREA_HEIGHT = 150;
    public static final int PREDICTION_TYPE_AREA_HEIGHT = 120;
    public static final int OPTIMISATION_AREA_HEIGHT = 150;
    public static final int ARCHITECTURE_AREA_HEIGHT = 150;
    public static final int BUTTON_AREA_HEIGHT = 50;

    public static final String VISUALIZATION = "Visualisation";
    public static final String TRAINING = "Entraînement";
    public static final String EVALUATION = "Évaluation";
    public static final int DISPLAY_AREA_WIDTH = 600;
    public static final int VISUALIZATION_AREA_HEIGHT = 240;
    public static final int TRAINING_AREA_HEIGHT = 275;
    public static final int EVALUATION_AREA_HEIGHT = 140;
    public static final int DISPLAY_AREA_TOP_MARGIN = 20;

    public static final String INFORMATION = "Information";
    public static final String ERROR = "Erreur";
    public static final String CONFIRMATION = "Confirmation";
    public static final String STRING_TO_REPLACE = "@@@";

    public static final String FILE = "Fichier";
    public static final String EDIT = "Éditer";
    public static final String HELP = "Aide";

    public static final String START_TRAINING = "    Démarrer\nl'entraînement";
    public static final String STOP_TRAINING = "      Arrêter\nl'entraînement";
    public static final String CANCEL_TRAINING = "   Supprimer\nl'entraînement";
    public static final int BUTTON_WIDTH = 110;
    public static final int BUTTON_HEIGHT = 50;

    public static final Font NORMAL_FONT = Font.font("Arial", FontWeight.NORMAL, 14);
    public static final Font TITLE_FONT = Font.font("Arial", FontWeight.BLACK, FontPosture.ITALIC, 16);
    public static final String COMMA = ",";
    public static final String CR = "\n";
    public static final String SPACED_COLON = " : ";
    public static final String EMPTY_STRING = "";
    public static final String[] EMPTY_STRINGS = {EMPTY_STRING};
    public static final int IMPOSSIBLE_INDEX = -1;

    public static final String CSV = "CSV";
    public static final String STAR_DOT_CSV = "*.csv";
    public static final String ALL = "All";
    public static final String STAR_DOT_STAR = "*.*";

    public static final String HEADER_REGEX = "^\"[a-zA-Z][_a-zA-Z0-9]*\"$";
    public static final String DATA_REGEX = "(^-?[0-9]+$|^-?\\.[0-9]+$|^-?[0-9]+\\.[0-9]*$)";

    public static final String PREDICTION_TYPE = "Type de prédiction";
    public static final String CLASSIFICATION = "Classification";
    public static final String REGRESSION = "Régression";
    public static final String[] PREDICTION_TYPES = {CLASSIFICATION, REGRESSION};

    public static final String DATASET = "Jeu de données";
    public static final String TARGET_VARIABLE = "Variable cible";
    public static final String TRAINING_RATE = "Entraînement";
    public static final String CSV_FILE = "Fichier cvs";
    public static final String CHOOSE_AND_DOTS = "Choisir...";
    public static final String FILE_CHOOSER_TITLE = "Choix d'un fichier CSV";
    public static final int CSV_DATA_LINE_MIN_COUNT = 10;

    public static final String PRETREATMENT = "Prétraitement";
    public static final String NONE = "Aucun";
    public static final String STANDARD_SCALER = "Standard scaler";
    public static final String MIN_MAX_SCALER = "Min-max scaler";
    public static final String[] PRETREATMENT_OPTIONS = {NONE, STANDARD_SCALER, MIN_MAX_SCALER};

    public static final String ARCHITECTURE = "Architecture";
    public static final String INPUTS = "Entrée(s)";
    public static final String OUTPUTS = "Sortie(s)";
    public static final String HIDDEN_LAYERS = "Couche(s) cachée(s)";
    public static final String ACTIVATION_FUNCTION = "Fonction d'activation";

    public static final String RELU = "Rectified linear unit";
    public static final String SIGMOID = "Sigmoïd";
    public static final String TANH = "Hyperbolic tangent";
    public static final String IDENTITY = "Identity";
    public static final String SOFTMAX = "Softmax";
    public static final String SOFTPLUS = "Softplus";
    public static final String[] ACTIVATION_FUNCTIONS = {RELU, SIGMOID, TANH, IDENTITY, SOFTMAX, SOFTPLUS};

    public static final String OPTIMIZATION = "Optimisation";
    public static final String LOSS_FUNCTION = "Fonction perte";
    public static final String OPTIMIZER = "Optimiseur";
    public static final String PARAMETERS = "Paramètre(s)";
    public static final String ITERATIONS = "Itérations";

    public static final int ITERATION_MIN_VALUE = 0;
    public static final int ITERATION_MAX_VALUE = 1000;

    public static final int INPUT_MIN_VALUE = 1;
    public static final int INPUT_MAX_VALUE = 10;

    public static final int OUTPUT_MIN_VALUE = 1;
    public static final int OUTPUT_MAX_VALUE = 10;

    public static final int HIDDEN_LAYER_MIN_VALUE = 1;
    public static final int HIDDEN_LAYER_MAX_VALUE = 20;

    public static final String MEAN_SQUARED_ERROR = "Mean squared error";
    public static final String HINGE_ERROR = "Hinge error";
    public static final String SQUARED_HINGE_ERROR = "Squared hinge error";
    public static final String NEGATIVE_LOG_LIKELIHOOD = "Negative log-likelihood";
    public static final String[] LOSS_FUNCTIONS = {MEAN_SQUARED_ERROR, NEGATIVE_LOG_LIKELIHOOD,
                                                    HINGE_ERROR, SQUARED_HINGE_ERROR};

    public static final String STOCHASTIC_GRADIENT = "Stochastic gradient";
    public static final String ADAM = "Adam";
    public static final String NADAM = "Nadam";
    public static final String NESTEROV = "Nesterov";
    public static final String ADAMAX = "AdaMax";

    public static final int DEFAULT_HIDDEN_LAYER_COUNT = 2;
    public static final int DIFFERENT_VALUE_MAX_COUNT = 10;

    public static final String[] OPTIMIZERS = {STOCHASTIC_GRADIENT, ADAM, NADAM, NESTEROV, ADAMAX};
    public static final double DEFAULT_LEARNING_RATE = 0.1;
    public static final int DEFAULT_ITERATION_COUNT = 100;
    public static final double DEFAULT_TRAINING = 0.75;

    public static final String TRAINING_DISPLAY_INTRODUCTION = "Exactitude de l'itération n° ";
    public static final String PERCENT = " %";
    public static final int TRAINING_DELAY = 100;
    public static final int NEURON_RADIUS = 10;
    public static final Color INPUT_OUPUT_NEURON_FILL_COLOR = Color.BLUE;
    public static final Color HIDDEN_NEURON_FILL_COLOR = Color.RED;
    public static final Color NEURON_LINK_COLOR = Color.GRAY;
    public static final Color NETWORK_BACKGROUND_COLOR = Color.WHITE;

    public static final String ACCURACY = "Exactitude";
    public static final String PRECISION = "Précision";
    public static final String RECALL = "Sensibilité";
    public static final String F1SCORE = "Score F1";
    public static final ButtonType DELETE = new ButtonType("Supprimer");
    public static final ButtonType DO_NOT_DELETE = new ButtonType("Ne pas supprimer");

}
