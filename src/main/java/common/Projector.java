package common;

import java.util.stream.Stream;

public interface Projector<T> {
    T project(Stream<String> lines);
}
