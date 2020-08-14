package commands;

import collection.WorkerCollection;
import userAuthentication.User;

import java.io.IOException;

public class ErrorCommand extends AbstractCommand{

    private String msg;
    public ErrorCommand(String msg){
        this.msg = msg;
    }
    @Override
    public String execute(WorkerCollection workerCollection, User user) throws IOException {
        return this.msg;
    }
}
