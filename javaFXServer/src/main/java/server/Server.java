package server;

import com.beust.jcommander.JCommander;
import handler.RoomHandler;
import repositories.MainWordRepository;
import repositories.MainWordRepositoryImpl;
import repositories.WordRepository;
import repositories.WordRepositoryImpl;

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
    public static MainWordRepository mainWordRepository;
    public static WordRepository wordRepository;

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
            mainWordRepository = new MainWordRepositoryImpl();
            wordRepository = new WordRepositoryImpl();

            ServerSocket serverSocket = new ServerSocket(argv.port);
            Socket clientSocket1; Socket clientSocket2;
            while (true) {
                try {
                    clientSocket1 = serverSocket.accept();
                    System.out.println("1 user");
                    clientSocket2 = serverSocket.accept();
                    System.out.println("2 user");
                    new RoomHandler(clientSocket1, clientSocket2);
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        } catch (IOException | SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
