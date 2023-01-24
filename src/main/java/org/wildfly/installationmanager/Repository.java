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
 * Maven repository the channel is based on.
 */
public class Repository {
    /**
     * Unique ID of the repository. Used in Maven local cache.
     */
    private final String id;
    /**
     * URL of the Maven repository.
     */
    private final String url;

    public Repository(String id, String url) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(url);

        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return Objects.equals(id, that.id) && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url);
    }

    @Override
    public String toString() {
        return "Repository{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String asFormattedString() {
        return "id=" + id + "::" + "url=" + url;
    }
}
