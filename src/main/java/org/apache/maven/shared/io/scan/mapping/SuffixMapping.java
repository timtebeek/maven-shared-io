/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.maven.shared.io.scan.mapping;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jdcasey
 * @version $Id$
 */
public final class SuffixMapping implements SourceMapping {
    private final String sourceSuffix;

    private final Set<String> targetSuffixes;

    /**
     * @param sourceSuffix source suffix.
     * @param targetSuffix target suffix.
     */
    public SuffixMapping(String sourceSuffix, String targetSuffix) {
        this.sourceSuffix = sourceSuffix;

        this.targetSuffixes = Collections.singleton(targetSuffix);
    }

    /**
     * @param sourceSuffix source suffix.
     * @param targetSuffixes target suffixes.
     */
    public SuffixMapping(String sourceSuffix, Set<String> targetSuffixes) {
        this.sourceSuffix = sourceSuffix;

        this.targetSuffixes = targetSuffixes;
    }

    /** {@inheritDoc} */
    public Set<File> getTargetFiles(File targetDir, String source) {
        Set<File> targetFiles = new HashSet<File>();

        if (source.endsWith(sourceSuffix)) {
            String base = source.substring(0, source.length() - sourceSuffix.length());

            for (String suffix : targetSuffixes) {
                targetFiles.add(new File(targetDir, base + suffix));
            }
        }

        return targetFiles;
    }
}
