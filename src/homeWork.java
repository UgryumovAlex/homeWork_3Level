import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 1. Создать класс, который может выполнять «тесты»,
 *    в качестве тестов выступают классы с наборами методов с аннотациями @Test.
 *    Для этого у него должен быть статический метод start(), которому в качестве параметра передается или объект типа Class,
 *    или имя класса. Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если такой имеется, далее
 *    запущены методы с аннотациями @Test, а по завершению всех тестов – метод с аннотацией @AfterSuite.
 *    К каждому тесту необходимо также добавить приоритеты (int числа от 1 до 10), в соответствии с которыми будет выбираться
 *    порядок их выполнения, если приоритет одинаковый, то порядок не имеет значения.
 *    Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре,
 *    иначе необходимо бросить RuntimeException при запуске «тестирования».
  * */

public class homeWork {
    public static void main(String[] args) {
        try {
            start(ClassForTesting.class);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void start(Class testClass ) throws InvocationTargetException, IllegalAccessException, RuntimeException {
        Method[] methods = testClass.getDeclaredMethods();

        Method beforeSuiteMethod = null;
        Method afterSuiteMethod  = null;
        List<PriorityMethod> testMethods = new ArrayList<>();

        for (Method m : methods) {
            Annotation[] annos =  m.getDeclaredAnnotations();
            for (Annotation anno : annos) {
                if (anno.annotationType() == Test.class) {
                    testMethods.add(new PriorityMethod(m.getAnnotation(Test.class).priority(), m));
                }

                if (anno.annotationType() == BeforeSuite.class) {
                    if (beforeSuiteMethod == null) {
                        beforeSuiteMethod = m;
                    } else {
                        throw new RuntimeException("В тестируемом классе объявлено более одного метода с аннотацией @BeforeSuite");
                    }
                }

                if (anno.annotationType() == AfterSuite.class) {
                    if (afterSuiteMethod == null) {
                        afterSuiteMethod = m;
                    } else {
                        throw new RuntimeException("В тестируемом классе объявлено более одного метода с аннотацией @AfterSuite");
                    }
                }
            }

        }

        try {
            Object testObject = testClass.newInstance();

            if (beforeSuiteMethod != null ) { beforeSuiteMethod.invoke(testObject); } //Запускаем BeforeSuite, если есть

            Collections.sort(testMethods);  //Сортируем методы по приоритету
            for ( PriorityMethod pm : testMethods ) {
                 pm.getMethod().invoke(testObject);  //Запускаем методы Test
            }

            if (afterSuiteMethod != null ) { afterSuiteMethod.invoke(testObject); } //Запускаем AfterSuite, если есть

        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
