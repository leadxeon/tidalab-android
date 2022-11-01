package bolts;
/* loaded from: classes.dex */
public interface Continuation<TTaskResult, TContinuationResult> {
    TContinuationResult then(Task<TTaskResult> task) throws Exception;
}
