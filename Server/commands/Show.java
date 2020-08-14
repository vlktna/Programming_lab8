/**
 * @author Veronika Volokitina
 * @version 2
 * @since 1
 *
 * Выводит элементы коллекции
 */

package commands;

import collection.WorkerCollection;
import userAuthentication.User;
import java.util.Locale;
import java.util.ResourceBundle;

public class Show extends AbstractCommand {

    @Override
    public String execute(WorkerCollection workerCollection, User user){
        workerCollection.getCollectionFromDB();
        Locale locale = new Locale(user.getLanguage());
        ResourceBundle bundle = ResourceBundle.getBundle("command", locale);

        int size = workerCollection.getCollection().size();
        if(size != 0) {
            return String.valueOf(workerCollection.getCollection());
        }
        else{
            return  bundle.getString("emptyCollection");
        }
    }
}
