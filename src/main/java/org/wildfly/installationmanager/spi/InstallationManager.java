package org.wildfly.installationmanager.spi;

import org.wildfly.installationmanager.HistoryResult;
import org.wildfly.installationmanager.HistoryRevisionResult;

import java.nio.file.Path;
import java.util.List;

public interface InstallationManager {
    InstallationManager initialize(Path installationDir) throws Exception;

    String getName();
    List<HistoryResult> history() throws Exception;
    List<HistoryRevisionResult> history(String revision) throws Exception;
}
