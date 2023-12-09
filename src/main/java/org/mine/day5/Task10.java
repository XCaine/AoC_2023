package org.mine.day5;

import org.mine.BaseTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task10 extends BaseTask {
    @Override
    protected void solve() {
        String line = _reader.nextLine();
        _reader.nextLine();
        _reader.nextLine();
        List<Long> initialMappedValues = Arrays.stream((line.split(":")[1]).trim().split("\\s+"))
                .map(Long::parseLong).toList();

        ArrayList<SeedMapper> mappers = loadMappers(line);

        _result = Long.MAX_VALUE;

        for (int i = 0; i < initialMappedValues.size(); i += 2) {
            Long originalValue = initialMappedValues.get(i);
            Long range = initialMappedValues.get(i + 1);
            for (int j = 0; j < range; j++) {
                long value = originalValue + j;
                for (SeedMapper mapper : mappers) {
                    value = mapper.map(value);
                }
                if(value < _result) {
                    _result = value;
                    _LOGGER.info(STR."New result: \{_result}");
                }
            }
        }
    }

    @Override
    public String getDisabledReason() {
        return "Execution time is too long (~6 mins)";
    }

    private ArrayList<SeedMapper> loadMappers(String line) {
        ArrayList<SeedMapper> mappers = new ArrayList<>();
        while (line != null) {
            if (line.isBlank()) {
                line = _reader.nextLine();
                continue;
            } else if (Character.isLetter(line.charAt(0))) {
                if (!mappers.isEmpty()) {
                    mappers.getLast().models.sort(SeedMapper.Model::compareTo);
                }
                mappers.add(new SeedMapper());
            } else if (Character.isDigit(line.charAt(0))) {
                Long[] values = Arrays.stream(line.split("\\s+")).map(s -> Long.parseLong(s.trim())).toArray(Long[]::new);
                SeedMapper.Model model = new SeedMapper.Model(values[0], values[1], values[2]);
                mappers.getLast().models.add(model);
            }
            line = _reader.nextLine();
        }
        return mappers;
    }
}
