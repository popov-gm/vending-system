# Vending System

## Overview

This repository consists of two Spring Boot projects: **vending-machine** and **vending-controller**. They can be 
imported separately in an IDE (Intellij Idea) and run with gradle bootRun command. A jar file can be generated with the 
bootJar command.

## Prerequisites

* Java 17
* Gradle 8.3
* Docker 4.21.1
* RabbitMQ 3.11
* Git

## Docker Compose

There is also a docker-compose.yml file which can be run with the **docker compose up** command. Both projects should 
already contain built jar files in their respective **/build/libs** directories.
Docker compose starts the following containers:
- a RabbitMQ container
- two instances of **vending-machine** service which represent 2 distinct vending machines
- **vending-controller** service which sends commands to the vending machines via RabbitMq.

The vending machines listen on different queues for commands and the vending controller identifies them by the vending
machine ids which serve as routing key.