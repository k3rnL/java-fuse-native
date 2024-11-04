# Java FUSE Native

This project is a Java library that provides a FUSE (Filesystem in Userspace) integration using GraalVM native images.
It allows Java applications to interact with the filesystem at a low level, bringing the power of FUSE to Java in a native, high-performance way. 
Users can implement various filesystem operations by extending the provided classes to achieve seamless native-to-Java transitions.

## Features
- **High Performance**: The library is built using GraalVM native images, which provides high performance and low memory consumption.
- **Java-to-FUSE Integration**: The library provides a seamless integration between Java and FUSE, allowing Java applications to interact with the filesystem at a low level.
- **GraalVM Native Image Support**: The library is made to be built exclusively with GraalVM native images, which provides standalone, native executable and fast startup times and low memory consumption.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Examples](#examples)

## Prerequisites
- [GraalVM](https://www.graalvm.org/) 21.0.0 or later
- FUSE 3 or later installed on your system

I've never tried to compile on Windows or MacOS, so I can't guarantee that it will work. If you have any issues, please let me know.

## Getting Started
Download the latest release from the releases page, and include it in your project.
For now it's not available in Maven Central, so you have to download the jar from the releases page.

There is no additional configuration required to use the library.

## Usage
To use the library, you need to extend the `JavaFuseOperations` class and implement the required methods.
I suggest you to start with `getattr` and `readdir` methods, as they are the most basic ones and let you `ls` and `cd` in the FileSystem.

Here is a basic POM configuration to build the project:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example.fuse</groupId>
    <artifactId>some-fuse-native-fs</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>com.k3rnl.fuse</groupId>
            <artifactId>fuse-native</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.graalvm.buildtools</groupId>
                <artifactId>native-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <id>some-fuse-native-fs</id>
                        <goals>
                            <goal>compile-no-fork</goal>
                        </goals>
                        <phase>package</phase>
                        <configuration>
                            <mainClass>org.example.fuse.Main</mainClass>
                            <imageName>some-fuse-native-fs</imageName>
                            <buildArgs>
                                <arg>-O2</arg>
                                <arg>--no-fallback</arg>
                            </buildArgs>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>
```

Runnings `mvn package` will generate a native image in the `target` directory.

This is a minimal example of in-memory FUSE filesystem that allows you to list the root directory and read files:
```java
public class MemoryFSOperations extends JavaFuseOperations {

    public static void main(String[] args) {
        MemoryFSOperations memoryFSOperations = new MemoryFSOperations();
        memoryFSOperations.nodes.put("/", new Folder("/")); // add root directory

        FuseNative fuse = new FuseNative(memoryFSOperations);

        fuse.run("/tmp/test", true, List.of("-o", "fsname=MemoryFS"));
    }

    private static final int CHUNK_SIZE = 4096 * 8; // 32KB

    static abstract class Node {
        final String name;
        Instant atime;
        Instant mtime;

        int uid;
        int gid;
        int permission;

        public Node(String name) {
            this.name = name;
            this.atime = Instant.now();
            this.mtime = Instant.now();
            this.uid = 1000;
            this.gid = 1000;
            this.permission = 0755;
        }
    }

    static class Folder extends Node {
        final List<Node> children = new ArrayList<>();

        public Folder(String name) {
            super(name);
        }
    }

    static class File extends Node {
        List<byte[]> data;
        long size;

        public File(String name) {
            super(name);
            this.data = new ArrayList<>();
            this.size = 0;
        }
    }

    private final Map<String, Node> nodes = new HashMap<>();

    private final Map<Long, File> openFiles = new HashMap<>();
    private final AtomicLong nextFileHandle = new AtomicLong(0);

    private void fillFileStat(Node node, FileStat stat) {
        stat.st_atime(node.atime.getEpochSecond());
        stat.st_mtime(node.mtime.getEpochSecond());
        stat.st_uid(node.uid);
        stat.st_gid(node.gid);
        if (node instanceof File file) {
            stat.st_size(file.size);
            stat.st_mode(FileStatFlags.S_IFREG | node.permission);
        } else {
            stat.st_size(0);
            stat.st_mode(FileStatFlags.S_IFDIR | node.permission);
        }
        stat.st_nlink(1);
    }

    @Override
    public int getattr(String path, FileStat stat, FuseFileInfo fi) {
        Node node = nodes.get(path);
        if (node == null) 
            return -Errno.ENOENT();
        fillFileStat(node, stat);
        return 0;
    }

    @Override
    public int readdir(String path, VoidPointer buf, FillDir filler, long offset, FuseFileInfo fi) {
        Node node = nodes.get(path);
        if (node == null) {
            return Errno.ENOENT();
        } else if (node instanceof File) {
            return Errno.ENOTDIR();
        }
        FileStat stat = StackValue.get(FileStat.class);
        Folder folder = (Folder) node;
        filler.apply(buf, ".", WordFactory.nullPointer(), 0);
        filler.apply(buf, "..", WordFactory.nullPointer(), 0);
        for (Node child : folder.children) {
            fillFileStat(child, stat);
            filler.apply(buf, child.name, stat, 0);
        }
        return 0;
    }
}
```

## Examples
You can find more examples in the [`examples` directory](https://github.com/k3rnL/java-fuse-native/tree/main/src/main/java/com/k3rnl/fuse/examples).
To compile the examples :

```bash
git clone https://github.com/k3rnL/java-fuse-native.git
cd java-fuse-native
mvn -Pbuild-examples package
```

Then you can run the examples using the generated native image:
```bash
./target/memory-fs /path/to/mountpoint -o fsname=MemoryFS
```

You can find a real world application with the [HDFS implementation](https://github.com/k3rnL/java-hdfs-fuse)