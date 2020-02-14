package com.github.jonizei.clickergame.game;

import com.google.common.collect.Lists;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameManager {

    private int clickerCount;
    private List<Pair<Integer, Integer>> rewardSteps;

    public GameManager() {
        clickerCount = 0;
        rewardSteps = createRewardSteps();
    }

    private ArrayList<Pair<Integer, Integer>> createRewardSteps() {
        return Lists.newArrayList(
                new Pair<Integer, Integer>(10, 5),
                new Pair<Integer, Integer>(100, 40),
                new Pair<Integer, Integer>(500, 250)
        );
    }

    public int click() {
        clickerCount++;
        System.out.println("Clicker count: " + clickerCount);
        return getReward(clickerCount);
    }

    public int getReward(int num) {

        int points = 0;
        int highestKeyValue = 0;
        for(Pair<Integer, Integer> step : rewardSteps) {
            if(step.getKey() > highestKeyValue && (num % step.getKey()) == 0) {
                highestKeyValue = step.getKey();
                points = step.getValue();
            }
        }

        return points;
    }

}
