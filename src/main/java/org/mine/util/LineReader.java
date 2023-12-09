package org.mine.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;

public class LineReader {
    public static LineReader readerForDefaultFile(String className) {
        return new LineReader(Constants.RELATIVE_RESOURCES_PATH + Constants.TASK_INPUT_DIR_NAME + className);
    }

    private static final Logger _logger = LoggerFactory.getLogger(LineReader.class.getName());

    String _filePath;
    File _file;
    BufferedReader _bufferedReader;


    private LineReader(String filePath) {
        _filePath = filePath;
        try {
            _file = new File(filePath);
            _bufferedReader = new BufferedReader(new FileReader(_file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String nextLine() {
        try {
            return _bufferedReader.readLine();
        } catch (IOException e) {
            _logger.error("Could not read line", e);
            return null;
        }
    }

    public String[] wholeFile() {
        ArrayList<String> lines = new ArrayList<>();
        try {
            String line = _bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = _bufferedReader.readLine();
            }
            return lines.toArray(new String[]{});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanup() {
        if (_bufferedReader != null) {
            try {
                _bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}

