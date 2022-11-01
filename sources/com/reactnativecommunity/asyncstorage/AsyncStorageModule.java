package com.reactnativecommunity.asyncstorage;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.horcrux.svg.PathParser;
@ReactModule(name = AsyncStorageModule.NAME)
/* loaded from: classes.dex */
public final class AsyncStorageModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private static final int MAX_SQL_KEYS = 999;
    public static final String NAME = "RNC_AsyncSQLiteDBStorage";
    private final SerialExecutor executor;
    private ReactDatabaseSupplier mReactDatabaseSupplier;
    private boolean mShuttingDown;

    public AsyncStorageModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ensureDatabase() {
        if (this.mShuttingDown) {
            return false;
        }
        this.mReactDatabaseSupplier.ensureDatabase();
        return true;
    }

    @ReactMethod
    public void clear(final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.reactnativecommunity.asyncstorage.AsyncStorageModule.5
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void[] voidArr) {
                AsyncStorageModule.this.mReactDatabaseSupplier.ensureDatabase();
                try {
                    AsyncStorageModule.this.mReactDatabaseSupplier.clear();
                    callback.invoke(new Object[0]);
                } catch (Exception e) {
                    FLog.w("ReactNative", e.getMessage(), e);
                    callback.invoke(PathParser.getError(null, e.getMessage()));
                }
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    public void clearSensitiveData() {
        ReactDatabaseSupplier reactDatabaseSupplier = this.mReactDatabaseSupplier;
        synchronized (reactDatabaseSupplier) {
            try {
                reactDatabaseSupplier.clear();
                reactDatabaseSupplier.closeDatabase();
                FLog.d("ReactNative", "Cleaned RKStorage");
            } catch (Exception unused) {
                if (reactDatabaseSupplier.deleteDatabase()) {
                    FLog.d("ReactNative", "Deleted Local Database RKStorage");
                    return;
                }
                throw new RuntimeException("Clearing and deleting database RKStorage failed");
            }
        }
    }

    @ReactMethod
    public void getAllKeys(final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.reactnativecommunity.asyncstorage.AsyncStorageModule.6
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void[] voidArr) {
                if (!AsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(PathParser.getDBError(null), null);
                    return;
                }
                WritableArray createArray = Arguments.createArray();
                Cursor query = AsyncStorageModule.this.mReactDatabaseSupplier.get().query("catalystLocalStorage", new String[]{"key"}, null, null, null, null, null);
                try {
                    try {
                        if (query.moveToFirst()) {
                            do {
                                createArray.pushString(query.getString(0));
                            } while (query.moveToNext());
                            query.close();
                            callback.invoke(null, createArray);
                        }
                        query.close();
                        callback.invoke(null, createArray);
                    } catch (Exception e) {
                        FLog.w("ReactNative", e.getMessage(), e);
                        callback.invoke(PathParser.getError(null, e.getMessage()), null);
                        query.close();
                    }
                } catch (Throwable th) {
                    query.close();
                    throw th;
                }
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        super.initialize();
        this.mShuttingDown = false;
    }

    @ReactMethod
    public void multiGet(final ReadableArray readableArray, final Callback callback) {
        if (readableArray == null) {
            callback.invoke(PathParser.getInvalidKeyError(null), null);
        } else {
            new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.reactnativecommunity.asyncstorage.AsyncStorageModule.1
                /* JADX WARN: Removed duplicated region for block: B:25:0x00d2 A[LOOP:4: B:23:0x00cc->B:25:0x00d2, LOOP_END] */
                @Override // com.facebook.react.bridge.GuardedAsyncTask
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void doInBackgroundGuarded(java.lang.Void[] r20) {
                    /*
                        Method dump skipped, instructions count: 291
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.asyncstorage.AsyncStorageModule.AnonymousClass1.doInBackgroundGuarded(java.lang.Object[]):void");
                }
            }.executeOnExecutor(this.executor, new Void[0]);
        }
    }

    @ReactMethod
    public void multiMerge(final ReadableArray readableArray, final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.reactnativecommunity.asyncstorage.AsyncStorageModule.4
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void[] voidArr) {
                WritableMap writableMap = null;
                try {
                    if (!AsyncStorageModule.this.ensureDatabase()) {
                        callback.invoke(PathParser.getDBError(null));
                        return;
                    }
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.get().beginTransaction();
                        for (int i = 0; i < readableArray.size(); i++) {
                            try {
                                if (readableArray.getArray(i).size() != 2) {
                                    PathParser.getInvalidValueError(null);
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                } else if (readableArray.getArray(i).getString(0) == null) {
                                    PathParser.getInvalidKeyError(null);
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                } else if (readableArray.getArray(i).getString(1) == null) {
                                    PathParser.getInvalidValueError(null);
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                } else if (!PathParser.mergeImpl(AsyncStorageModule.this.mReactDatabaseSupplier.get(), readableArray.getArray(i).getString(0), readableArray.getArray(i).getString(1))) {
                                    PathParser.getDBError(null);
                                    AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                }
                                return;
                            } catch (Exception e) {
                                FLog.w("ReactNative", e.getMessage(), e);
                                return;
                            }
                        }
                        AsyncStorageModule.this.mReactDatabaseSupplier.get().setTransactionSuccessful();
                        try {
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                        } catch (Exception e2) {
                            FLog.w("ReactNative", e2.getMessage(), e2);
                            writableMap = PathParser.getError(null, e2.getMessage());
                        }
                    } catch (Exception e3) {
                        FLog.w("ReactNative", e3.getMessage(), e3);
                        writableMap = PathParser.getError(null, e3.getMessage());
                        try {
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                        } catch (Exception e4) {
                            FLog.w("ReactNative", e4.getMessage(), e4);
                        }
                    }
                    if (writableMap != null) {
                        callback.invoke(writableMap);
                    } else {
                        callback.invoke(new Object[0]);
                    }
                } catch (Throwable th) {
                    try {
                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                    } catch (Exception e5) {
                        FLog.w("ReactNative", e5.getMessage(), e5);
                        PathParser.getError(null, e5.getMessage());
                    }
                    throw th;
                }
            }
        }.executeOnExecutor(this.executor, new Void[0]);
    }

    @ReactMethod
    public void multiRemove(final ReadableArray readableArray, final Callback callback) {
        if (readableArray.size() == 0) {
            callback.invoke(new Object[0]);
        } else {
            new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.reactnativecommunity.asyncstorage.AsyncStorageModule.3
                @Override // com.facebook.react.bridge.GuardedAsyncTask
                public void doInBackgroundGuarded(Void[] voidArr) {
                    WritableMap writableMap = null;
                    try {
                        if (!AsyncStorageModule.this.ensureDatabase()) {
                            callback.invoke(PathParser.getDBError(null));
                            return;
                        }
                        try {
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().beginTransaction();
                            for (int i = 0; i < readableArray.size(); i += AsyncStorageModule.MAX_SQL_KEYS) {
                                int min = Math.min(readableArray.size() - i, (int) AsyncStorageModule.MAX_SQL_KEYS);
                                SQLiteDatabase sQLiteDatabase = AsyncStorageModule.this.mReactDatabaseSupplier.get();
                                String buildKeySelection = PathParser.buildKeySelection(min);
                                ReadableArray readableArray2 = readableArray;
                                String[] strArr = new String[min];
                                for (int i2 = 0; i2 < min; i2++) {
                                    strArr[i2] = readableArray2.getString(i + i2);
                                }
                                sQLiteDatabase.delete("catalystLocalStorage", buildKeySelection, strArr);
                            }
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().setTransactionSuccessful();
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                            } catch (Exception e) {
                                FLog.w("ReactNative", e.getMessage(), e);
                                writableMap = PathParser.getError(null, e.getMessage());
                            }
                        } catch (Exception e2) {
                            FLog.w("ReactNative", e2.getMessage(), e2);
                            writableMap = PathParser.getError(null, e2.getMessage());
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                            } catch (Exception e3) {
                                FLog.w("ReactNative", e3.getMessage(), e3);
                            }
                        }
                        if (writableMap != null) {
                            callback.invoke(writableMap);
                        } else {
                            callback.invoke(new Object[0]);
                        }
                    } catch (Throwable th) {
                        try {
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                        } catch (Exception e4) {
                            FLog.w("ReactNative", e4.getMessage(), e4);
                            PathParser.getError(null, e4.getMessage());
                        }
                        throw th;
                    }
                }
            }.executeOnExecutor(this.executor, new Void[0]);
        }
    }

    @ReactMethod
    public void multiSet(final ReadableArray readableArray, final Callback callback) {
        if (readableArray.size() == 0) {
            callback.invoke(new Object[0]);
        } else {
            new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) { // from class: com.reactnativecommunity.asyncstorage.AsyncStorageModule.2
                @Override // com.facebook.react.bridge.GuardedAsyncTask
                public void doInBackgroundGuarded(Void[] voidArr) {
                    WritableMap writableMap = null;
                    if (!AsyncStorageModule.this.ensureDatabase()) {
                        callback.invoke(PathParser.getDBError(null));
                        return;
                    }
                    SQLiteStatement compileStatement = AsyncStorageModule.this.mReactDatabaseSupplier.get().compileStatement("INSERT OR REPLACE INTO catalystLocalStorage VALUES (?, ?);");
                    try {
                        try {
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().beginTransaction();
                            for (int i = 0; i < readableArray.size(); i++) {
                                try {
                                    if (readableArray.getArray(i).size() != 2) {
                                        PathParser.getInvalidValueError(null);
                                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                    } else if (readableArray.getArray(i).getString(0) == null) {
                                        PathParser.getInvalidKeyError(null);
                                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                    } else if (readableArray.getArray(i).getString(1) == null) {
                                        PathParser.getInvalidValueError(null);
                                        AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                                    } else {
                                        compileStatement.clearBindings();
                                        compileStatement.bindString(1, readableArray.getArray(i).getString(0));
                                        compileStatement.bindString(2, readableArray.getArray(i).getString(1));
                                        compileStatement.execute();
                                    }
                                    return;
                                } catch (Exception e) {
                                    FLog.w("ReactNative", e.getMessage(), e);
                                    return;
                                }
                            }
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().setTransactionSuccessful();
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                            } catch (Exception e2) {
                                FLog.w("ReactNative", e2.getMessage(), e2);
                                writableMap = PathParser.getError(null, e2.getMessage());
                            }
                        } catch (Exception e3) {
                            FLog.w("ReactNative", e3.getMessage(), e3);
                            writableMap = PathParser.getError(null, e3.getMessage());
                            try {
                                AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                            } catch (Exception e4) {
                                FLog.w("ReactNative", e4.getMessage(), e4);
                            }
                        }
                        if (writableMap != null) {
                            callback.invoke(writableMap);
                        } else {
                            callback.invoke(new Object[0]);
                        }
                    } catch (Throwable th) {
                        try {
                            AsyncStorageModule.this.mReactDatabaseSupplier.get().endTransaction();
                        } catch (Exception e5) {
                            FLog.w("ReactNative", e5.getMessage(), e5);
                            PathParser.getError(null, e5.getMessage());
                        }
                        throw th;
                    }
                }
            }.executeOnExecutor(this.executor, new Void[0]);
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        this.mShuttingDown = true;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        this.mReactDatabaseSupplier.closeDatabase();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
    }

    /* JADX WARN: Removed duplicated region for block: B:95:0x009c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0066 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AsyncStorageModule(com.facebook.react.bridge.ReactApplicationContext r19, java.util.concurrent.Executor r20) {
        /*
            Method dump skipped, instructions count: 446
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.asyncstorage.AsyncStorageModule.<init>(com.facebook.react.bridge.ReactApplicationContext, java.util.concurrent.Executor):void");
    }
}
