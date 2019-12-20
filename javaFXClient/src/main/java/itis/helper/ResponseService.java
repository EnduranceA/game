package itis.helper;

import java.io.PrintWriter;

public class ResponseService{

    private PrintWriter out;

    public ResponseService(PrintWriter out) {
        this.out = out;
    }

    public void sendWordToServer(String word){
        out.println(word);
    }

    public void sendUserNameToServer(String username){
        out.println(username);
    }

}
