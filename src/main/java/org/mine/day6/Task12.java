package org.mine.day6;

import org.mine.BaseTask;

public class Task12 extends BaseTask {

    @Override
    protected void solve() {
        long time = extractValue(_reader.nextLine());
        long maxDistance = extractValue(_reader.nextLine());

        for (int i = 0; i <= time; i++) {
            long distance = (time - i) * i;
            if (distance > maxDistance) {
                _result++;
            }
        }
    }

    private long extractValue(String line) {
        String part = (line.split(":")[1]).replaceAll("\\s", "");
        return Long.parseLong(part);
    }
}
