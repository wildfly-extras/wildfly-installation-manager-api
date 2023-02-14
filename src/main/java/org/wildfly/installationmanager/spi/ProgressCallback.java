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

package org.wildfly.installationmanager.spi;

/**
 * Callback interface allowing clients to receive progress updates about the installation.
 */
public interface ProgressCallback {

    enum Event {
        /**
         * new stage has been started
         */
        STARTING,
        /**
         * progress update on current stage
         */
        PULSE,
        /**
         * current stage has been finished
         */
        COMPLETED;
    }

    class Status {
        private final long completed;
        private final long total;
        private final String stage;
        private final Event event;

        public Status(String stage, Event event, long completed, long total) {
            this.completed = completed;
            this.total = total;
            this.stage = stage;
            this.event = event;
        }

        public Status(String stage, Event event) {
            this.completed = 0;
            this.total = -1;
            this.stage = stage;
            this.event = event;
        }

        boolean isMeasurable() {
            return total >0;
        }

        public Event getEvent() {
            return event;
        }

        public long getCompleted() {
            return completed;
        }

        public long getTotal() {
            return total;
        }

        public String getStage() {
            return stage;
        }

        @Override
        public String toString() {
            return "Status{" +
                    "completed=" + completed +
                    ", total=" + total +
                    ", stage='" + stage + '\'' +
                    ", event='" + event + '\'' +
                    '}';
        }
    }

    /**
     * receive updates on installation progress.
     *
     * @param status - {@link Status} representing current phase of installation
     */
    void update(Status status);

    /**
     * print a message.
     *
     * @param message
     */
    void println(String message);
}
