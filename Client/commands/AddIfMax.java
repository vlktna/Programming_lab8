/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Добавляет новый элемент в коллекцию, если его ID превышает значение наибольшего элемента этой коллекции
 */

package commands;

import aboutWorker.Worker;
import collection.WorkerCollection;
import userAuthentication.User;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddIfMax extends AbstractCommand {

    private Worker worker;

    public AddIfMax(Worker worker){
        this.worker = worker;
    }

    @Override
    public String execute(WorkerCollection workerCollection, User user) {
        int maxId = -1;
        int size = workerCollection.getCollection().size();

        Locale locale = new Locale(user.getLanguage());
        ResourceBundle bundle = ResourceBundle.getBundle("command", locale);

        for (int i = 0; i < size; i++) {
            if (workerCollection.getCollection().get(i).getId() > maxId) {
                maxId = workerCollection.getCollection().get(i).getId();
            }
        }
        if (this.worker.getId() > maxId) {
            worker.setOwner(user.getUserName());
            workerCollection.addWorkerToDB(worker);
            workerCollection.getCollectionFromDB();
            return bundle.getString("addNewWorker");
        } else {
            return bundle.getString("addIfMaxError");
        }
    }
}
