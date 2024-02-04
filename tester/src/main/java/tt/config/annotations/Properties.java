package tt.config.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tt.cases.CaseAwareString;
import tt.config.Config;
import tt.config.PropertyConfig;
import tt.config.annotations.exceptions.AnnotationException;
import tt.config.annotations.exceptions.CannotConvertIntoTypeException;
import tt.config.annotations.exceptions.CannotCreateConverterInstanceException;
import tt.config.annotations.exceptions.CannotSetFieldValueException;
import tt.config.annotations.exceptions.FileNotFoundException;
import tt.config.annotations.exceptions.NoGetterException;
import tt.config.annotations.exceptions.NoSetterException;
import tt.config.annotations.exceptions.NotAConverterException;
import tt.config.annotations.exceptions.NotAnnotatedException;
import tt.config.annotations.exceptions.UnsupportedFieldTypeException;
import tt.config.exceptions.ConfigException;

public interface Properties {

    // default From extractPropertiesAnnotation() throws NotAnnotatedException {
    //     Class<?> cls = this.getClass();
    //     Annotation propertyInterface = cls.getAnnotation(From.class);
    //     if (null == propertyInterface) {
    //         throw new NotAnnotatedException(cls, From.class);
    //     }
    //     return (From) propertyInterface;
    // }

    // default String extractPropertiesFilePath() throws NotAnnotatedException, FileNotFoundException {
    //     From from = this.extractPropertiesAnnotation();
    //     String path = from.file();
    //     URL resource = Config.class.getClassLoader().getResource(path);
    //     if (resource == null) {
    //         Class<?> cls = this.getClass();
    //         throw new FileNotFoundException(cls, path);
    //     }
    //     return path;
    // }

    // default List<Field> extractOptionAnnotatedFields() {
    //     Class<?> cls = this.getClass();
    //     return Stream.of(cls.getFields())
    //             .filter(f -> f.isAnnotationPresent(Option.class))
    //             .collect(Collectors.toList());
    // }

    // // XXX
    // default String extractPropertyNameFromField(PropertyPath path, Field field) {
    //     Option annotation = field.getAnnotation(Option.class);
    //     String property = annotation.property();

    //     if (property.isEmpty()) {
    //         String name = field.getName();
    //         property = CaseAwareString.fromCamelCase(name).intoSnakeCase(false);
    //     }

    //     PropertyPath fullPath = path.concat(property);
    //     return fullPath.toString();
    // }

    // default <T> Optional<Converter<String, T>> extractConverterFromField(Field field) throws AnnotationException {

    //     Option annotation = field.getAnnotation(Option.class);
    //     Type fieldType = field.getGenericType();
    //     Class<?> cls = annotation.converter();

    //     if (cls == None.class) {
    //         return Optional.empty();
    //     }

    //     var interfaces = List.of(cls.getInterfaces());
    //     int converterIndex = interfaces.indexOf(Converter.class);
    //     if (converterIndex < 0) {
    //         throw new NotAConverterException(this.getClass(), field, fieldType, cls);
    //     }

    //     var genericInterface = cls.getGenericInterfaces()[converterIndex];
    //     assert (genericInterface instanceof ParameterizedType);

    //     Type[] generics = ((ParameterizedType) genericInterface).getActualTypeArguments();
    //     assert (generics.length == 2);

    //     Type from = generics[0];
    //     Type into = generics[1];

    //     // System.err.println("into: " + into);
    //     // System.err.println("fieldType: " + fieldType);

    //     assert (from == String.class);
    //     assert (into instanceof Class<?>);

    //     if (!into.equals(fieldType)) {
    //         throw new CannotConvertIntoTypeException(this.getClass(), field, fieldType, cls, into);
    //     }

    //     try {
    //         @SuppressWarnings("unchecked")
    //         Converter<String, T> converter = (Converter<String, T>) cls.getConstructor().newInstance();
    //         return Optional.of(converter);
    //     } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
    //             | InvocationTargetException | NoSuchMethodException | SecurityException | ClassCastException e) {
    //         throw new CannotCreateConverterInstanceException(this.getClass(), field, fieldType, cls, e);
    //     }
    // }

    // default <T> void setFieldValueFromConfigDefault(Config config, Field field, String property)
    //         throws ConfigException, AnnotationException {

    //     Class<?> type = field.getType();
    //     if (!Properties.setters.containsKey(type)) {
    //         throw new UnsupportedFieldTypeException(this.getClass(), field, type, Option.class, setters.keySet());
    //     }

    //     ValueGetter<Object> getter = Properties.getters.get(type);
    //     if (getter == null) {
    //         throw new NoGetterException(this.getClass(), field, type, getters.keySet());
    //     }

