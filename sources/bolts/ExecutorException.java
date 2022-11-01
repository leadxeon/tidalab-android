package bolts;
/* loaded from: classes.dex */
public class ExecutorException extends RuntimeException {
    public ExecutorException(Exception exc) {
        super("An exception was thrown by an Executor", exc);
    }
}
