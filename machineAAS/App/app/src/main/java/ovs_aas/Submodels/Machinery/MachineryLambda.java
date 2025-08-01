package ovs_aas.Submodels.Machinery;

import java.math.BigInteger;

// Copyright 2024 riccardo.bacca@studio.unibo.it
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

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.basyx.submodel.metamodel.map.submodelelement.SubmodelElement;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.eclipse.paho.client.mqttv3.MqttException;

import ovs_aas.Mqtt.AASMqttClient;

public class MachineryLambda {
    private PingTest pingHost;
    private AASMqttClient client;

    public MachineryLambda(AASMqttClient aasMqttClient) {
        this.pingHost = new PingTest();
        this.client = aasMqttClient;
    }

    /**
     * @param machineHosts used to make Ping requests from
     * @return Ping test results from Speicifc IP (on this machine) to given IP from WebUI
     */
    public Function<Map<String, SubmodelElement>, SubmodelElement[]> pingMachinery(List<String> machineHosts) {
        return (args) -> {
            String pingFrom = ((String) args.get("From").getValue());
            String pingDst = ((String) args.get("To").getValue());

            if (machineHosts.contains(pingFrom)) {
                return new SubmodelElement[] {
                    new Property("Result: " + this.pingHost.pingTest(pingDst, pingFrom))
                };
            } else {
                return new SubmodelElement[] {
                    new Property("Selected 'from' host, must be in this machine !")
                };
            }
        };
    }

    public Function<Map<String, SubmodelElement>, SubmodelElement[]> test() {
        return (args) -> {
            // Ottenimento dei valori dalle variabili di input specificate
            String Prova1 = ((String) args.get("Prova1").getValue());

            // Limitazione di BaSyx, i valori interi vanno castati a BigInteger, sempre
            BigInteger Prova2 = ((BigInteger) args.get("Prova2").getValue());

            // Restituzione del risultato
            return new SubmodelElement[] {
                new Property("Hai inserito i valori: " + Prova1 + ", " + Prova2)
            };
        };
    }

    public Function<Map<String, SubmodelElement>, SubmodelElement[]> mqtt() {
        return (args) -> {
            String topic = ((String) args.get("Topic").getValue());

            try {
                return new SubmodelElement[] {
                    new Property("Result: " + this.client.readVelocity(topic))
                };
            } catch (InterruptedException | MqttException e) {
                e.printStackTrace();
            }
            return null;
        };
    }
}