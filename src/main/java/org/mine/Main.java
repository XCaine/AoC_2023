package org.mine;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Reflections reflections = new Reflections("org.mine");
        List<Class<? extends BaseTask>> subtypes = reflections.getSubTypesOf(BaseTask.class)
                .stream()
                .sorted(Comparator.comparingInt(aClass -> Integer.parseInt(aClass.getSimpleName().substring(4))))
                .toList();
        try {
            for(Class<? extends BaseTask> subtypeClass : subtypes) {
                BaseTask instance = subtypeClass.getDeclaredConstructor().newInstance();
                instance.solveMeasured();
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}