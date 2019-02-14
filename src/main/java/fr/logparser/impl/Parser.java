package fr.logparser.impl;

import fr.logparser.IParser;
import fr.logparser.models.Filter;
import fr.logparser.models.Server;
import fr.logparser.repository.ILogRepository;
import org.junit.Assert;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Parser implements IParser {

    private final ILogRepository logRepository;

    public Parser(ILogRepository logRepository) {
        Assert.assertNotNull("LogRepository must not be null!", logRepository);

        this.logRepository = logRepository;
    }


    @Override
    public List<Server> getConnectedServers(Filter filter) throws IOException {

        Optional<List<Server>> result = Optional.empty();
        switch (filter) {
            case ALL:
                result = this.logRepository.getAllLogs();
                break;
            case BY_HOUR:
                result = this.logRepository.getLogsBy(ChronoUnit.HOURS);
                break;
        }

        return result.orElse(new ArrayList<>());


    }


}
