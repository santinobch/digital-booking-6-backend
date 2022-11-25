#!/bin/bash

apt update

apt install openjdk-8-jre openjdk-8-jdk
cd /usr/lib/jvm/
ls -la
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/
apt install openjdk-17-jdk
apt install openjdk-17-jre

sudo useradd -s /bin/bash -d /home/grupo6/ -m -G sudo grupo6
su - grupo6
cd ~
git clone https://gitlab.ctd.academy/ctd/proyecto-integrador-1022/0521-pt-c8/grupo-06/-/tree/main/digital-booking-6-backend
cd digital-booking-6-backend
mvn clean install
java -Dserver.port=80 -jar DigitalBooking-BE-G6.jar