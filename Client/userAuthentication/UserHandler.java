/**
 * @author Veronika Volokitina
 * @version 3
 * @since 3
 *
 * Вход в систему
 */

package userAuthentication;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UserHandler {

    private String name;

    public Map<String, String> getUsersFromDB(){
        Map<String, String> users = new HashMap<String, String>();

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab7");
            Statement statement = connection.createStatement();
            String sql = "SELECT name, password FROM USERS";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                users.put(password, name);
            }
            statement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Ошибка при подключении к базе данных");
        }

        return users;
    }

    public void addUserToDB(String name, String password){
        try {
            Class.forName("org.postgresql.Driver");
//            Connection connection = DriverManager.getConnection("jdbc:postgresql://pg:5432/studs", "s284754", "uhn393");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab7");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO USERS (name, password) VALUES (?, ?)");
            statement.setString(1, name);
            statement.setString(2, password);
            statement.executeUpdate();

            statement.close();
            connection.close();
        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("Ошибка при подключении к базе данных");
        }
    }

    public String getName(){
        return this.name;
    }

}
