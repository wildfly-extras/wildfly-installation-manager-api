package org.wildfly.installationmanager.spi;

import org.wildfly.installationmanager.HistoryResult;
import org.wildfly.installationmanager.HistoryRevisionResult;

import java.util.List;

public interface InstallationManager {
    /**
     * Return all changes from the managed server history.
     *
     * @return
     * @throws Exception
     */
    List<HistoryResult> history() throws Exception;

    /**
     * Return description of a changes with if {@code revision} from the managed server history.
     *
     * @return
     * @throws Exception
     */
    List<HistoryRevisionResult> revisionDetails(String revision) throws Exception;
}
