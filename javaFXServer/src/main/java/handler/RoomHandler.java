package handler;

import helper.JSONService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.GameService;

import java.io.*;
import java.net.Socket;

public class RoomHandler extends Thread{

    private Socket client1;
    private Socket client2;
    private JSONParser parser;
    private GameService gameService;
    private JSONService jsonService;

    private boolean state = false;//ходит первый

    //ввод и вывод данных для 1 клиента
    private PrintWriter out1;
    private BufferedReader in1;
    private int rating1;
    private String userName1;

    //ввод и вывод данных для 2 клиента
    private PrintWriter out2;
    private BufferedReader in2;
    private int rating2;
    private String userName2;


    //означает, что ходит первый игрок
    private boolean status = false;

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
            //игрок ходит первым
            out1.println(0);
            //указываем, что игрок ходит вторым
            out2.println(1);
            System.out.println("STATUS");

            //отправляем игрокам сгенерированное слово
            String mainWord = gameService.getMainWord();
            out1.println(mainWord);
            out2.println(mainWord);
            System.out.println("MAINWORD");

            //отправляем игрокам имена соперников
            out2.println(in1.readLine());
            out1.println(in2.readLine());
            System.out.println("USERNAME");

            //отправляем количество ходов
            out2.println(in1.readLine());
            out1.println(in2.readLine());
            System.out.println("MOVES");
            label:
            while (!gameService.isOver) {
                while (!state) {
                    if (!listen(in1, out1, out2)) {
                        break label;
                    }
                }
                while (state) {
                    if (!listen(in2, out2, out1)) {
                        break label;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean listen(BufferedReader in, PrintWriter out1, PrintWriter out2) {
        try {
            String word = in.readLine();
            System.out.println("WORD "+ word);
            //проверяем, что слово не было придумано раннее и содержится в БД
            if (gameService.check(word)) {
                //был выполнен последний ход
                if (gameService.isOver) {
                    out1.println(1);
                    out2.println(1);
                    System.out.println("I TUT");
                    //далее отправляем информацию о том, кто победил, а кто проиграл
                    if (rating1 >= rating2) {
                        out1.println(jsonService.sendNameOfWinner(userName1));
                        out2.println(jsonService.sendNameOfWinner(userName1));
                    } else {
                        out1.println(jsonService.sendNameOfWinner(userName2));
                        out2.println(jsonService.sendNameOfWinner(userName2));
                    }
                    return false;
                } else {
                    //отправляем информацию о продолжении игры
                    out1.println(0);
                    out2.println(0);
                    out1.println(5);//успешно добавлено в лист
                    state = !state;
                }
            }
            //слово было уже составлено
            else {
                //отправляем информацию о продолжении игры
                out1.println(0);
                out2.println(0);
                out1.println(7);//недобавлено в лист
                return false;
            }

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return true;
    }


}
