package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that represents a User
 */
public class User implements Serializable{

    @Serial
    private static final long serialVersionUID = 202609312389L;
    
    private final String _name;
    private List<Spreadsheet> _spreadsheets = new ArrayList<>();

    public User(String name){
        _name = name;
    }

    void addSpreadsheet(Spreadsheet spreadsheet){
        _spreadsheets.add(spreadsheet);
    }

    public List<Spreadsheet> getSpreadsheets(){
        return Collections.unmodifiableList(_spreadsheets);
    }

    public final String getName(){
        return _name;
    }
    
}
