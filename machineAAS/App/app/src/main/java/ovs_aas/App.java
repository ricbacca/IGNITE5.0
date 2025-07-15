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

import org.eclipse.basyx.aas.metamodel.api.parts.asset.AssetKind;
import java.util.List;

import ovs_aas.AssetShells.IShell;
import ovs_aas.AssetShells.Machine;
import ovs_aas.Network.Controller;

/**
 * All'avvio del container definire 3 variabili d'ambiente:
 * - AAS_IP
 * - ASSET_IP
 * - ID
 * - REGISTRY_IP
 * 
 * Comando:
 * 1. Dopo aver modificato questo progetto, eseguire: gradle build
 *  1a. Eseguire il comando all'interno del folder "App"
 * 2. Creare il container: docker build -t machineAAS .
 *  2a. Il dockerfile e il MachineAAS.jar devono essere nella stessa directory - come di default
 * 3. Avvio del container: 
 *  docker run --rm -e ASSET_IP=192.168.1.10:8080 -e AAS_IP=10.0.0.5:443 -e ID=Machine1 machineAAS
 *  3a: Indirizzi IP a piacere. Fondamentale rispettare la sintassi IP:PORTA
 * 
 * Nota bene:
 * 1. modificare solamente le parti all'interno di: Submodels/Machinery/*
 */
public class App {
    private final static Controller client = new Controller();
    public static void main(String[] args) {
        parseAndStore();

        waitForRegistry();

        IShell machine = new Machine(
            StaticProperties.getAAS_PORT(),
            StaticProperties.get_ID(),
            "org.unibo.aas." + StaticProperties.get_ID().toLowerCase(),
            AssetKind.INSTANCE,
            List.of(StaticProperties.getASSET_IP()));

        machine.createAndStartServlet();
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

    private static void parseAndStore() {
        String id = System.getenv("ID");
        String aas_ip = System.getenv("AAS_IP");
        String asset_ip = System.getenv("ASSET_IP");
        String registry_ip = System.getenv("REGISTRY_IP");

        if (aas_ip == null || asset_ip == null || id == null || registry_ip == null) {
            System.err.println("Errore: definire le variabili d'ambiente AAS_IP - ASSET_IP - ID - REGISTRY_IP");
            throw new IllegalArgumentException("Le variabili non devono essere null!!");
        }

        // Verifica validità formato
        if ((aas_ip.split(":").length != 2) || (asset_ip.split(":").length != 2)|| (registry_ip.split(":").length != 2)) {
            throw new IllegalArgumentException("Formato errato. Deve essere IP:PORT");
        }

        // Verifica validità porta
        try {
            if (!isBetween(Integer.parseInt(aas_ip.split(":")[1])) || !isBetween(Integer.parseInt(asset_ip.split(":")[1])) || !isBetween(Integer.parseInt(registry_ip.split(":")[1]))) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Porta non valida: deve essere un numero tra 1 e 65535");
        }

        // Salva risultati
        StaticProperties.setAAS_PORT(aas_ip.split(":")[1]);
        StaticProperties.setAAS_IP(aas_ip);
        StaticProperties.setASSET_IP(asset_ip);
        StaticProperties.setID(id);
        StaticProperties.setREGISTRY_IP(registry_ip);

        System.out.println("Saved AAS PORT: " + StaticProperties.getAAS_PORT());
    }

    private static boolean isBetween(int port) {
        return port > 1 || port < 65535;
    }

}