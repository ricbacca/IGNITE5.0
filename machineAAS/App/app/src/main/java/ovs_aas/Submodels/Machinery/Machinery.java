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

package ovs_aas.Submodels.Machinery;

import java.util.List;

import org.eclipse.basyx.submodel.metamodel.map.Submodel;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.LangStrings;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.valuetype.ValueType;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.operation.Operation;

import ovs_aas.Submodels.AbstractSubmodel;

import java.util.Map;

/**
 * Definizione dei submodels da poter utilizzare nell'AAS in questione
 */
public class Machinery extends AbstractSubmodel {
    private List<String> hosts;
    private MachineryLambda lambdaProvider;

    public Machinery(List<String> hosts) {
        super();
        this.hosts = hosts;
        this.lambdaProvider = new MachineryLambda();
    }

    @Override
    public List<Submodel> createSubmodel() {
        Submodel operations = new Submodel();
        operations.setIdShort("Operations");
        operations.addSubmodelElement(this.pingMachinery());

        // RomagnaTech - TEST
        Submodel romagnaTech = new Submodel();
        romagnaTech.setIdShort("RomagnaTech");
        romagnaTech.addSubmodelElement(this.test());

        return List.of(operations, romagnaTech);
    }

    private LangStrings getDescription() {
        return new LangStrings("English", "This machine is composed by host: " + hosts);
    }

    private Operation pingMachinery() {
        Operation pingMachinery = new Operation("PingMachinery");
        pingMachinery.setDescription(this.getDescription());
        pingMachinery.setInputVariables(getUtils().getCustomInputVariables(Map.of(
            "From", ValueType.String,
            "To", ValueType.String
        )));

        pingMachinery.setOutputVariables(getUtils().getOperationVariables(1, "Output"));
        pingMachinery.setWrappedInvokable(lambdaProvider.pingMachinery(hosts));

        return pingMachinery;
    }

    private Operation test() {
        //Definizione della funzione da rendere disponibile
        Operation test = new Operation("Test");

        //Eventuale - Opzionale Descrizione della funzione da inserire
        test.setDescription(new LangStrings("English", "Test"));

        // Aggiunta di variabili per ottenere dati in input
        // Inserire una riga per ogni variabile che si vuole, nella forma: <NomeVariabile, ValueType>
        test.setInputVariables(getUtils().getCustomInputVariables(Map.of(
            "Prova1", ValueType.String,
            "Prova2", ValueType.Integer
        )));

        // Aggiunta di variabili di output, in cui mostrare il risultato dell'operazione svolta
        // Ogni variabile di output è indifferente nel formato, specificare "N" input variables necessarie
        test.setOutputVariables(getUtils().getOperationVariables(1, "Output"));
        
        // Specifica la funzione Lambda da richiamare alla pressione del tasto sulla WebUI
        test.setWrappedInvokable(lambdaProvider.test());

        return test;
    }
}