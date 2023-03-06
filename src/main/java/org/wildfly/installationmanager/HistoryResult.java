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

import java.time.Instant;
import java.util.Objects;

public class HistoryResult {

    private String hash;
    private Instant timestamp;
    private String type;
    private String description;

    public HistoryResult(String hash, Instant timestamp, String type, String description) {
        this.hash = hash;
        this.timestamp = timestamp;
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return this.hash;
    }

    public Instant timestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryResult that = (HistoryResult) o;
        return Objects.equals(hash, that.hash) && Objects.equals(timestamp, that.timestamp)
                && Objects.equals(type, that.type) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash, timestamp, type, description);
    }
}
