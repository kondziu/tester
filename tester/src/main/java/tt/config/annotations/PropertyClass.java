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

public interface PropertyClass<T extends Properties> {  

    public static class Top<T extends Properties> implements PropertyClass<T> {
        final T instance;
        final Class<T> cls;
        final String file;
        final Config config;
        final List<PropertyField<T>> fields;

        @SuppressWarnings("unchecked")
        private Top(T instance, Config config, String file)  {
            this.instance = instance;
            this.cls = (Class<T>) instance.getClass();
            this.file = file;
            this.config = config;
    
            this.fields = this.optionFields().stream()
                .map((field) -> new PropertyField<T>(this, field))
                .collect(Collectors.toList());
        }

        @Override
        public PropertyPath path() {
            return PropertyPath.top();
        }

        @Override
        public T instance() {
            return this.instance;
        }

        @Override
        public Class<T> cls() {
            return this.cls;
        }

        @Override
        public String file() {
            return this.file;
        }

        @Override
        public Config config() {
            return this.config;
        }

        @Override
        public List<PropertyField<T>> fields() {
            return this.fields;
        }

        @Override
        public Optional<PropertyField<?>> parent() {
            return Optional.empty();
        }
    }

    public static class Nested<T extends Properties, P extends Properties> implements PropertyClass<T> {
        final PropertyField<P> parent;
        final T instance;
        final Class<T> cls;
        final List<PropertyField<T>> fields;

        @SuppressWarnings("unchecked")
        private Nested(T instance, PropertyField<P> parent)  {
            this.parent = parent;
            this.instance = instance;
            this.cls = (Class<T>) instance.getClass();
    
            this.fields = this.optionFields().stream()
                .map((field) -> new PropertyField<T>(this, field))
                .collect(Collectors.toList());
        }

        @Override
        public PropertyPath path() {
            return this.parent.path();
        }

        @Override
        public T instance() {
            return this.instance;
        }

        @Override
        public Class<T> cls() {
            return this.cls;
        }

        @Override
        public String file() {
            return this.parent.file();
        }

        @Override
        public Config config() {
            return this.parent.config();
        }

        @Override
        public List<PropertyField<T>> fields() {
            return this.fields;
        }

        @Override
        public Optional<PropertyField<?>> parent() {
           return Optional.of(this.parent);
        }
    }
    
    public static <T extends Properties> PropertyClass<T> from(T instance) throws NotAnnotatedException, FileNotFoundException {
        String filePath = getFilePath(instance);
        Config config = new PropertyConfig(filePath);
        return new PropertyClass.Top<T>(instance, config, filePath);
    }

    public static <T extends Properties, P extends Properties> PropertyClass<T> from(T instance, PropertyField<P> parent) throws NotAnnotatedException, FileNotFoundException {
        return new PropertyClass.Nested<T, P>(instance, parent);
    }

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

    T instance();
    Class<T> cls();
    String file();
    Config config();
    List<PropertyField<T>> fields();
    Optional<PropertyField<?>> parent();
    PropertyPath path();

    default List<Field> optionFields() {
        return Stream.of(this.cls().getFields())
                .filter(field -> field.isAnnotationPresent(Option.class))
                .collect(Collectors.toList());
    }

    default void populateFields() throws AnnotationException, ConfigException {
        for (var field : this.fields()) {            
            field.set();
        }
    }
}
