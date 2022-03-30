package common;

import java.io.BufferedReader;
import java.io.FileReader;

public class CsvLoader {
    private String[] columnNames;

    public CsvLoader() {

    }

    //Todo
    public boolean check(String filePath) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            String[] fields;
            line = bufferedReader.readLine();
            if (line != null) {
                fields = line.split(Constants.COMMA);
                if (!this.isAValidHeader(fields)) {
                    Tools.error(Message.INVALID_CSV_HEADER_ERROR);
                    return false;
                }
            }
            //A ce stade this.columnNames est initialisé
            //car l'entete a ete verifiee
            int headerFieldCount = this.columnNames.length;

            int dataLineCount = 0;
            line = bufferedReader.readLine();

            while (line != null) {
                dataLineCount++;
                fields = line.split(Constants.COMMA);
                if(fields.length != headerFieldCount){
                    Tools.error(Message.INCOMPATIBLE_FIELD_COUNT, String.valueOf(dataLineCount));
                }

                line = bufferedReader.readLine();
            }

            if (dataLineCount < Constants.CSV_DATA_LINE_MIN_COUNT) {
                Tools.error(Message.UNSUFFICINENT_DATA_ERROR);
                return false;
            }

            //Todo verifier les lignes de donnees

            bufferedReader.close();
        } catch (Exception e) {
            Tools.error(Message.CSV_READ_ERROR);
        }

        return true;
    }

    private boolean isAValidHeader(String[] fields) {
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
        this.columnNames = cleanedFields;

        return true;
    }

    public String[] getColumnNames() {
        return this.columnNames;
    }
}
