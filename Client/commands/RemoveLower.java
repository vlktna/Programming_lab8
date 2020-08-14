/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Удаляет из коллекции все элементы, ID которых меньшие, чем заданный
 */

package commands;

import collection.WorkerCollection;
import communication.ExecuteStatement;
import userAuthentication.User;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class RemoveLower extends AbstractCommand {

    private int id;
    public ArrayList<Integer> deletedID;

    public RemoveLower(int id){
        this.id = id;
    }

    @Override
    public String execute(WorkerCollection workerCollection, User user) throws NumberFormatException{
        int size = workerCollection.getCollection().size();

        Locale locale = new Locale(user.getLanguage());
        ResourceBundle bundle = ResourceBundle.getBundle("command", locale);

        for (int i = 0; i < workerCollection.getCollection().size(); i++) {
            int currentId = workerCollection.getCollection().get(i).getId();
            if (currentId < this.id &
                    workerCollection.getCollection().get(i).getOwner().equals(user.getUserName())) {

                int id = workerCollection.getCollection().get(i).getId();
                this.deletedID.add(id);

            }
        }
        if (deletedID != null) {
            for (int j = 0; j < deletedID.size(); j++) {
                ExecuteStatement.execute("DELETE FROM worker_collection WHERE id = " + deletedID.get(j));
            }return bundle.getString("itemDeleted");
        }else{
            return bundle.getString("notFoundError");

        }

    }
}
