NON ESEGUIBILE SU PC/RASPBERRY CON ARCHITETTURA ARM

STARTUP:

docker run --rm --name=web-ui \
-e basyxcontext_accesscontrolalloworigin='*' \
-p 3000:3000 \
eclipsebasyx/aas-gui:v230703

NOTA:
- raggiungibile su localhost:3000
- una volta avviato, da webUI occorre specificare l'IP del registry: http://<<IP>>:8082/registry