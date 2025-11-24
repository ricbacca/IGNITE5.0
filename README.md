  # IGNITE5.0
Repository for IGNITE5.0 Project

All'interno di ciascuna cartella disponibile il relativo readme per l'avvio del componente.
Per arricchire MachineAAS seguire le indicazioni commentate all'interno del progetto "App"


MODIFICATO

L'ordine di avvio deve essere il seguente:
1. Infrastruttura BaSyx: Registry e WEB-UI
2. Avvio dei Controller (vedi: controlPlaneAAS/configurazione_controller)
3. Avvio degli Switch OpenFlow (vedi: infrastructureAAS/configurazione_switch)
4. Avvio di eventuali dispositivi esterni necessari per MachineAAS o simili
   1. Se possibile inserire sempre delle routine per verificare la connettività all'avvio, senza far crashare tutto
5. Avvio di tutti gli AAS



REGISTRY E WEB UI -> 192.168.10.101
   - WEB UI: 3000 FRONT E BACKEND
   - REGISTRY: 8082 - 4000 BACKEND

IT BOUNDARY -> 192.168.10.4
   - CNT1 -> Controller 1 per Switch 1
     - Porta 8080
     - Api: 6633

OT BONDARY -> 192.168.10.3
   - CNT2 -> Controller 2 per Switch 2
     - Porta 9090
     - Api: 6653

AAS RETE -> 192.168.10.2
   - AAS_IP: 192.168.10.2
   - CNT1: 192.168.10.4
   - CNT2: 192.168.10.3
   - REGISTRI_IP: 192.168.10.101