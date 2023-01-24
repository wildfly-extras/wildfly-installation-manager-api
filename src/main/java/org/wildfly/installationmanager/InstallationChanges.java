/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2023 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
