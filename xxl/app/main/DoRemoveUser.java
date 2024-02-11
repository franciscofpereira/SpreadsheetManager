package xxl.app.main;

import java.util.ArrayList;
import java.util.List;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.core.User;

class DoRemoveUser extends Command<Calculator> {
    
    DoRemoveUser(Calculator receiver){
        super("Remover Utilizadores" , receiver);
        addStringField("userName", "Introduza uma cadeia de carateres para remover utilizador:");
    }

    @Override
    protected void execute() throws CommandException {
        
        // List of users that satisfy the requirements to be removed
        List<User> usersToRemove = new ArrayList<>();

        // Adds the users to be removed to a list
        for(User user: _receiver.getUsers()){
            if(user.getName().contains(stringField("userName")))
                usersToRemove.add(user);
        }

        // displays the number of users to be removed
        _display.popup(usersToRemove.size());

        // displays the names of the removed users
        for(User user: usersToRemove){
            _display.popup(user.getName());
        }

            
    }
}

