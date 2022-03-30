package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

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
                fields = line.split(",");
                if (!this.isAValidHeader(fields)) {
                    Tools.error(Message.INVALID_CSV_HEADER_ERROR);
                    return false;
                }
            }

            //Todo verifier les lignes de donnees

            bufferedReader.close();
        } catch (Exception e) {
            Tools.error(Message.CSV_READ_ERROR);
        }

        return true;
    }

    private boolean isAValidHeader(String[] fields) {
        //Il doit y avoir au moins deux champs dans header
        if ((fields == null) || (fields.length <= 1))
            return false;

        String[] cleanedFields = new String[fields.length];

        for (int ii = 0; ii < fields.length; ii++) {
            String field = fields[ii];
            if (!field.matches(Constants.HEADER_REGEX))
                return false;

            //On retire les double quotes du début et de la fin
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
