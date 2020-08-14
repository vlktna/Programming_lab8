package userAuthentication;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private String language;
    private ArrayList<Integer> deletedId = new ArrayList<Integer>();

    public User(String name){
        this.name = name;
    }

    public String getUserName(){
        return this.name;
    }

    public void setLanguage(String language){
        this.language = language;
    }

    public String getLanguage(){
        return this.language;
    }

    public void addDeletedId(Integer id){
        this.deletedId.add(id);
    }
    public ArrayList<Integer> getDeletedId(){
        return this.deletedId;
    }

}