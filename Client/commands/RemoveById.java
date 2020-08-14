/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Удаляет элемент коллекции по заданному ID
 */

package commands;

import collection.WorkerCollection;
import communication.ExecuteStatement;
import userAuthentication.User;
import java.util.Locale;
import java.util.ResourceBundle;

public class RemoveById extends AbstractCommand {

    private int id;

    public RemoveById(int id){
        this.id = id;
    }

    @Override
    public String execute(WorkerCollection workerCollection, User user) throws NumberFormatException{
        int counter = 0;
        workerCollection.getCollectionFromDB();

        int size = workerCollection.getCollection().size();
        String reply = "";

        Locale locale = new Locale(user.getLanguage());
        ResourceBundle bundle = ResourceBundle.getBundle("command", locale);


        for (int i = 0; i < workerCollection.getCollection().size(); i++) {
            if (workerCollection.getCollection().get(i).getId() == id &
                    workerCollection.getCollection().get(i).getOwner().equals(user.getUserName())) {

                ExecuteStatement.execute("DELETE FROM worker_collection WHERE id = " + id);
                workerCollection.getCollection().remove(i);
                reply = bundle.getString("itemDeleted");
                user.addDeletedId(id);
            } else {
                counter += 1;
            }
        }
        if (counter == size) {
            reply = bundle.getString("notFoundError");
        }

        return reply;
    }
}
