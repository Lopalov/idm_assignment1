package tudelft.wis.idm_tasks.boardGameTracker;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;

import java.util.Collection;
import java.util.LinkedList;

public class PlayerImplementation implements tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player {
    private String name;
    private String nickName;
    private Collection<BoardGame> gameCollection = new LinkedList<BoardGame>();

    /**
     * Instantiates a new Player POJO.
     *
     * @param name     name
     * @param nickName nickname
     */
    public PlayerImplementation(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String getPlayerName() {
        return name;
    }

    @Override
    public String getPlayerNickName() {
        return nickName;
    }

    @Override
    public Collection<BoardGame> getGameCollection() {
        return gameCollection;
    }


    @Override
    public String toVerboseString() {
        String result = name;
        if (nickName != null) {
            result = result + " (" + nickName + ")";
        }
        return result;
    }
}
