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

import java.io.IOException;
import java.net.InetAddress;

import org.eclipse.basyx.aas.metamodel.api.parts.asset.AssetKind;

import ovs_aas.AssetShells.IShell;
import ovs_aas.AssetShells.NetworkInfrastructure;
import ovs_aas.Network.Controller;

/**
 * All'avvio del container definire 3 variabili d'ambiente:
 * - AAS_IP (normalmente localhost:porta)
 * - CNT1_IP (NB: SOLO IP - NUMERI PORTA PREDEFINITI 6633 e 8080)
 * - CNT2-IP (NB: SOLO IP - NUMERI PORTA PREDEFINITI 6653 e 9090)
 * - REGISTRY_IP
 * - CNT1_SSH
 * - CNT2_SSH
 * - PORTA (-p) utilizzata da AAS_IP
 * 
 * - ID fisso....non come argomento
 * 
 * Comando:
 * 1. Dopo aver modificato questo progetto, eseguire: gradle build
 *  1a. Eseguire il comando all'interno del folder "App"
 * 2. Creare il container: docker build -t controlplaneAAS .
 *  2a. Il dockerfile e il controlplaneAAS.jar devono essere nella stessa directory - come di default
 * 3. Avvio del container: 
 *  docker run --rm \
 *      -e AAS_IP=localhost:6002 \
 *      -e CNT1_IP=192.168.1.10 \
 *      -e CNT2_IP=192.168.1.11 \
 *      -e CNT1_SSH=1000 \
 *      -e CNT2_SSH=2000 \
 *      -e REGISTRY_IP=172.17.0.2:4000 \
 *      -p 6002:6002 controlplaneAAS
 *  3a: Indirizzi IP a piacere. Fondamentale rispettare la sintassi IP:PORTA (TRANNE PER I CNT CHE HANNO PORTE PREDEFINITE)
 *  3b: Le porte SSH possono anche essere omesse se i controller sono su due macchine diverse. In questo caso verrà usata la porta 22 di default
 * 
 * Nota bene:
 * 1. modificare solamente le parti all'interno di: Submodels/Machinery/*
 */
public class App {
    private final static Controller client = new Controller();
    public static void main(String[] args) {
        parseAndStore();

        waitForRegistry();
        waitForControllers();

        IShell networkInfrastructure = new NetworkInfrastructure(
            StaticProperties.getAAS_PORT(), 
            "Network Infrastructure", 
            "org.unibo.aas.networkInfrastructure", 
            AssetKind.INSTANCE);

        networkInfrastructure.createAndStartServlet();
    }

    /**
     * Polling on Registry Url, waiting for a positive response to proceed on.
     */
    private static void waitForRegistry() {
        System.out.print("Waiting for Registry at " + StaticProperties.get_REGISTRY_PATH());
        while(!client.isServerAvailable(StaticProperties.get_REGISTRY_PATH() +  StaticProperties.REGISTRY_POLLING)) {
            System.out.print(".");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }

        /**
     * Polling on Registry Url, waiting for a positive response to proceed on.
     */
    private static void waitForControllers() {
        System.out.print("Waiting for Controllers at: " + StaticProperties.getCNT1_IP() + ", " + StaticProperties.getCNT2_IP());
        InetAddress cnt1Address;
        InetAddress cnt2Address;
        try {
            cnt1Address = InetAddress.getByName(StaticProperties.getCNT1_IP());
            cnt2Address = InetAddress.getByName(StaticProperties.getCNT2_IP());

            System.out.print("\n");
            while(!cnt1Address.isReachable(20000) || !cnt2Address.isReachable(20000))
                System.out.println("Controllers not reachable!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseAndStore() {
        String aas_ip = System.getenv("AAS_INF_IP");
        String cnt1_ip = System.getenv("CNT1_INF_IP");
        String cnt2_ip = System.getenv("CNT2_INF_IP");
        String registry_ip = System.getenv("REGISTRY_INF_IP");
        String cnt1_ssh = System.getenv("CNT1_SSH") == null ? "22" : System.getenv("CNT1_SSH");
        String cnt2_ssh = System.getenv("CNT2_SSH") == null ? "22" : System.getenv("CNT2_SSH");

        if (aas_ip == null || cnt1_ip == null || cnt2_ip == null || registry_ip == null || cnt1_ssh == null || cnt2_ssh == null) {
            System.err.println("Errore: definire le variabili d'ambiente AAS_IP - ASSET_IP - ID - REGISTRY_IP");
            throw new IllegalArgumentException("Le variabili non devono essere null!!");
        }

        // Verifica validità formato
        if ((aas_ip.split(":").length != 2) || (registry_ip.split(":").length != 2)) {
            throw new IllegalArgumentException("Formato errato. Deve essere IP:PORT");
        }

        // Verifica validità porta
        try {
            if (!isBetween(Integer.parseInt(aas_ip.split(":")[1])) || !isBetween(Integer.parseInt(registry_ip.split(":")[1]))) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Porta non valida: deve essere un numero tra 1 e 65535");
        }

        // Salva risultati
        StaticProperties.setAAS_PORT(aas_ip.split(":")[1]);
        StaticProperties.setAAS_IP(aas_ip);
        StaticProperties.setCNT1_IP(cnt1_ip);
        StaticProperties.setCNT2_IP(cnt2_ip);
        StaticProperties.setCNT1_SSH(cnt1_ssh);
        StaticProperties.setCNT2_SSH(cnt2_ssh);
        StaticProperties.setREGISTRY_IP(registry_ip);

        System.out.println("Saved AAS PORT: " + StaticProperties.getAAS_PORT());
        System.out.println("Saved CNT1_SSH PORT: " + StaticProperties.getCNT1_SSH());
        System.out.println("Saved CNT2_SSH PORT: " + StaticProperties.getCNT2_SSH());
    }

    private static boolean isBetween(int port) {
        return port > 1 || port < 65535;
    }

}