    //     FieldSetter<Object> setter = Properties.setters.get(type);
    //     if (setter == null) {
    //         throw new NoSetterException(this.getClass(), field, type, setters.keySet());
    //     }

    //     Object value = getter.apply(config, property);
    //     try {
    //         setter.apply(field, this, value);
    //     } catch (IllegalAccessException e) {
    //         throw new CannotSetFieldValueException(this.getClass(), field, Option.class, type, e);
    //     }
    // }

    // default <T> void setFieldValueFromConfigCustom(Config config, Field field, String property,
    //         Converter<String, ?> converter) throws ConfigException, AnnotationException {

    //     String value = config.getOrFail(property);
    //     Object object = converter.apply(value);
        
    //     try {
    //         field.set(this, object);
    //     } catch (IllegalAccessException e) {
    //         throw new CannotSetFieldValueException(this.getClass(), field, Option.class, field.getType(), e);
    //     }
    // }

    // default boolean isFieldANestedPropertyObject(Field field) {
    //     return Stream.of(field.getType().getInterfaces()).anyMatch((Class<?> i) -> 
    //         Properties.class.equals(i)
    //     );
    // }

    // @SuppressWarnings("unchecked")
    // default <T> void setFieldValueFromNestedPropertyObject(String filePath, PropertyPath propertyPath, Config config, Field field, String property) throws ConfigException, AnnotationException {
    //     Constructor<Properties> constructor;
    //     try {
    //         constructor = (Constructor<Properties>) field.getType().getConstructor();
    //     } catch (ClassCastException | NoSuchMethodException | SecurityException e) {
    //         throw new UnsupportedOperationException("WIP2"); // TODO exception
    //     }
        
    //     Properties nestedProperties;
    //     try {
    //         nestedProperties = constructor.newInstance();
    //     } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
    //         throw new UnsupportedOperationException("WIP3"); // TODO exception
    //     }

    //     String propertyName = this.extractPropertyNameFromField(propertyPath, field);
    //     PropertyPath nextedPropertyPath = propertyPath.concat(propertyName);
    //     nestedProperties.initializeProperties(filePath, nextedPropertyPath);

    //     try {
    //         field.set(this, nestedProperties);
    //     } catch (IllegalArgumentException | IllegalAccessException e) {
    //         throw new UnsupportedOperationException("WIP4"); // TODO exception
    //     }
    // }

    // // default <T> T ffumbshional(Supplier<T> f, String s, Object... spaceName) {
    // //     try {
    // //         return f.get();
    // //     } catch (Exception e) {
    // //         throw new UnsupportedOperationException(String.format(s, spaceName), e);
    // //     }
    // // }

    // default <T> void setFieldValueFromConfig(String filePath, PropertyPath propertyPath, Config config, PropertyPath path, Field field)
    //         throws ConfigException, AnnotationException {

    //     String property = this.extractPropertyNameFromField(path, field);
    //     var converterOption = this.extractConverterFromField(field);

    //     if (this.isFieldANestedPropertyObject(field)) {
    //         if (!converterOption.isEmpty()) {
    //             throw new UnsupportedOperationException("WIP1"); // TODO exception
    //         }
    //         this.setFieldValueFromNestedPropertyObject(filePath, propertyPath, config, field, property);
    //     } else if (converterOption.isEmpty()) {
    //         this.setFieldValueFromConfigDefault(config, field, property);
    //     } else {
    //         var converter = converterOption.get();
    //         this.setFieldValueFromConfigCustom(config, field, property, converter);
    //     }
    // }

    // default void initializeProperties() throws ConfigException, AnnotationException {
    //     String file = this.extractPropertiesFilePath();
    //     this.initializeProperties(file, PropertyPath.top());
    // }

    // default void initializeProperties(String filePath, PropertyPath propertyPath) throws ConfigException, AnnotationException {
    //     Config config = new PropertyConfig(filePath);
    //     List<Field> fields = this.extractOptionAnnotatedFields();
    //     for (Field field : fields) {
    //         this.setFieldValueFromConfig(filePath, propertyPath, config, propertyPath, field);
    //     }
    // }

    // KEEP



    // default void initialize() throws ConfigException, AnnotationException {
    //     var propertClass = PropertyClass.from(this);
    //     initialize(propertClass);
    // }

    // default void initialize(PropertyClass<Properties> propertyClass) throws ConfigException, AnnotationException {

    // }

    default void initialize() throws ConfigException, AnnotationException {
        var property = PropertyClass.from(this);
        property.populateFields();
    }

    default <T extends Properties> void initialize(PropertyField<T /*baka!*/> field) throws ConfigException, AnnotationException {
        var property = PropertyClass.from(this, field);
        property.populateFields();
    }
}
