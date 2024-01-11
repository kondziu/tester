# Tester v2.5.2

A vocab testing program and teacher aid for computer assisted language learning
classes. Based on a DOS program by Marek Siek, originally written in 2005 in
Delphi, and re-written in Java 1.4 in November 2006. Currently it is undergoing
modernization.

## Install Tester

Install prerequisites:

```bash
sudo apt install openjdk-11-jdk
```

Download and extract [latest
release](https://github.com/kondziu/tester/releases/download/v2.5.2/tester-2.5.2-dist.zip),
e.g.:

```bash
wget https://github.com/kondziu/tester/releases/download/v2.5.2/tester-2.5.2-dist.zip
unzip Tester.zip
cd Tester
```

(For older releases see: https://github.com/kondziu/tester/releases)

## Run Tester

Start with run script:

```bash
./tester.sh
```

## Build

Install prerequisites:

```bash
sudo apt install maven
```

Create jar and distributable archive file:

```bash
mvn clean package
```

Compile and run program (from classes):

```bash
mvn exec:java
```

Start compiled program (from Jar):

```bash
java -jar build/tester-2.5.2.jar
```

Create distributable archive file:

```bash
mvn clean package assembly:single
```

See `pom.xml` for a list of targets.

## Requirements (summary)

| requirement                        | version | needed for               |
| :--                                | :--     | :--                      |
| Java                               | 11      | running the app          |
| [Maven](https://maven.apache.org/) | 1.10    | development and building |