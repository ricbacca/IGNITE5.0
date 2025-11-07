# IGNITE5.0
Repository for IGNITE5.0 Project

All'interno di ciascuna cartella disponibile il relativo readme per l'avvio del componente.
Per arricchire MachineAAS seguire le indicazioni commentate all'interno del progetto "App"


MODIFICATO

L'ordine di avvio deve essere il seguente:
1. Infrastruttura BaSyx: Registry e WEB-UI (questa opzionale)
2. Avvio dei Controller (vedi: controlPlaneAAS/configurazione_controller)
3. Avvio degli Switch OpenFlow (vedi: infrastructureAAS/configurazione_switch)
4. Avvio di eventuali dispositivi esterni necessari per MachineAAS o simili
   1. Se possibile inserire sempre delle routine per verificare la connettività all'avvio, senza far crashare tutto
5. Avvio di tutti gli AAS
