/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Очищает коллекцию
 */

package commands;

import collection.WorkerCollection;
import communication.ExecuteStatement;
import userAuthentication.User;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Clear extends AbstractCommand {

    private ArrayList<Integer> deletedID;

    @Override
    public String execute(WorkerCollection workerCollection, User user){
        int counter = 0;
        int size = workerCollection.getCollection().size();

        Locale locale = new Locale(user.getLanguage());
        ResourceBundle bundle = ResourceBundle.getBundle("command", locale);

        deletedID = new ArrayList<Integer>();
        for (int i = 0; i < size; i++){
            if (workerCollection.getCollection().get(i).getOwner().equals(user.getUserName())){
                int id = workerCollection.getCollection().get(i).getId();
                this.deletedID.add(id);
            }else{
                counter += 1;
            }
        }

        for(int j = 0; j < this.deletedID.size(); j++){
            ExecuteStatement.execute("DELETE FROM worker_collection WHERE id = " + this.deletedID.get(j));
        }
        workerCollection.getCollectionFromDB();

        if (counter != size){
            return bundle.getString("itemDeleted");
        }else{
            return bundle.getString("notFoundError");
        }
    }
}
