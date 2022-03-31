package common;

import java.io.BufferedReader;
import java.io.FileReader;

public class CsvLoader {
    private Dataset dataset;

    public CsvLoader() {
    }

    public boolean check(String filePath) {
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

    private boolean checkDataLines(BufferedReader bufferedReader) throws Exception {
        //A ce stade this.columnNames est initialise
        //car l'entete a ete verifiee
        int headerFieldCount = this.dataset.getColumnNames().length;

        // On verifie les lignes de donnees
        int dataLineCount = 0;
        String line = bufferedReader.readLine();

        while (line != null) {
            dataLineCount++;
            String[] fields = line.split(Constants.COMMA);
            if (fields.length != headerFieldCount) {
                return this.checkError(Message.INCOMPATIBLE_FIELD_COUNT);
            }
            if (!checkDataLine(fields)) {
                Tools.error(Message.INVALID_DATA_ROW);
            }
            line = bufferedReader.readLine();
        }

        if (dataLineCount < Constants.CSV_DATA_LINE_MIN_COUNT) {
            return this.checkError(Message.UNSUFFICIENT_DATA_ERROR);
        }

        return true;
    }

    private boolean checkError(String message) {
        Tools.error(message);
        this.dataset = null;
        return false;
    }

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
            Tools.error(Message.INVALID_ROW_LENGTH);
            return false;
        }

        return true;
    }

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

}
