package repositories;

import models.MainWord;
import server.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MainWordRepositoryImpl implements MainWordRepository {

    private Connection connection;

    public MainWordRepositoryImpl() {
        this.connection = Server.connection;
    }

    //language=SQL
    private String SQL_CHECK_WORD = "SELECT * FROM main_words WHERE id = ?;";

    @Override
    public void save(MainWord mainWord) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<MainWord> findAll() {
        return null;
    }

    @Override
    public MainWord findBy(Integer id) {
        try {
            if (isExist(id)) {
                PreparedStatement st = connection.prepareStatement(SQL_CHECK_WORD);
                st.setInt(1, id);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    return MainWord.builder()
                            .id(id)
                            .name(rs.getString("name"))
                            .build();
                }
            }
            return null;
        }
        catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean isExist(Integer id) {
        try {
            PreparedStatement st = connection.prepareStatement(SQL_CHECK_WORD);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
