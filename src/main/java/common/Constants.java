package common;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Constants {
    public static final int MAIN_WINDOW_WIDTH = 1200;
    public static final int MAIN_WINDOW_HEIGHT = 750;
    public static final String MAIN_WINDOW_TITLE = "Easy Deep Learning";

    public static final int PARAMETER_AREA_WIDTH = 400;
    public static final int DATASET_AREA_HEIGHT = 150;
    public static final int PREDICTION_TYPE_AREA_HEIGHT = 150;
    public static final int OPTIMISATION_AREA_HEIGHT = 150;
    public static final int ARCHITECTURE_AREA_HEIGHT = 150;
    public static final int BUTTON_AREA_HEIGHT = 150;

    public static final int DISPLAY_AREA_WIDTH = 500;
    public static final int VISUALISATION_AREA_HEIGHT = 250;
    public static final int TRAINING_AREA_HEIGHT = 250;
    public static final int EVALUATION_AREA_HEIGHT = 250;

    public static final String FILE = "Fichier";
    public static final String EDIT = "Éditer";
    public static final String HELP = "Aide";

    public static final String VISUALIZE = "Visualiser";
    public static final String TRAIN = "Entraîner";
    public static final String EVALUATE = "Évaluer";

    public static final String SPACED_COLUMN = " : ";


    public static final String CLASSIFICATION = "Classification";
    public static final String REGRESSION = "Prédiction";
    public static final String[] PREDICTION_TYPES = {CLASSIFICATION, REGRESSION};


    public static final String TARGET_VARIABLE = "Variable cible";
    public static final String TRAINING = "Entraînement";
    public static final String CSV_FILE = "Fichier cvs";
    public static final String PRETREATMENT = "Prétraitement";
    public static final String NONE = "Aucun";
    public static final String STANDARD_SCALER = "Standard scaler";
    public static final String MIN_MAX_SCALER = "Min-max scaler";
    public static final String[] PRETREATMENT_OPTIONS = {NONE, STANDARD_SCALER, MIN_MAX_SCALER};


    public static final String INPUTS = "Entrrée(s)";
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


    public static final String LOSS_FUNCTION = "Fonction perte";
    public static final String OPTIMIZER = "Optimiseur";
    public static final String PARAMETERS = "Paramètre(s)";
    public static final String ITERATIONS = "Itérations";

    public static final int ITERATION_MIN_VALUE = 1;
    public static final int ITERATION_MAX_VALUE = 1000;

    public static final int INPUT_MIN_VALUE = 1;
    public static final int INPUT_MAX_VALUE = 10;

    public static final int OUTPUT_MIN_VALUE = 1;
    public static final int OUTPUT_MAX_VALUE = 10;

    public static final int HIDDEN_LAYER_MIN_VALUE = 1;
    public static final int HIDDEN_LAYER_MAX_VALUE = 5;


    public static final String MEAN_SQUARED_ERROR = "Mean squared error";
    public static final String HINGE_ERROR = "Hinge error";
    public static final String NEGATIVE_LOG_LIKELIHOOD = "Negative log-likelihood";

    public static final String[] LOSS_FUNCTIONS = {MEAN_SQUARED_ERROR, HINGE_ERROR, NEGATIVE_LOG_LIKELIHOOD};

    public static final String GRADIENT = "Gradient";
    public static final String STOCHASTIC_GRADIENT = "Stochastic gradient";
    public static final String ADAM = "Adam";
    public static final String NADAM = "Nadam";
    public static final String NESTEROV = "Nesterov";
    public static final String ADAMAX = "AdaMax";

    public static final String[] OPTIMIZERS = {GRADIENT, STOCHASTIC_GRADIENT, ADAM, NADAM, NESTEROV, ADAMAX};

    public static final Font NORMAL_FONT = Font.font("Arial", FontWeight.NORMAL, 14);
}
