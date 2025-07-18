ESEGUBIILE ANCHE SU ARCHITETTURA ARM RASPBERRY/REV_PI

STARTUP:

1. Docker build -t infrastructure_aas .
2. docker run --rm \
   -e AAS_IP=localhost:6003 \
   -e CNT1_IP=172.17.0.4 \
   -e CNT2_IP=172.17.0.3 \
   -e CNT1_SSH=1000 \
   -e CNT2_SSH=2000 \
   -e REGISTRY_IP=172.17.0.2:4000 \
   -p 6003:6003 infrastructure_aas

NOTA:
- Avviare InfrastructureAAS solo DOPO aver avviato il Registry ed i controller Ryu.
- aprire le porte, che l'AAS utilizza (le stesse di AAS_IP)
- REGISTRY IP su porta 4000 di backend sempre !!
- i due switch openflow a cui connettersi sono semplicemente due PC/RASPBERRY. Vedi "configurazione_switch"