package data;

import common.Constants;
import common.Message;
import common.Tools;

import java.util.ArrayList;
import java.util.HashSet;

public class Dataset {
    private final String[] columnNames;
    private final ArrayList<double[]> data;

    public Dataset(String[] columnNames) throws Exception {
        if (columnNames == null || columnNames.length == 0)
            throw new Exception(Message.INVALID_ROW_LENGTH);

        this.columnNames = columnNames;
        this.data = new ArrayList<>();
    }

    /**
     *
     * @param row la ligne a rajouter
     *
     *
     *
     * @throws Exception
     */
    public void addRow(double[] row) throws Exception{
        if (row == null || row.length != columnNames.length)
            throw new Exception(Message.INVALID_ROW_LENGTH);

        this.data.add(row);
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    /**
     *
     * @param columnName : nom de la colonne
     * @return le nombre de valeur differente dans la colonne
     */
    public int getValueCount(String columnName){
        int columnIndex = Tools.indexOf(this.columnNames, columnName);

        if(columnIndex== Constants.IMPOSSIBLE_INDEX)
            return 0;

        HashSet<Double> set = new HashSet<>();
        for (double[] row: this.data){
            set.add(row[columnIndex]);
        }

        return set.size();
    }

    public int getColumnIndex(String columnName){
        int columnIndex = Tools.indexOf(this.columnNames, columnName);
        return columnIndex;
    }

}
