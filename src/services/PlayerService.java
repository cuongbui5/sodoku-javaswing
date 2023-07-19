package services;

import database.Database;
import interfaces.Service;
import lombok.RequiredArgsConstructor;
import models.Player;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class PlayerService implements Service<Player> {
    private final Database database;
    @Override
    public List<Player> findAll() {
        List<Player> players=new ArrayList<>();
        try {
            database.connectionDB();
            String query = "SELECT * FROM player";
            Statement statement = database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Integer time=resultSet.getInt("time");
                Integer level = resultSet.getInt("level");
                String type = resultSet.getString("type");
                players.add(new Player(id,name,time,level,type));
            }
            database.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return players;
    }


    @Override
    public void save(Player player) {
        try {
            database.connectionDB();
            String query = "INSERT INTO player(name, time,level,type) VALUES ( ?, ?, ?, ?)";
            PreparedStatement state = database.getConnection().prepareStatement(query);
            state.setString(1, player.getName());
            state.setInt(2, player.getTime());
            state.setInt(3, player.getLevel());
            state.setString(4, player.getType());
            state.executeUpdate();
            database.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
