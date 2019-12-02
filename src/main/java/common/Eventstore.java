package common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Eventstore<T> {
    private final Projector<T> projector;

    public Eventstore(Projector<T> projector) {
        this.projector = projector;
    }

    public T replay(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(getFilePathOf(fileName)))) {
            return projector.project(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFilePathOf(String fileName) {
        return String.format("src/main/resources/%s", fileName);
    }
}
