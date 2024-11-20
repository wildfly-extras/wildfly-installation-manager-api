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

import java.util.Objects;

/**
 * Latest version information for each installed channel
 */
public class ManifestVersion {

    /**
     * Type of the channel manifest - Maven GAV or a URL
     */
    public enum Type {MAVEN, URL}

    private final String channelId;
    private final String description;
    private final String version;
    private final Type type;

    public ManifestVersion(String channelId, String description, String version, Type type) {
        Objects.requireNonNull(channelId);
        Objects.requireNonNull(version);
        Objects.requireNonNull(type);

        this.channelId = channelId;
        this.description = description;
        this.version = version;
        this.type = type;
    }

    /**
     * channel identifier - either Maven coordinate or an URL
     * @return channel identifier
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * human read-able description of the channel version. Can be null if the description is not available.
     * @return channel description
     */
    public String getDescription() {
        return description;
    }

    /**
     * version of the channel manifest. If the manifest is provided by URL, the hash of the resolved manifest is used instead of version
     * @return channel version
     */
    public String getVersion() {
        return version;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ManifestVersion{" +
                "channelId='" + channelId + '\'' +
                ", description='" + description + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManifestVersion that = (ManifestVersion) o;
        return Objects.equals(channelId, that.channelId) && Objects.equals(description, that.description) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channelId, description, version);
    }
}
