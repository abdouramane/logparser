package fr.logparser.repository;

import fr.logparser.models.LogItem;

import java.io.IOException;
import java.util.List;

/**
 * Loader pour charger le fichier.
 * L'implémentation peut être remplacer par un accès à une BDD par ex.
 */
public interface ILogLoader {

    /**
     * Load a resource
     * @param resource the resource
     * @return items represents lines
     * @throws IOException when resource doesn't exists
     */
    List<LogItem> load(String resource) throws IOException;
}
