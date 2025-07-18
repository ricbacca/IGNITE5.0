ESEGUBIILE ANCHE SU ARCHITETTURA ARM RASPBERRY/REV_PI

STARTUP:

1. Docker build -t controller .
2. Avvio CNT1:
   docker run --rm --name=cnt1 -p:8080:8080 -p 6633:6633 -p 1000:22 controller


Stesso comando per CNT2, ma le porte sono: 9090 e 6653.
Se i due controller vengono eseguiti sulla stessa macchina: mappare la porta 22 su porte differenti
   - ESEMPIO: "-p 1000:22" e "-p 2000:22"

SPIEGAZIONE

Porta 8080 (9090 su CNT2): per l'accesso all'API di RYU
Porta 6633 (6653 su CNT2): per la connessione con lo switch openflow
Porta 22: per la connessione via SSH al NETWORK INFRASTRUCTURE AAS (che si occupa di fare il SOLO boot dei controller, eseguendo i comandi di cui sotto)

NOTA BENE:
Ulteriore possibilità di eseguire RYU su un PC, senza il container docker (SCONSIGLIATO).
In questo caso installare RYU, ed i comandi (per l'avvio dei rispettivi programmi - altrimenti gestiti dall'AAS) sono i seguenti:

- simple switch: "ryu-manager --ofp-tcp-listen-port 6633/6653 ryu.app.simple_switch_13 ryu.app.ofctl_rest --wsapi-port 8080/9090"
- firewall: "ryu-manager --ofp-tcp-listen-port 6633/6653 ryu.app.rest_firewall ryu.app.ofctl_rest --wsapi-port 8080/9090"