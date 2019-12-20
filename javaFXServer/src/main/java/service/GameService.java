package service;

import repositories.MainWordRepository;
import repositories.WordRepository;
import server.Server;

import java.util.ArrayList;

public class GameService {

    private String mainWord;
    private ArrayList<String> composedWords;
    private WordRepository wordRepository;
    private MainWordRepository mainWordRepository;

    public GameService() {
        this.composedWords = new ArrayList<>();
        this.wordRepository = Server.wordRepository;
        this.mainWordRepository = Server.mainWordRepository;
    }

    public String getMainWord() {
        int number = (int) (Math.random()+ 1) * 10;
        mainWord = mainWordRepository.findBy(number).getName();
        return mainWord;
    }

    public int check(String word) {
        if(!isExist(word)) {
            if (wordRepository.isExist(word)) {
                composedWords.add(word);
                return word.length();
            }
        }
        return 0;
    }

    private boolean isExist(String word) {
        for (int i = 0; i < composedWords.size(); i++) {
            if (composedWords.get(i).equals(word)) {
                return true;
            }
        }
        return false;
    }


}
