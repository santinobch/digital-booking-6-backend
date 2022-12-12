#!/bin/bash
yum update -y | tee /home/ec2-user/logs.txt

#instalar git en EC2
yum install -y git  | tee /home/ec2-user/logs.txt

#instalar apache HTTP server
yum install -y httpd | tee /home/ec2-user/logs.txt

#instalar java
yum install -y openjdk-18-jdk | tee /home/ec2-user/logs.txt

#instalar apache maven
yum install -y maven | tee /home/ec2-user/logs.txt

#instalar jdk for springboot
yum install -y java-1.8.0-openjdk-devel | tee /home/ec2-user/logs.txt
yum install -y java-1.8.0-openjdk | tee /home/ec2-user/logs.txt

# java -Dserver.port=80 -jar DigitalBooking-BE-G6.jar