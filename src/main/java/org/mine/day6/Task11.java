package org.mine.day6;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.mine.BaseTask;

import java.util.ArrayList;
import java.util.Arrays;

public class Task11 extends BaseTask {

    @Override
    protected void solve() {
        int[] timeValues = extractValues(_reader.nextLine());
        int[] distanceValues = extractValues(_reader.nextLine());
        ArrayList<Pair<Integer, Integer>> races = new ArrayList<>();
        for (int i = 0; i < timeValues.length; i++) {
            races.add(new ImmutablePair<>(timeValues[i], distanceValues[i]));
        }

        int[] counts = new int[races.size()];
        for (int i = 0; i < races.size(); i++) {
            Pair<Integer, Integer> race = races.get(i);
            int maxDistance = race.getRight();
            int time = race.getLeft();
            for (int j = 0; j <= time; j++) {
                int distance = (time - j) * j;
                if (distance > maxDistance) {
                    counts[i]++;
                }
            }
        }
        _result = 1;
        for(int count : counts) {
            _result *= count;
        }
    }


    private int[] extractValues(String line) {
        String[] parts = (line.split(":")[1]).trim().split("\\s+");
        return Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
    }

}

