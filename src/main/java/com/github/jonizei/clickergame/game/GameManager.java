package com.github.jonizei.clickergame.game;

import com.google.common.collect.Lists;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public Reward click() {
        clickerCount++;
        int rewardPoints = getReward(clickerCount);

        if(rewardPoints > 0)
            return new Reward(rewardPoints, getClicksToReward(clickerCount));
        else
            return new Reward(rewardPoints - 1, 0);
    }

    public int getReward(int num) {

        Pair<Integer, Integer> pair = rewardSteps.stream()
                .filter(step -> (num % step.getKey()) == 0)
                .max(Comparator.comparing(Pair::getKey))
                .orElse(null);

        return (pair != null) ? pair.getValue() : 0;
    }

    public int getClicksToReward(int num) {

        return rewardSteps.stream()
                .mapToInt(step -> countClicks(num, step.getKey()))
                .min().getAsInt();
    }

    private int countClicks(int n, int x) {
        int i = n + 1;
        while((i % x) != 0) {
            i++;
        }
        return i - n;
    }

}
