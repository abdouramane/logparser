package fr.logparser.repository.impl;

import fr.logparser.models.LogItem;
import fr.logparser.models.Server;
import fr.logparser.repository.ILogLoader;
import fr.logparser.repository.ILogRepository;
import org.junit.Assert;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LogRepository implements ILogRepository {

    private final String file;

    private final ILogLoader logLoader;

    public LogRepository(String file, ILogLoader logLoader) {
        Assert.assertNotNull("File name must not be empty!", file);
        this.file = file;
        this.logLoader = logLoader;
    }

    @Override
    public Optional<List<Server>> getAllLogs() throws IOException {
        List<LogItem> logItems = this.logLoader.load(file);

        return Optional.ofNullable(mapToServersResult(logItems));
    }

    @Override
    public Optional<List<Server>> getLogsBy(ChronoUnit chronoUnit) throws IOException {
        List<LogItem> logItems = this.logLoader.load(file);

        List<LogItem> logsItemByHours = getLogItemsBy(logItems, chronoUnit);

        return Optional.ofNullable(mapToServersResult(logsItemByHours));
    }


    ////////////////////////////// Private Functions /////////////////////////////////////////////////////:

    private List<LogItem> getLogItemsBy(List<LogItem> logItems, ChronoUnit chronoUnit) {
        return logItems.stream().filter(logItem ->
                logItem.getLogTime().isBefore(Instant.now())
                        &&
                        logItem.getLogTime().isAfter(Instant.now().minus(1, chronoUnit))
            ).collect(Collectors.toList());
    }

    private List<Server> mapToServersResult(List<LogItem> logItems) {

        List<Server> servers = new ArrayList<>();

        logItems.stream().distinct().forEach(logItem -> {
            Server server = new Server();
            server.setName(logItem.getServerHostname());

            logItems.forEach( line ->
                    {
                        if(line.getServerHostname().equals(server.getName())) {
                            server.getConnectedServers().add(logItem.getConnectedServerHostname());
                            server.updateAmoutOfConnection();
                        }
                    }
            );

            servers.add(server);
        });

        return servers;

    }

}
