package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a User
 */
public class User implements Serializable{

    @Serial
    private static final long serialVersionUID = 202609312389L;
    
    private String _name;
    private List<Spreadsheet> _userSpreadsheets;
    private Spreadsheet _activeSpreadsheet;
    private int _numSpreadsheets;

    public User(String name){
        _name = name;
        _userSpreadsheets = new ArrayList<>();
        _numSpreadsheets = 0;
    }

    void addSpreadsheet(Spreadsheet spreadsheet){
        _userSpreadsheets.add(spreadsheet);
        ++_numSpreadsheets;
    }

    public String getName(){
        return _name;
    }
    
    // public boolean equals(Object obj){}

    // public in hashCode(){}
}
