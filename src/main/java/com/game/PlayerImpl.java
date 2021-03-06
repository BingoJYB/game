package com.game;

import com.game.api.Player;
import com.game.logging.Logging;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a player which is able to send and receive message with other players
 *
 * @author Julian Vasa
 */
public class PlayerImpl implements Player {

    /**
     * Player name
     */
    private final String name;
    /**
     * Keep track of received messages for each other player
     */
    private final Map<Player, Integer> receivedMessages = new HashMap<>();

    /**
     * Create a new player with the provided name
     * @param name
     */
    public PlayerImpl(String name) {
        this.name = name;
    }

    /**
     * @return the player name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sends a message to another player
     * @param toPlayer call receiveMessage on otherPlayer
     * @param message  the message sent to the other player
     * @return returns message + counter (messages sent to otherPlayer)
     */
    public String sendMessage(Player toPlayer, String message){
        Logging.write(this.name + " sent a message '" +message + "' to " +toPlayer.getName(), PlayerImpl.class.getName());
        return toPlayer.receiveMessage(this, message);
    }

    /**
     * Receives a message from another player.
     * If the number of messages received from this player is 10 exit the program gracefully
     * @param fromPlayer the reference of the other player
     * @param message   the message received from the other player
     * @return  returns message + counter (messages received from otherPlayer)
     */
    public String receiveMessage(Player fromPlayer, String message) {
        Integer counter = receivedMessages.getOrDefault(fromPlayer, 0) + 1;
        receivedMessages.put(fromPlayer, counter);
        Logging.write(this.name + " received a (counter: "+counter+") message '" +message + "' from " +fromPlayer.getName(), PlayerImpl.class.getName());
        if (counter == 10) {
            System.exit(0);
        }
        return message + counter;
    }
}
