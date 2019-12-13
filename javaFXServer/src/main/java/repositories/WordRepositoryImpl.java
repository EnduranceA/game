package repositories;

import models.Word;
import server.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WordRepositoryImpl implements WordRepository {

    private Connection connection;
    private String SQL_CHECK_WORD = "SELECT * FROM words WHERE name = ?;";

    public WordRepositoryImpl() {
        this.connection = Server.connection;
    }

    @Override
    public void save(Word word) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Word> findAll() {
        return null;
    }

    @Override
    public boolean isExist(String name) {
        try {
            PreparedStatement st = connection.prepareStatement(SQL_CHECK_WORD);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
