package org.mine.day5;

import org.mine.BaseTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Task9 extends BaseTask {

    @Override
    protected void solve() {

        String line = _reader.nextLine();
        _reader.nextLine();
        _reader.nextLine();
        List<Long> mappedValues = Arrays.stream((line.split(":")[1]).trim().split("\\s+"))
                .map(Long::parseLong).toList();
        ArrayList<SeedMapper> mappers = new ArrayList<>();

        while(line != null) {
            if(line.isBlank()) {
                line = _reader.nextLine();
                continue;
            } else if(Character.isLetter(line.charAt(0))) {
                if(!mappers.isEmpty()) {
                    mappers.getLast().models.sort(SeedMapper.Model::compareTo);
                }
                mappers.add(new SeedMapper());
            } else if(Character.isDigit(line.charAt(0))) {
                Long[] values = Arrays.stream(line.split("\\s+")).map(s -> Long.parseLong(s.trim())).toArray(Long[]::new);
                SeedMapper.Model model = new SeedMapper.Model(values[0], values[1], values[2]);
                mappers.getLast().models.add(model);
            }
            line = _reader.nextLine();
        }

        ArrayList<Long> results = new ArrayList<>();
        long valueToMap;
        for(long value : mappedValues) {
            valueToMap = value;
            for(SeedMapper mapper : mappers) {
                valueToMap = mapper.map(valueToMap);
            }
            results.add(valueToMap);
        }

        Optional<Long> minValue = results.stream().min(Long::compareTo);

        _result = minValue.orElse(0L);
    }
}
