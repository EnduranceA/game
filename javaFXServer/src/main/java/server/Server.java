package server;

import com.beust.jcommander.JCommander;
import handler.RoomHandler;

import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Server {

    public static Connection connection;

    public static void main(String[] args) {
        try {
            Args argv = new Args();
            JCommander.newBuilder()
                    .addObject(argv)
                    .build()
                    .parse(args);

            Properties properties = new Properties();
            properties.load(new FileReader(argv.proper));
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            String url = properties.getProperty("db.url");

            connection = DriverManager.getConnection(url, username, password);
            //TODO: подключить репозитории

            ServerSocket serverSocket = new ServerSocket(argv.port);
            Socket clientSocket1; Socket clientSocket2;
            while (true) {
                try {
                    clientSocket1 = serverSocket.accept();
                    clientSocket2 = serverSocket.accept();
                    System.out.println("Successful connection");
                    new RoomHandler(clientSocket1, clientSocket2);
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
