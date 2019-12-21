import models.MainWord;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import repositories.MainWordRepository;
import repositories.WordRepository;
import server.Server;
import service.GameService;

public class GameTest {

    public static WordRepository wordRepository;
    private static MainWordRepository mainWordRepository;
    private static GameService gameService;

    @BeforeClass
    public static void init() {
        wordRepository = Server.wordRepository;
        mainWordRepository = Server.mainWordRepository;
        gameService = new GameService();
    }

    @Test
    public void checkQuestionAnswersTest() {
        MainWord mainWord = mainWordRepository.findBy(1);
        Assert.assertEquals("администратор", mainWord.getName());
    }

    @Test
    public void checkRepository() {
        Assert.assertNotNull(wordRepository);
    }

    @Test
    public void getQuestionTest() {
        Assert.assertTrue(wordRepository.isExist("провод"));
    }

    @Test
    public void checkWordsInRepository() {
        Assert.assertNotNull(wordRepository.findBy(1));
    }

    public void checkAddingInList() {
        String name = "автомат";
        Assert.assertEquals(0, gameService.check(name));
    }
}
