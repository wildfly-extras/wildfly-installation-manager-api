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

/**
 * Represents a change in artifact version or status.
 */
public class ArtifactChange {
    /**
     * Type of the change. Artifact can be
     * <ul>
     *     <li>updated - when a version changed</li>
     *     <li>installed - when a new artifact was installed in the server</li>
     *     <li>removed - when an artifact was removed from the server</li>
     * </ul>
     */
    public enum Status {UPDATED, INSTALLED, REMOVED}

    /**
     * Previous version of the artifact or null if a new artifact is installed
     */
    private final String oldVersion;
    /**
     * New version of the artifact or null if an artifact is removed
     */
    private final String newVersion;
    /**
     * Maven coordinates of the artifact. Format groupId:artifactId[:classifier]
     */
    private final String artifactName;
    /**
     * Type of change captured in {@code ArtifactChange}
     */
    private final Status status;

    public ArtifactChange(String oldVersion, String newVersion, String artifactName, Status status) {
        this.oldVersion = oldVersion;
        this.newVersion = newVersion;
        this.artifactName = artifactName;
        this.status = status;
    }

    public String getOldVersion() {
        return oldVersion;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public String getArtifactName() {
        return artifactName;
    }

    public String getOldGav() {
        return getArtifactName() + ":" + getOldVersion();
    }

    public String getNewGav() {
        return getArtifactName() + ":" + getNewVersion();
    }

    public Status getStatus() {
        return status;
    }
}
