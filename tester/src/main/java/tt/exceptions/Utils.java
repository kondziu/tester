package tt.exceptions;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Utils {

    private Utils() {} // static only

    private static Object[] extend(Object[] arguments, Object argument) {
        var array = Arrays.copyOf(arguments, arguments.length + 1);
        array[array.length - 1] = argument;
        return array;
    }

    public static <E extends Throwable> E constructException(Class<E> exception, Object... arguments) {
        Class<?>[] signature = Stream.of(arguments).map(Object::getClass).toArray(size -> (new Class<?>[size]));
        try {
            var constructor = exception.getConstructor(signature);
            return constructor.newInstance(arguments);
        } catch (NoSuchMethodException | SecurityException error) {
            throw new RuntimeException(String.format("Cannot construct object of class %s: %s", exception.getCanonicalName(), error.getMessage()), error);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException error) {
            throw new RuntimeException(String.format("Cannot call constructor for class %s: %s", exception.getCanonicalName(), error.getMessage()), error);
        } 
    }

    public static <R, E  extends Throwable> R notEmpty(Supplier<Optional<R>> f, Class<E> exception,  Object... arguments) throws E {
        Optional<R> result = f.get();
        if (result.isPresent()) {
            return result.get();
        }
        throw Utils.constructException(exception, arguments);
    }

    public static <R, E  extends Throwable> R notEmpty(Supplier<Optional<R>> f, Supplier<E> constructor) throws E {
        Optional<R> result = f.get();
        if (result.isPresent()) {
            return result.get();
        }
        throw constructor.get();
    }

    public static <R, E  extends Throwable> R notNull(Supplier<R> f, Class<E> exception, Object... arguments) throws E {
        R result = f.get();
        if (result != null) {
            return result;
        }
        throw Utils.constructException(exception, arguments);
    }

    public static <R, E  extends Throwable> R notNull(Supplier<R> f, Supplier<E> constructor) throws E {
        R result = f.get();
        if (result != null) {
            return result;
        }
        throw constructor.get();
    }

    public static <R, E  extends Throwable> R check(Supplier<Boolean> precondition, Supplier<R> f, Predicate<R> postcondition, Class<E> exception, Object... arguments) throws E {
        if (!precondition.get()) {
            throw Utils.constructException(exception, arguments); 
        }
        R result = f.get();
        if (!postcondition.test(result)) {
            throw Utils.constructException(exception, arguments);    
        }
        return result;
    }

    public static <R, E  extends Throwable> R check(Supplier<Boolean> precondition, Supplier<R> f, Predicate<R> postcondition, Supplier<E> constructor) throws E {
        if (!precondition.get()) {
            throw constructor.get();  
        }
        R result = f.get();
        if (!postcondition.test(result)) {
            throw constructor.get();  
        }
        return result;
    }

    public static <R, E  extends Throwable> R check(Supplier<R> f, Predicate<R> postcondition, Supplier<E> constructor) throws E {
        return check(() -> true, f, postcondition, constructor);
    }

    public static <R, E  extends Throwable> R check(Supplier<Boolean> precondition, Supplier<R> f, Supplier<E> constructor) throws E {
        return check(precondition, f, (result) -> true, constructor);
    }

    public static <E  extends Throwable> void check2(Supplier<Boolean> precondition, Runnable f, Supplier<Boolean> postcondition, Supplier<E> constructor) throws E {
        if (!precondition.get()) {
            throw constructor.get();  
        }
        f.run();
        if (!postcondition.get()) {
            throw constructor.get();  
        }
    }

    public static <E  extends Throwable> void check2(Runnable f, Supplier<Boolean> postcondition, Supplier<E> constructor) throws E {
        check2(() -> true, f, postcondition, constructor);
    }

    public static <E  extends Throwable> void check2(Supplier<Boolean> precondition, Runnable f, Supplier<E> constructor) throws E {
        check2(precondition, f, () -> true, constructor);
    }

    public static <E  extends Throwable> void check2(Supplier<Boolean> condition, Supplier<E> constructor) throws E {
        check2(condition, () -> {}, () -> true, constructor);
    }

    public static <R, E  extends Throwable> R attempt(Callable<R> f, Class<E> exception, Object... arguments) throws E {
        try {
            return f.call();
        } catch (Throwable e) {
            throw Utils.constructException(exception, extend(arguments, e));    
        }
    }

    public static <R, E  extends Throwable> R attempt(Callable<R> f, Function<Throwable, E> constructor) throws E {
        try {
            return f.call();
        } catch (Throwable e) {
            throw constructor.apply(e);
        }
    }

    @FunctionalInterface
    public static interface FallibleRunnable {
        void run() throws Throwable;
    }

    public static <R, E  extends Throwable> void attempt(FallibleRunnable f, Class<E> exception, Object... arguments) throws E {
        try {
            f.run();
        } catch (Throwable e) {
            throw Utils.constructException(exception, extend(arguments, e));
        }
    }

    public static <R, E  extends Throwable> void attempt(FallibleRunnable f, Function<Throwable, E> constructor) throws E {
        try {
            f.run();
        } catch (Throwable e) {
            throw constructor.apply(e);
        }
    }
}
