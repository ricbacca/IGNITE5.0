MOSQUITTO BROKER

$ docker run -it -p 1883:1883 -v "$PWD/mosquitto/config:/mosquitto/config"  eclipse-mosquitto
