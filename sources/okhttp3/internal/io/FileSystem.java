package okhttp3.internal.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import okio.Okio;
import okio.Sink;
import okio.Source;
/* loaded from: classes.dex */
public interface FileSystem {
    public static final FileSystem SYSTEM = new FileSystem() { // from class: okhttp3.internal.io.FileSystem.1
        @Override // okhttp3.internal.io.FileSystem
        public Sink appendingSink(File file) throws FileNotFoundException {
            try {
                return Okio.appendingSink(file);
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                return Okio.appendingSink(file);
            }
        }

        @Override // okhttp3.internal.io.FileSystem
        public void delete(File file) throws IOException {
            if (!file.delete() && file.exists()) {
                throw new IOException("failed to delete " + file);
            }
        }

        @Override // okhttp3.internal.io.FileSystem
        public void deleteContents(File file) throws IOException {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        deleteContents(file2);
                    }
                    if (!file2.delete()) {
                        throw new IOException("failed to delete " + file2);
                    }
                }
                return;
            }
            throw new IOException("not a readable directory: " + file);
        }

        @Override // okhttp3.internal.io.FileSystem
        public boolean exists(File file) {
            return file.exists();
        }

        @Override // okhttp3.internal.io.FileSystem
        public void rename(File file, File file2) throws IOException {
            delete(file2);
            if (!file.renameTo(file2)) {
                throw new IOException("failed to rename " + file + " to " + file2);
            }
        }

        @Override // okhttp3.internal.io.FileSystem
        public Sink sink(File file) throws FileNotFoundException {
            try {
                Logger logger = Okio.logger;
                if (file != null) {
                    return Okio.sink(new FileOutputStream(file));
                }
                throw new IllegalArgumentException("file == null");
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                Logger logger2 = Okio.logger;
                return Okio.sink(new FileOutputStream(file));
            }
        }

        @Override // okhttp3.internal.io.FileSystem
        public long size(File file) {
            return file.length();
        }

        @Override // okhttp3.internal.io.FileSystem
        public Source source(File file) throws FileNotFoundException {
            Logger logger = Okio.logger;
            if (file != null) {
                return Okio.source(new FileInputStream(file));
            }
            throw new IllegalArgumentException("file == null");
        }
    };

    Sink appendingSink(File file) throws FileNotFoundException;

    void delete(File file) throws IOException;

    void deleteContents(File file) throws IOException;

    boolean exists(File file);

    void rename(File file, File file2) throws IOException;

    Sink sink(File file) throws FileNotFoundException;

    long size(File file);

    Source source(File file) throws FileNotFoundException;
}
