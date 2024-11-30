/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2024 Red Hat, Inc., and individual contributors
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
 * Certificate information used to validate the installation components
 */
public class TrustCertificate {
    private final String keyID;
    private final String fingerprint;
    private final String description;
    private final String status;

    public TrustCertificate(String keyID, String fingerprint, String description, String status) {
        this.keyID = keyID;
        this.fingerprint = fingerprint;
        this.description = description;
        this.status = status;
    }

    /**
     * hex form of the certificate key ID
     * @return
     */
    public String getKeyID() {
        return keyID;
    }

    /**
     * Hash of the certificate, used to verify integrity of the certificate.
     *
     * @return
     */
    public String getFingerprint() {
        return fingerprint;
    }

    /**
     * Description of the certificate identity.
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * trust status of the certificate in the installation
     * @return
     */
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "TrustCertificate{" +
                "keyID='" + keyID + '\'' +
                ", fingerprint='" + fingerprint + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrustCertificate that = (TrustCertificate) o;
        return Objects.equals(keyID, that.keyID) && Objects.equals(fingerprint, that.fingerprint) && Objects.equals(description, that.description) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyID, fingerprint, description, status);
    }
}
