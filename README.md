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
release](https://github.com/kondziu/tester/releases/download/v2.5.2/tester-2.5.2.zip),
e.g.:

```bash
wget https://github.com/kondziu/tester/releases/download/v2.5.2/tester-2.5.2.zip
unzip Tester.zip
cd Tester
```

(For older releases see: https://github.com/kondziu/tester/releases)

## Run Tester

Start with run script:

```bash
./tester-2.5.2.sh
```

## Build

Compile and run program (in working directory `app/build/working-dir/`):

```bash
./gradlew run
```

Create jar (`app/build/jar/tester-2.5.2.jar`):

```bash
./gradlew jar 
```


Start compiled program (from Jar):

```bash
java -jar app/build/libs/tester-2.5.2.jar
```

Create distribution package (`app/build/dist/tester-2.5.2.zip`):

```bash
./gradlew dist
```

For a list of targets:

```bash
./gradlew tasks
```

## Requirements (summary)

| requirement                         | version | needed for                          |
| :--                                 | :--     | :--                                 |
| Java                                | 11      | running the app                     |
| [Gradle](https://maven.apache.org/) | 8.0     | development and building (included) |

## Troubleshooting

### Invalid Java installation

When building, the build command reports invalid toolchains:

```bash
./gradlew build
```

```
Invalid Java installation found at '/usr/lib/jvm/openjdk-17' (Common Linux Locations). It will be re-checked in the next build. This might have performance impact if it keeps failing. Run the 'javaToolchains' task for more details.
Invalid Java installation found at '/usr/lib/jvm/openjdk-11' (Common Linux Locations). It will be re-checked in the next build. This might have performance impact if it keeps failing. Run the 'javaToolchains' task for more details.
Invalid Java installation found at '/usr/lib/jvm/openjdk-18' (Common Linux Locations). It will be re-checked in the next build. This might have performance impact if it keeps failing. Run the 'javaToolchains' task for more details.
Invalid Java installation found at '/usr/lib/jvm/openjdk-19' (Common Linux Locations). It will be re-checked in the next build. This might have performance impact if it keeps failing. Run the 'javaToolchains' task for more details.
```

Gradle auto JVM finder is finding JVM sources and misinterpreting them as
complete JVM installations. Set a specific JVM to be used and turn off automatic
detection. Write this to `gradle.properties` in project root:

```
org.gradle.java.installations.auto-detect=false
org.gradle.java.installations.paths=/usr/lib/jvm/java-19-openjdk-amd64
```