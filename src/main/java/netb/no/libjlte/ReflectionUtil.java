package netb.no.libjlte;

import netb.no.libjlte.annotations.Getter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReflectionUtil {

    private static final List<String> getterNamePatterns = Arrays.asList(
            "get%s",
            "is%s",
            "getIs%s"
    );

    public static Method findGetter(Object object, String property) {
        String partialName = property.substring(0, 1).toUpperCase() + property.substring(1); // capitalize first letter
        Set<String> fullNames = getterNames(partialName);

        Method[] methods = object.getClass().getMethods();

        List<Method> getters = Arrays.stream(methods)
                .filter(m -> m.isAnnotationPresent(Getter.class)
                        && m.getParameterCount() == 0
                        && fullNames.contains(m.getName()))
                .collect(Collectors.toList());

        if (getters.size() != 1) {
            throw new TemplateException("jlte: expected exactly 1 getter for property \"" + property + "\" (found " + getters.size() + "\")");
        }

        return getters.get(0);
    }

    private static Set<String> getterNames(String property) {
        return getterNamePatterns
                .stream()
                .map(s -> String.format(s, property))
                .collect(Collectors.toSet());
    }
}
