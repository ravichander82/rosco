/*
 * Copyright 2021 Salesforce, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.rosco.manifests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.netflix.spinnaker.kork.retrofit.exceptions.SpinnakerHttpException;
import java.util.List;
import retrofit.client.Response;
import retrofit.mime.TypedString;

public class ManifestTestUtils {

  public static SpinnakerHttpException makeSpinnakerHttpException(int status) {
    SpinnakerHttpException spinnakerHttpException = mock(SpinnakerHttpException.class);
    when(spinnakerHttpException.getMessage()).thenReturn("message");

    // Response is a final class, so straightforward mocking fails.
    String url = "https://some-url";
    Response response =
        new Response(
            url,
            status,
            "arbitrary reason",
            List.of(),
            new TypedString("{ message: \"unused message due to above mock\" }"));

    when(spinnakerHttpException.getResponse()).thenReturn(response);
    return spinnakerHttpException;
  }
}
