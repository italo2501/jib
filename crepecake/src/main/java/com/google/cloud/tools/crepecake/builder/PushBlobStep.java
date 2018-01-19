/*
 * Copyright 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.cloud.tools.crepecake.builder;

import com.google.cloud.tools.crepecake.blob.Blob;
import com.google.cloud.tools.crepecake.image.DescriptorDigest;
import com.google.cloud.tools.crepecake.registry.RegistryClient;
import com.google.cloud.tools.crepecake.registry.RegistryException;
import java.io.IOException;

// TODO: Comment and test.
class PushBlobStep implements Step<DescriptorDigest, Void> {

  private final RegistryClient registryClient;
  private final Blob blob;

  PushBlobStep(RegistryClient registryClient, Blob blob) {
    this.registryClient = registryClient;
    this.blob = blob;
  }

  @Override
  public Void run(DescriptorDigest digest) throws IOException, RegistryException {
    if (registryClient.checkBlob(digest) != null) {
      return null;
    }

    registryClient.pushBlob(digest, blob);

    return null;
  }
}
