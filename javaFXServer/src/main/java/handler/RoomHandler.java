package handler;

import helper.JSONService;
import org.json.simple.parser.JSONParser;
import service.GameService;

import java.io.*;
import java.net.Socket;

public class RoomHandler extends Thread{

    private Socket client1;
    private Socket client2;
    private JSONParser parser;
    private GameService gameService;
    private JSONService jsonService;

    private int turnCount = 3;

    //ввод и вывод данных для 1 клиента
    private PrintWriter out1;
    private BufferedReader in1;
    private int rating1 = 0;
    private String userName1;

    //ввод и вывод данных для 2 клиента
    private PrintWriter out2;
    private BufferedReader in2;
    private int rating2 = 0;
    private String userName2;

    public RoomHandler(Socket client1, Socket client2) {
       try {
           this.client1 = client1;
           this.client2 = client2;
           this.out1 = new PrintWriter(client1.getOutputStream(), true);
           this.in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
           this.out2 = new PrintWriter(client2.getOutputStream(), true);
           this.in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
           this.parser = new JSONParser();
           this.jsonService = new JSONService();
           this.gameService = new GameService();
           //подключились два игрока
           this.start();
       }
       catch (IOException e) {
           throw new IllegalArgumentException(e);
       }
    }

    @Override
    public void run() {
        try {
            //игрок ходит
            out1.println(jsonService.sendStatus(0));
            //указываем, что игрок ходит вторым
            out2.println(jsonService.sendStatus(1));

            //отправляем игрокам сгенерированное слово
            String mainWord = gameService.getMainWord();
            out1.println(jsonService.sendMainWord(mainWord));
            out2.println(jsonService.sendMainWord(mainWord));

            //отправляем игрокам имена соперников
            userName1 = in1.readLine();
            out2.println(jsonService.sendUsername(userName1));
            userName2 = in2.readLine();
            out1.println(jsonService.sendUsername(userName2));

            out1.println(jsonService.getCommandStartGameWithYourTurn());
            out2.println(jsonService.getCommandStartGameWithWait());

            while(turnCount > 0) {
                rating1 += gameService.check(in1.readLine());

                out1.println(jsonService.sendRating(rating1));
                out2.println(jsonService.sendRatingOfRival(rating1));

                out2.println(jsonService.getCommandYourTurn());
                out1.println(jsonService.getCommandWait());

                rating2 += gameService.check(in2.readLine());

                out2.println(jsonService.sendRating(rating2));
                out1.println(jsonService.sendRatingOfRival(rating2));

                out1.println(jsonService.getCommandYourTurn());
                out2.println(jsonService.getCommandWait());

                turnCount--;
            }
            if(rating1 > rating2){
                out1.println(jsonService.sendNameOfWinner(userName1));
                out2.println(jsonService.sendNameOfWinner(userName1));
            } else {
                out1.println(jsonService.sendNameOfWinner(userName2));
                out2.println(jsonService.sendNameOfWinner(userName2));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
