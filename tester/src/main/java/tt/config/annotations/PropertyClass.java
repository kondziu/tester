package tt.config.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tt.config.Config;
import tt.config.PropertyConfig;
import tt.config.annotations.exceptions.AnnotationException;
import tt.config.annotations.exceptions.FileNotFoundException;
import tt.config.annotations.exceptions.NotAnnotatedException;
import tt.config.exceptions.ConfigException;

public class PropertyClass<T extends Properties> {
    
    public final T instance;
    public final Class<T> cls;
    public final PropertyPath path;
    public final String file;
    public final Config config;
    private List<PropertyField<T>> fields;
    private final Optional<PropertyField<?>> parent;

    @SuppressWarnings("unchecked")
    private PropertyClass(Optional<PropertyField<?>> parent, T instance, Config config, PropertyPath path, String filePath)  {
        this.instance = instance;
        this.path = PropertyPath.top();
        this.cls = (Class<T>) instance.getClass();
        this.file = filePath;
        this.config = config;
        this.parent = parent;

        this.fields = this.optionFields().stream()
            .map((field) -> new PropertyField<T>(this, field))
            .collect(Collectors.toList());
    }

    public static <T extends Properties> PropertyClass<T> from(T instance) throws NotAnnotatedException, FileNotFoundException {
        System.err.println("...");
        String filePath = getFilePath(instance);
        System.err.println(",,,");
        Config config = new PropertyConfig(filePath);
        return new PropertyClass<T>(Optional.empty(), instance, config, PropertyPath.top(), filePath);
    }

    public static <T extends Properties, F extends Properties> PropertyClass<T> from(T instance, PropertyField<F> field) throws NotAnnotatedException, FileNotFoundException {
        return new PropertyClass<T>(Optional.of(field), instance, field.parent.config, field.path(), field.parent.file);
    }

    // public static <T extends Properties> PropertyClass<T> from(T instance, Config config, PropertyPath path, String filePath) {
    //     return new PropertyClass<T>(instance, config, path, filePath);
    // }

    public static <T extends Properties> String getFilePath(T instance) throws NotAnnotatedException, FileNotFoundException {
        Class<?> cls = instance.getClass();

        Annotation annotation = cls.getAnnotation(From.class);
        if (null == annotation) {
            throw new NotAnnotatedException(cls, From.class);
        }

        From from = (From) annotation;
        String path = from.file();

        URL resource = Config.class.getClassLoader().getResource(path);
        if (resource == null) {
            throw new FileNotFoundException(cls, path);
        }

        return path;
    }

    public List<Field> optionFields() {
        return Stream.of(this.cls.getFields())
                .filter(field -> field.isAnnotationPresent(Option.class))
                .collect(Collectors.toList());
    }


    public void populateFields() throws AnnotationException, ConfigException {
        for (var field : this.fields) {            
            field.set();
        }
    }
}
