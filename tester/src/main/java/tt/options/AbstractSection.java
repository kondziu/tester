package tt.options;

import java.lang.reflect.Field;

import tt.config.Config;
import tt.config.PropertyConfig;

public abstract class AbstractSection {

    public static final Object initialize(Object object) throws Exception {

        Class cls = object.getClass();
        Section section = (Section) cls.getAnnotation(Section.class);

        if (null == section) {
            // TODO Exception
            String message = String.format("Class %s is not annotated as a @Section, so it cannot be loaded as options", cls.getCanonicalName());
            throw new Exception(message);
        }        

        Config config = new PropertyConfig(section.config());
        cls.getSimpleName();

        for (Field field : cls.getFields()) {
            if (field.isAnnotationPresent(Option.class)) {
                
            }
        }

        return object;
    }

}