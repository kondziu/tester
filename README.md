# Tester v2.5.1

A vocab testing program and teacher aid for computer assisted language learning
classes. Based on a DOS program by Marek Siek, originally written in 2005 in
Delphi, and re-written in Java 1.4 around 2007. Undergoing modernization.

## Install Tester

Install prerequisites:

```bash
sudo apt install openjdk-11-jdk
```

Download and extract [latest
release](https://github.com/kondziu/tester/releases/download/v2.5.1/Tester.zip),
e.g.:

```bash
wget https://github.com/kondziu/tester/releases/download/v2.5.1/Tester.zip
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
sudo apt ant
```

Run build script:

```bash
ant clean jar
```

Start compiled program (from Jar):

```bash
ant run
```

Create distributable archive file:

```bash
ant dist
```

See `build.xml` for a list of targets.

## Requirements (summary)

| requirement                      | version | needed for      |
| :--                              | :--     | :--             |
| `java`                           | 11      | running the app |
| [`ant`](https://ant.apache.org/) | 1.10    | development     |
