package org.wildfly.installationmanager.spi;

import org.wildfly.installationmanager.HistoryResult;
import org.wildfly.installationmanager.ArtifactChange;

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
    List<ArtifactChange> revisionDetails(String revision) throws Exception;


    /**
     * Performs update of the server installation.
     * If no updates are found, this operation does nothing.
     *
     * @throws Exception
     */
    void update() throws Exception;

    /**
     * Lists updates available for the server installation.
     *
     * @return list of {@code ArtifactChange} available for update
     *
     * @throws Exception
     */
    List<ArtifactChange> findUpdates() throws Exception;
}
