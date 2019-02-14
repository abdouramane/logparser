package fr.logparser.repository.impl;

import fr.logparser.models.LogItem;
import fr.logparser.repository.ILogLoader;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LogLoader implements ILogLoader {

    private static final String SEPARATOR = " ";

    @Override
    public List<LogItem> load(String resource) throws IOException {
        List<LogItem> logItems = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(resource))) {

            stream.forEach(line -> {
                String[] lineValues = StringUtils.split(line, SEPARATOR);

                LogItem logItem = new LogItem();

                logItem.setLogTimestamp(Long.valueOf(lineValues[0]));
                logItem.setLogTime(Instant.ofEpochSecond(logItem.getLogTimestamp()));
                logItem.setServerHostname(lineValues[1]);
                logItem.setConnectedServerHostname(lineValues[2]);

                logItems.add(logItem);

            });

        } catch (IOException ex){
            System.err.printf("Error loading file %s", resource);
            throw ex;
        }

        return logItems;
    }

}
