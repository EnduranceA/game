package service;

import models.MainWord;
import repositories.MainWordRepository;
import repositories.WordRepository;
import server.Server;

public class WordService {

    private WordRepository wordRepository;
    private MainWordRepository mainWordRepository;

    public WordService() {
        this.wordRepository = Server.wordRepository;
        this.mainWordRepository = Server.mainWordRepository;
    }

    public MainWord getMainWord() {
        int number = (int) (Math.random() * 15);
        return mainWordRepository.findBy(number);
    }
}
