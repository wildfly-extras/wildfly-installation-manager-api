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
