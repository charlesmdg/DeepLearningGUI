package data;

import common.Constants;
import common.Message;
import common.Tools;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Outil de chargement, verification du fichier csv et chargement du jeu de donnees
 */
public class CsvLoader {
    private Dataset dataset;
    private final String filePath;

    public CsvLoader(String filePath) {
        this.filePath = filePath;
    }

    /**
     *
     * @return le statut de la verification
     */
    public boolean check() {
        boolean answer;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

            answer = this.checkHeader(bufferedReader) && this.checkDataLines(bufferedReader);

            bufferedReader.close();
        } catch (Exception e) {
            return this.checkError(Message.CSV_READ_ERROR);
        }

        return answer;
    }

    /**
     *
     * @param bufferedReader le reader du fichier csv
     * @return le statut de la verification du header
     * @throws Exception cas de probleme de lecture du fichier
     */
    private boolean checkHeader(BufferedReader bufferedReader) throws Exception {
        String line;
        String[] fields;
        line = bufferedReader.readLine();
        if (line != null) {
            fields = line.split(Constants.COMMA);
            if (!this.checkHeader(fields)) {
                return this.checkError(Message.INVALID_CSV_HEADER_ERROR);
            }
        }
        return true;
    }

    /**
     *
     * @param bufferedReader le reader du fichier csv
     * @return le statut de la verification
     * @throws Exception cas de probleme de lecture du fichier
     */
    private boolean checkDataLines(BufferedReader bufferedReader) throws Exception {
        //A ce stade this.columnNames est initialise
        //car l'entete a ete verifiee
        int headerFieldCount = this.dataset.getColumnNames().length;

        // On verifie les lignes de donnees
        int dataLineCount = 1;
        String line = bufferedReader.readLine();

        while (line != null) {
            dataLineCount++;
            String[] fields = line.split(Constants.COMMA);
            if (fields.length != headerFieldCount) {
                return this.checkError(Message.INCOMPATIBLE_FIELD_COUNT, dataLineCount);
            }
            if (!checkDataLine(fields)) {
                return this.checkError(Message.INVALID_DATA_ROW, dataLineCount);
            }
            line = bufferedReader.readLine();
        }

        if (dataLineCount < Constants.CSV_DATA_LINE_MIN_COUNT) {
            return this.checkError(Message.UNSUFFICIENT_DATA_ERROR);
        }

        return true;
    }

    /**
     *
     * @param message le message d'erreur a afficher
     * @return toujours false
     */
    private boolean checkError(String message, int lineRank) {
        Tools.error(message, lineRank);
        this.dataset = null;
        return false;
    }

    private boolean checkError(String message) {
        Tools.error(message);
        this.dataset = null;
        return false;
    }

    /**
     *
     * @param fields la liste de champs sous forme de string
     * @return le statut de la verification du header
     */
    private boolean checkHeader(String[] fields) {
        //Il doit y avoir au moins deux champs
        // dans une entete de fichier csv
        if ((fields == null) || (fields.length <= 1))
            return false;

        String[] cleanedFields = new String[fields.length];

        for (int ii = 0; ii < fields.length; ii++) {
            String field = fields[ii];
            if (!field.matches(Constants.HEADER_REGEX))
                return false;

            //On retire les doubles quotes du début et de la fin
            String cleanedField = field.substring(1, field.length() - 1);
            cleanedFields[ii] = cleanedField;
        }

        try {
            this.dataset = new Dataset(cleanedFields);
        } catch (Exception e) {
            Tools.error(Message.INVALID_FIELD_COUNT);
            return false;
        }

        return true;
    }

    /**
     *
     * @param fields la liste des champs de donnees sous forme de string
     * @return le statut de la verification
     */
    private boolean checkDataLine(String[] fields) {
        if ((fields == null) || (fields.length <= 1))
            return false;

        double[] dataRow = new double[fields.length];

        for (int ii = 0; ii < fields.length; ii++) {
            String field = fields[ii];
            if (!field.matches(Constants.DATA_REGEX))
                return false;

            dataRow[ii] = Double.parseDouble(field);
        }

        try {
            this.dataset.addRow(dataRow);
        } catch (Exception e) {
            Tools.error(Message.INVALID_ROW_LENGTH);
            return false;
        }

        return true;
    }

    public String[] getColumnNames() {
        return this.dataset.getColumnNames();
    }

    public Dataset getDataset() {
        return dataset;
    }

    public String getFilePath() {
        return filePath;
    }
}
