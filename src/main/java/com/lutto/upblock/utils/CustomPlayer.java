package com.lutto.upblock.utils;

import com.lutto.upblock.UpBlock;
import com.lutto.upblock.enums.Rank;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CustomPlayer {

    private UpBlock mainClass;

    private UUID uuid;
    private Rank rank;
    private long playtime;

    public CustomPlayer(UpBlock mainClass, UUID uuid) throws SQLException {
        this.uuid = uuid;
        this.mainClass = mainClass;

        PreparedStatement getPlayerProfile = mainClass.getDatabaseManager().getConnection().prepareStatement("SELECT RANK, PLAYTIME FROM players WHERE uuid = ?;");
        getPlayerProfile.setString(1, uuid.toString());
        ResultSet resultSet = getPlayerProfile.executeQuery();
        if (resultSet.next()) {
            rank = Rank.valueOf(resultSet.getString("RANK"));
            playtime = resultSet.getInt("PLAYTIME");
        } else {
            rank = Rank.GUEST;
            playtime = 0;
            PreparedStatement setPlayerProfile = mainClass.getDatabaseManager().getConnection().prepareStatement("INSERT INTO players (ID, UUID, RANK, PLAYTIME) VALUES(" +
                    "default," +
                    "'" + uuid + "'," +
                    "'" + rank.toString() + "'," +
                    playtime + ");");
            setPlayerProfile.executeUpdate();
        }

    }

    public void setRank(Rank rank) {
        this.rank = rank;
        try {
            PreparedStatement updatePlayerProfile = mainClass.getDatabaseManager().getConnection().prepareStatement("UPDATE players SET RANK = '" + rank + "' WHERE UUID = '" + uuid + "';");
            updatePlayerProfile.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPlaytime(long playtime) {
        this.playtime = playtime;
        try {
            PreparedStatement updatePlayerProfile = mainClass.getDatabaseManager().getConnection().prepareStatement("UPDATE players SET PLAYTIME = '" + playtime + "' WHERE UUID = '" + uuid + "';");
            updatePlayerProfile.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Rank getRank() { return rank; }
    public long getPlaytime() { return playtime; }

}
