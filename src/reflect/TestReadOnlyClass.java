package reflect;

import java.lang.reflect.Field;

class ReadOnlyClass {
    private Integer age = 20;
    public Integer getAge() {
        return age;
    }
}

public class TestReadOnlyClass {
    public static void main(String[] args) throws Exception {
        ReadOnlyClass pt = new ReadOnlyClass();
        Class<?> clazz = ReadOnlyClass.class;
        Field field = clazz.getDeclaredField("age");
        field.setAccessible(true);
        field.set(pt, 30);
        System.out.println(pt.getAge());    // 30
    }
}
