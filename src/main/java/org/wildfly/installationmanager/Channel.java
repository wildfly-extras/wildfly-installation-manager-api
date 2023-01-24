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

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Simplified channel representation.
 */
public class Channel {
    /**
     * Name of the channel.
     */
    private final String name;
    /**
     * Repositories the channel uses to resolve artifacts.
     */
    private final List<Repository> repositories;
    /**
     * Optional URL of channel manifest
     */
    private final Optional<URL> manifestUrl;
    /**
     * Optional Maven coordinate of the channel manifest. Accepted formats are:
     * <ul>
     *     <li>groupId:artifactId</li>
     *     <li>groupId:artifactId:version</li>
     * </ul>
     */
    private final Optional<String> manifestCoordinate;

    public Channel(String name, List<Repository> repositories, URL manifestUrl) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(repositories);
        Objects.requireNonNull(manifestUrl);

        this.name = name;
        this.repositories = repositories;
        this.manifestUrl = Optional.of(manifestUrl);
        this.manifestCoordinate = Optional.empty();
    }

    public Channel(String name, List<Repository> repositories, String manifestCoordinate) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(repositories);
        Objects.requireNonNull(manifestCoordinate);

        this.name = name;
        this.repositories = repositories;
        this.manifestUrl = Optional.empty();
        this.manifestCoordinate = Optional.of(manifestCoordinate);
    }

    public Channel(String name, List<Repository> repositories) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(repositories);

        this.name = name;
        this.repositories = repositories;
        this.manifestUrl = Optional.empty();
        this.manifestCoordinate = Optional.empty();
    }

    public String getName() {
        return name;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public Optional<URL> getManifestUrl() {
        return manifestUrl;
    }

    public Optional<String> getManifestCoordinate() {
        return manifestCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return Objects.equals(name, channel.name) && Objects.equals(repositories, channel.repositories) && Objects.equals(manifestUrl, channel.manifestUrl) && Objects.equals(manifestCoordinate, channel.manifestCoordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, repositories, manifestUrl, manifestCoordinate);
    }

    @Override
    public String toString() {
        return "Channel{" +
                "name='" + name + '\'' +
                ", repositories=" + repositories +
                ", manifestUrl=" + manifestUrl +
                ", manifestCoordinate=" + manifestCoordinate +
                '}';
    }
}
