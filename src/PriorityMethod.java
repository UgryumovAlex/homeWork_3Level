import java.lang.reflect.Method;

public class PriorityMethod implements Comparable<PriorityMethod> {
    private int priority;
    private Method method;

    public PriorityMethod(int priority, Method method) {
        this.priority = priority;
        this.method = method;
    }

    @Override
    public int compareTo(PriorityMethod o) {
        return (this.priority - o.priority);
    }

    public Method getMethod() {
        return method;
    }
}
