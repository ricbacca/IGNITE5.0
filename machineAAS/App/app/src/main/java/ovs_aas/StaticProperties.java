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

    private static final String MQTT_BROKER = "MQTT_BROKER";
    private static final int MQTT_PORT = 1883;
    private static final String MQTT_TOPIC = "v1/#";

    private static final String AAS_IP = "AAS_IP";
    private static final String AAS_PORT = "0";
    private static final String ID = "ID";
    private static final String ASSET_IP = "ASSET_IP";

    public static void setAAS_IP(String newValue) {
        System.setProperty(AAS_IP,  newValue);
    }

    public static String getMqttBroker() {
        return System.getProperty(MQTT_BROKER, "null");
    }

    public static int getMqttPort() {
        return MQTT_PORT;
    }

    public static String getMqttTopic() {
        return MQTT_TOPIC;
    }

    public static void setAAS_PORT(String newValue) {
        System.setProperty(AAS_PORT,  newValue);
    }

    public static void setASSET_IP(String newValue) {
        System.setProperty(ASSET_IP,  newValue);
    }

    public static void setID(String newValue) {
        System.setProperty(ID,  newValue);
    }

    public static void setMQTT_IP(String newValue) {
        System.setProperty(MQTT_BROKER,  newValue);
    }

    public static void setREGISTRY_IP(String newValue) {
        System.setProperty(REGISTRY_IP,  newValue);
    }

    public static String getASSET_IP() {
        return System.getProperty(ASSET_IP, "null");
    }

    public static String getAAS_IP() {
        return System.getProperty(AAS_IP, "null");
    }

    public static String get_ID() {
        return System.getProperty(ID, "null");
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