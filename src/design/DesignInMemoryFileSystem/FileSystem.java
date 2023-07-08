package design.DesignInMemoryFileSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class LogicalEntity {
    boolean isFile;
    String name;
}

class File extends LogicalEntity {
    String content;
    File(String name, String content) {
        isFile = true;
        this.name = name;
        this.content = content;
    }
}

class Directory extends LogicalEntity {
    List<LogicalEntity> entities;
    Directory(String name) {
        isFile = false;
        this.name = name;
        this.entities = new ArrayList<>();
    }
}

public class FileSystem {
    private final Directory root;
    private static void test1() {
        FileSystem fileSystem = new FileSystem();
        System.out.println(fileSystem.ls("/"));                         // return []
        fileSystem.mkdir("/a/b/c");
        fileSystem.addContentToFile("/a/b/c/d", "hello");
        System.out.println(fileSystem.ls("/"));                         // return ["a"]
        System.out.println(fileSystem.readContentFromFile("/a/b/c/d")); // return "hello"
    }
    public static void main(String[] args) {
        test1();
    }

    public FileSystem() {
        this.root = new Directory("/");
    }

    public List<String> ls(String path) {
        String[] subpaths = path.split("/");
        Directory directory = getDirectory(0, subpaths, root);
        return directory != null ? directory.entities.stream().map(entity -> entity.name).collect(Collectors.toList()) : List.of();
    }

    public void mkdir(String path) {
        String[] subpaths = path.split("/");
        createDirectory(0, subpaths, root);
    }

    public void addContentToFile(String filePath, String content) {
        String[] subpaths = filePath.split("/");
        addContentToFile(0, subpaths, content, root);
    }

    public String readContentFromFile(String filePath) {
        String[] subpaths = filePath.split("/");
        return readContentFromFile(0, subpaths, root);
    }

    private String readContentFromFile(int i, String[] subpaths, Directory directory) {
        if (i == subpaths.length-1) {
            File file = (File) directory.entities.stream().filter(entity -> entity.isFile && entity.name.equals(subpaths[i])).findAny().orElse(null);
            return file != null ? file.content : "";
        }

        Directory dir = (Directory) directory.entities.stream().filter(entity -> !entity.isFile && entity.name.equals(subpaths[i])).findAny().orElse(null);
        if (dir != null) {
            return readContentFromFile(i+1, subpaths, dir);
        } else {
            Directory newDirectory = new Directory(subpaths[i]);
            directory.entities.add(newDirectory);
            return readContentFromFile(i+1, subpaths, newDirectory);
        }
    }

    private void addContentToFile(int i, String[] subpaths, String content, Directory directory) {
        if (i == subpaths.length-1) {
            File file = (File) directory.entities.stream().filter(entity -> entity.isFile && entity.name.equals(subpaths[i])).findAny().orElse(null);
            if (file != null) file.content += content;
            else directory.entities.add(new File(subpaths[i], content));
            return;
        }

        Directory dir = (Directory) directory.entities.stream().filter(entity -> !entity.isFile && entity.name.equals(subpaths[i])).findAny().orElse(null);
        if (dir != null) {
            addContentToFile(i+1, subpaths, content, dir);
        } else {
            Directory newDirectory = new Directory(subpaths[i]);
            directory.entities.add(newDirectory);
            addContentToFile(i+1, subpaths, content, newDirectory);
        }
    }

    private Directory getDirectory(int i, String[] subpaths, Directory directory) {
        if (i == subpaths.length) return directory;
        Directory dir = (Directory) directory.entities.stream().filter(entity -> !entity.isFile && entity.name.equals(subpaths[i])).findAny().orElse(null);
        if (dir == null) return null;
        return getDirectory(i+1, subpaths, dir);
    }

    private void createDirectory(int i, String[] subpaths, Directory directory) {
        if (i == subpaths.length) return;

        Directory dir = (Directory) directory.entities.stream().filter(entity -> entity.name.equals(subpaths[i]) && !entity.isFile).findAny().orElse(null);
        if (dir == null) {
            Directory newDirectory = new Directory(subpaths[i]);
            directory.entities.add(newDirectory);
            createDirectory(i+1, subpaths, newDirectory);
        } else {
            createDirectory(i+1, subpaths, dir);
        }
    }
}
