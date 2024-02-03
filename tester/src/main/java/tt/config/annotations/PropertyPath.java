package tt.config.annotations;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PropertyPath {
    private final String[] path;

    private PropertyPath (String[] path) {
        this.path = path;
    }

    public static final PropertyPath top() {
        return new PropertyPath(new String[] {});
    }

    public static final PropertyPath from(String[] path) {
        return new PropertyPath(path.clone());
    }

    public static final PropertyPath from(String path) {
        return new PropertyPath(path.split("\\.", -1));
    }

    public boolean isTop() {
        if (this.path.length == 0) {
            return true;
        }
        if (this.path.length == 1 && this.path[0] == "") {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return Stream.of(this.path).collect(Collectors.joining("."));
    }

    public PropertyPath concat(String string) {
        if (this.isTop()) {
            return new PropertyPath(new String[]{ string });
        }

        var path = Arrays.copyOf(this.path, this.path.length + 1);
        path[path.length - 1] = string;
        return new PropertyPath(path);
    }

    @Override
    public boolean equals(Object obj) {
        System.err.println("cmp '" + this + "' vs '" + obj + "'");
        return this.toString().equals(obj.toString());
    }

    @Override
    public int hashCode() {            
        return this.toString().hashCode();
    }

    public String[] toArray() {
        return this.path.clone();
    }

    public PropertyPath convertEach(Function<String, String> converter) {
        return new PropertyPath(Stream.of(this.path).map(converter).toArray(String[]::new));
    }
}