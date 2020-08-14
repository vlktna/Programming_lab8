/**
 * @author Veronika Volokitina
 * @version 3
 * @since 1
 *
 * Информация по всем доступым командам
 */

package commands;

import collection.WorkerCollection;
import userAuthentication.User;

import java.util.Locale;
import java.util.ResourceBundle;

public class Help extends AbstractCommand {

    @Override
    public String execute(WorkerCollection workerCollection, User user) {
        Locale locale = new Locale(user.getLanguage());
        ResourceBundle bundle = ResourceBundle.getBundle("command", locale);

        return bundle.getString("helpCommand");
    }


}
