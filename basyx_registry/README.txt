ESEGUIBILE SU RASPBERRY/SIMILI CON ARCHITETTURA ARM

STARTUP:

docker run --rm --name=registry \
-e basyxcontext_accesscontrolalloworigin='*' \
-p 8082:4000 \
eclipsebasyx/aas-registry:1.5.1

NOTA:
- raggiungibile su http://localhost:8082/registry/api/v1/registry