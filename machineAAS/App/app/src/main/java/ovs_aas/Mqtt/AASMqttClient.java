package ovs_aas.Mqtt;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AASMqttClient {
    String clientId;
    IMqttClient client;
    MqttConnectOptions options;
    ObjectMapper mapper;

    public AASMqttClient(String mqtt_ip, int mqtt_port) {
        this.clientId = UUID.randomUUID().toString();
        try {
            this.client = new MqttClient("tcp://" + mqtt_ip + ":" + mqtt_port, clientId);
        } catch(MqttException e) {}
        
        this.options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);

        try {
            this.client.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }

        this.mapper = new ObjectMapper();
    }

    public int readVelocity(String velocity_topic) throws InterruptedException, MqttSecurityException, MqttException {
        AtomicInteger velocita = new AtomicInteger(-1);
        CountDownLatch receivedSignal = new CountDownLatch(1);

        client.subscribe(velocity_topic, (topic, msg) -> {
            velocita.set((int) parsePayload(msg.getPayload()));
            receivedSignal.countDown();
        });
        receivedSignal.await(1, TimeUnit.MINUTES);

        return velocita.get();
    }

    private Object parsePayload(byte[] payload) {
        String jsonString = new String(payload);
        Object value = -1;

        try {
            JsonNode root = mapper.readTree(jsonString);
            value = root.get("VELOCITA").asInt();
        } catch (Exception e) {
            System.err.println("Errore nel parsing JSON: " + e.getMessage());
        }

        return value;
    }
}
