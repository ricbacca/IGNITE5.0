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

package ovs_aas;

/**
 * To manage System properties, used to mantain info throughout the app
 * about which controller is on, and not cause any problems.
 */
public class StaticProperties {
    public static final String REGISTRY_POLLING = "api/v1/registry";
    private static final String REGISTRY_IP = "REGISTRY_IP";

    public static final int CNT1_PORT = 6633;
    public static final int CNT2_PORT = 6653;
    public static final int CNT1_API_REST_PORT = 8080;
    public static final int CNT2_API_REST_PORT = 9090;

    public static final String CNT1_API_REST_IP = "http://" + StaticProperties.getCNT1_IP() + ":" + CNT1_API_REST_PORT;
    public static final String CNT2_API_REST_IP = "http://" + StaticProperties.getCNT2_IP() + ":" + CNT2_API_REST_PORT;

    private static final String AAS_IP = "AAS_IP";
    private static final String AAS_PORT = "0";
    private static final String CNT1_IP = "CNT1_IP";
    private static final String CNT2_IP = "CNT2_IP";

    public static void setAAS_IP(String newValue) {
        System.setProperty(AAS_IP,  newValue);
    }

    public static void setAAS_PORT(String newValue) {
        System.setProperty(AAS_PORT,  newValue);
    }

    public static void setCNT1_IP(String newValue) {
        System.setProperty(CNT1_IP,  newValue);
    }

    public static void setCNT2_IP(String newValue) {
        System.setProperty(CNT2_IP,  newValue);
    }

    public static void setREGISTRY_IP(String newValue) {
        System.setProperty(REGISTRY_IP,  newValue);
    }

    public static String getCNT1_IP() {
        return System.getProperty(CNT1_IP, "null");
    }

    public static String getCNT2_IP() {
        return System.getProperty(CNT2_IP, "null");
    }

    public static String getAAS_IP() {
        return System.getProperty(AAS_IP, "null");
    }

    public static String get_REGISTRY_IP() {
        return System.getProperty(REGISTRY_IP, "null");
    }

    public static int getAAS_PORT() {
        return Integer.parseInt(System.getProperty(AAS_PORT,  "null"));
    }

    public static String get_REGISTRY_PATH() {
        return "http://" + StaticProperties.get_REGISTRY_IP() + "/registry/";
    }
}