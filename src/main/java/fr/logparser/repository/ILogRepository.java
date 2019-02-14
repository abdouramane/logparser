package fr.logparser.repository;

import fr.logparser.models.Server;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public interface ILogRepository {

    Optional<List<Server>> getAllLogs() throws IOException;

    Optional<List<Server>> getLogsBy(ChronoUnit chronoUnit) throws IOException;
}
