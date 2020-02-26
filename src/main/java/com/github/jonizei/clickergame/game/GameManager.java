package com.github.jonizei.clickergame.game;

import com.google.common.collect.Lists;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class handles all the logic of the game
 *
 * @author Joni Koskinen
 * @version 2020-02-26
 */
@Component
public class GameManager {

    /**
     * Contais count of the button clicks
     */
    private int clickerCount;

    /**
     * List of pairs that contains values for clicks and points.
     *
     *
     */
    private List<Pair<Integer, Integer>> rewardSteps;

    /**
     * Default constructor of GameManager
     *
     * Initializes clicker count to zero
     * and initializes list of pairs using createRewardStep() method
     */
    public GameManager() {
        clickerCount = 0;
        rewardSteps = createRewardSteps();
    }

    /**
     * Creates new ArrayList of Pairs.
     *
     * Pair's key holds value that tells when a user wins
     * and pairs value holds value that tells how many points a user
     * will win
     *
     * @return ArrayList of Pairs
     */
    private ArrayList<Pair<Integer, Integer>> createRewardSteps() {
        return Lists.newArrayList(
                new Pair<Integer, Integer>(10, 5),
                new Pair<Integer, Integer>(100, 40),
                new Pair<Integer, Integer>(500, 250)
        );
    }

    /**
     * Increases click count by one and gets reward
     * points from getReward() method.
     *
     * If reward points are bigger than 0 then it will
     * get clicks to next reward by using getClicksToReward() method
     * then return Reward object with reward points and clicks to next reward.
     *
     * If reward points aren't bigger than 0 then it will return Reward object
     * with reward points and clicks to next reward is set to 0
     *
     * Every time user clicks the button it decreases user's points by one
     *
     * @return
     */
    public Reward click() {
        clickerCount++;
        int rewardPoints = getReward(clickerCount);

        if(rewardPoints > 0)
            return new Reward(rewardPoints, getClicksToReward(clickerCount));
        else
            return new Reward(rewardPoints, 0);
    }

    /**
     * Tries to find biggest number from reward steps that is
     * divisble with click count.
     *
     * If it founds one it will return the value
     * if not then it will return 0
     *
     * @param num Click count
     * @return If found Pair object is not null then returns value from the pair
     * else it will return 0
     */
    public int getReward(int num) {

        Pair<Integer, Integer> pair = rewardSteps.stream()
                .filter(step -> (num % step.getKey()) == 0)
                .max(Comparator.comparing(Pair::getKey))
                .orElse(null);

        return (pair != null) ? pair.getValue() : 0;
    }

    /**
     * Counts clicks to every reward using
     * countClicks() method then it will return
     * the lowest value
     *
     * @param num Click count
     * @return Clicks to the closest reward
     */
    public int getClicksToReward(int num) {

        return rewardSteps.stream()
                .mapToInt(step -> countClicks(num, step.getKey()))
                .min().getAsInt();
    }

    /**
     * Starts from click count and increases it
     * until it's divisible with given value
     *
     * @param n Click count
     * @param x Reward click value
     * @return Count of the clicks
     */
    private int countClicks(int n, int x) {
        int i = n + 1;
        while((i % x) != 0) {
            i++;
        }
        return i - n;
    }

}
