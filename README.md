  # IGNITE5.0
Repository for IGNITE5.0 Project

All'interno di ciascuna cartella disponibile il relativo readme per l'avvio del componente.
Per arricchire MachineAAS seguire le indicazioni commentate all'interno del progetto "App"

SEGUIRE STRETTAMENTE L'ORDINE DI AVVIO.

CONFIGURAZIONE HARDWARE DI SEGUITO:

ROUTER -> 192.168.10.1
   - usr: admin
   - pwd: adminRomagnaTech2025
   - pwd_wifi: romagnatech2025

PC -> REGISTRY E WEB UI -> 192.168.10.101
   - WEB UI: 3000 FRONT E BACKEND
   - REGISTRY: 8082 - 4000 BACKEND

RASP1 -> IT BOUNDARY -> 192.168.10.4
   - AVVIO OVS1
   - Controller CNT1 ip: 192.168.10.2:6633
   - Check di quali ETH abbiamo a disposizione: EHT0, ETH1 messe di default nello script

RASP2 -> OT BONDARY -> 192.168.10.3
   - AVVIO OVS2
   - Controller CNT2 ip: 192.168.10.2:6653
   - Check di quali ETH abbiamo a disposizione: EHT0, ETH1 messe di default nello script

RASP3 -> AAS RETE -> 192.168.10.2
   - CONTROL PLANE AAS: 192.168.10.2:6002
   - INFRASTRUCTURE AAS: 192.168.10.2:6003
   - CNT1: 192.168.10.2:6633 (1000 per ssh)
   - CNT2: 192.168.10.2:6653 (2000 per ssh)
   - REGISTRI_IP: 192.168.10.101:8082