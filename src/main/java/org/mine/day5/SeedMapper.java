package org.mine.day5;

import java.util.ArrayList;

public class SeedMapper {
    ArrayList<Model> models = new ArrayList<>();

    public long map(long input) {
        long output = input;
        for(Model model : models) {
            if(model.sourceRangeStart <= input && input <= model.sourceRangeStart + model.rangeLength) {
                output = input + model.destRangeStart - model.sourceRangeStart;
            }
        }
        return output;
    }

    public static class Model implements Comparable<Model> {
        long destRangeStart;
        long sourceRangeStart;
        long rangeLength;

        public Model(long destRangeStart, long sourceRangeStart, long rangeLength) {
            this.destRangeStart = destRangeStart;
            this.sourceRangeStart = sourceRangeStart;
            this.rangeLength = rangeLength;
        }

        @Override
        public int compareTo(Model o) {
            return Long.compare(this.sourceRangeStart, o.sourceRangeStart);
        }
    }
}
