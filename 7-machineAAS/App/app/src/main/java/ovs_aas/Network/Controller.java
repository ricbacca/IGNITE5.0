// Copyright 2023 riccardo.bacca@studio.unibo.it
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package ovs_aas.Network;

import java.io.IOException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

public class Controller extends AbstractController {

    /**
     * @param URL for Server on which to Poll for a positive response
     * @return true if Server on provided URL reply with StatusCode 200
     * @return false otherwise
     */
    public Boolean isServerAvailable(String URL) {
        HttpGet getRequest = new HttpGet(URL);
        int statusCode = 0;

        try {
            CloseableHttpResponse response = apacheClient.execute(getRequest);
            statusCode = response.getStatusLine().getStatusCode();
            if (response != null)
                response.close();
        } catch (IOException e) {} 

        return statusCode == HTTP_OK;
    }
}