package org.wildfly.installationmanager;

import java.util.List;
import java.util.Objects;

/**
 * Represents changes in installation history between two installation points.
 */
public class InstallationChanges {

    private final List<ArtifactChange> artifacts;
    private final List<ChannelChange> channels;

    public InstallationChanges(List<ArtifactChange> artifacts, List<ChannelChange> channels) {
        Objects.requireNonNull(artifacts);
        Objects.requireNonNull(channels);

        this.artifacts = artifacts;
        this.channels = channels;
    }

    /**
     * list of modified artifacts in the changeset.
     * @return list of changes or empty list if no changes found
     */
    public List<ArtifactChange> artifactChanges() {
        return artifacts;
    }

    /**
     * list of changed channel configurations in the changeset.
     * @return list of changes or empty list if no changes found
     */
    public List<ChannelChange> channelChanges() {
        return channels;
    }
}
