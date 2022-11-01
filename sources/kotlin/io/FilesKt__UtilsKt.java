package kotlin.io;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.io.FileTreeWalk;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.ConstrainedOnceSequence;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt__IndentKt;
/* compiled from: Utils.kt */
/* loaded from: classes.dex */
public class FilesKt__UtilsKt extends FilesKt__FileTreeWalkKt {
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007d, code lost:
        if (r3.delete() == false) goto L_0x007f;
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0085 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean copyRecursively$default(java.io.File r17, java.io.File r18, boolean r19, kotlin.jvm.functions.Function2 r20, int r21) {
        /*
            r7 = r17
            r0 = r21 & 2
            r8 = 0
            if (r0 == 0) goto L_0x0009
            r9 = 0
            goto L_0x000b
        L_0x0009:
            r9 = r19
        L_0x000b:
            r10 = 4
            r0 = r21 & 4
            r11 = 0
            if (r0 == 0) goto L_0x0015
            kotlin.io.FilesKt__UtilsKt$copyRecursively$1 r0 = kotlin.io.FilesKt__UtilsKt$copyRecursively$1.INSTANCE
            r12 = r0
            goto L_0x0016
        L_0x0015:
            r12 = r11
        L_0x0016:
            boolean r0 = r17.exists()
            java.lang.String r13 = "The source file doesn't exist."
            r14 = 2
            if (r0 == 0) goto L_0x00c0
            r2 = 1
            kotlin.io.FilesKt__UtilsKt$copyRecursively$2 r5 = new kotlin.io.FilesKt__UtilsKt$copyRecursively$2     // Catch: TerminateException -> 0x00bf
            r5.<init>(r12)     // Catch: TerminateException -> 0x00bf
            kotlin.io.FileTreeWalk r6 = new kotlin.io.FileTreeWalk     // Catch: TerminateException -> 0x00bf
            r3 = 0
            r4 = 0
            r16 = 2147483647(0x7fffffff, float:NaN)
            r0 = r6
            r1 = r17
            r15 = r6
            r6 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6)     // Catch: TerminateException -> 0x00bf
            kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = new kotlin.io.FileTreeWalk$FileTreeWalkIterator     // Catch: TerminateException -> 0x00bf
            r0.<init>()     // Catch: TerminateException -> 0x00bf
        L_0x003a:
            boolean r1 = r0.hasNext()     // Catch: TerminateException -> 0x00bf
            if (r1 == 0) goto L_0x00be
            java.lang.Object r1 = r0.next()     // Catch: TerminateException -> 0x00bf
            java.io.File r1 = (java.io.File) r1     // Catch: TerminateException -> 0x00bf
            boolean r2 = r1.exists()     // Catch: TerminateException -> 0x00bf
            if (r2 == 0) goto L_0x00b5
            java.lang.String r2 = toRelativeString(r1, r7)     // Catch: TerminateException -> 0x00bf
            java.io.File r3 = new java.io.File     // Catch: TerminateException -> 0x00bf
            r4 = r18
            r3.<init>(r4, r2)     // Catch: TerminateException -> 0x00bf
            boolean r2 = r3.exists()     // Catch: TerminateException -> 0x00bf
            if (r2 == 0) goto L_0x0090
            boolean r2 = r1.isDirectory()     // Catch: TerminateException -> 0x00bf
            if (r2 == 0) goto L_0x0069
            boolean r2 = r3.isDirectory()     // Catch: TerminateException -> 0x00bf
            if (r2 != 0) goto L_0x0090
        L_0x0069:
            if (r9 != 0) goto L_0x006c
            goto L_0x007f
        L_0x006c:
            boolean r2 = r3.isDirectory()     // Catch: TerminateException -> 0x00bf
            if (r2 == 0) goto L_0x0079
            boolean r2 = deleteRecursively(r3)     // Catch: TerminateException -> 0x00bf
            if (r2 != 0) goto L_0x0081
            goto L_0x007f
        L_0x0079:
            boolean r2 = r3.delete()     // Catch: TerminateException -> 0x00bf
            if (r2 != 0) goto L_0x0081
        L_0x007f:
            r2 = 1
            goto L_0x0082
        L_0x0081:
            r2 = 0
        L_0x0082:
            if (r2 != 0) goto L_0x0085
            goto L_0x0090
        L_0x0085:
            kotlin.io.FileAlreadyExistsException r0 = new kotlin.io.FileAlreadyExistsException     // Catch: TerminateException -> 0x00bf
            java.lang.String r2 = "The destination file already exists."
            r0.<init>(r1, r3, r2)     // Catch: TerminateException -> 0x00bf
            java.util.Objects.requireNonNull(r12)     // Catch: TerminateException -> 0x00bf
            throw r0     // Catch: TerminateException -> 0x00bf
        L_0x0090:
            boolean r2 = r1.isDirectory()     // Catch: TerminateException -> 0x00bf
            if (r2 == 0) goto L_0x009a
            r3.mkdirs()     // Catch: TerminateException -> 0x00bf
            goto L_0x003a
        L_0x009a:
            copyTo$default(r1, r3, r9, r8, r10)     // Catch: TerminateException -> 0x00bf
            long r2 = r3.length()     // Catch: TerminateException -> 0x00bf
            long r5 = r1.length()     // Catch: TerminateException -> 0x00bf
            int r1 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x00aa
            goto L_0x003a
        L_0x00aa:
            java.io.IOException r0 = new java.io.IOException     // Catch: TerminateException -> 0x00bf
            java.lang.String r1 = "Source file wasn't copied completely, length of destination file differs."
            r0.<init>(r1)     // Catch: TerminateException -> 0x00bf
            java.util.Objects.requireNonNull(r12)     // Catch: TerminateException -> 0x00bf
            throw r0     // Catch: TerminateException -> 0x00bf
        L_0x00b5:
            kotlin.io.NoSuchFileException r0 = new kotlin.io.NoSuchFileException     // Catch: TerminateException -> 0x00bf
            r0.<init>(r1, r11, r13, r14)     // Catch: TerminateException -> 0x00bf
            java.util.Objects.requireNonNull(r12)     // Catch: TerminateException -> 0x00bf
            throw r0     // Catch: TerminateException -> 0x00bf
        L_0x00be:
            r8 = 1
        L_0x00bf:
            return r8
        L_0x00c0:
            kotlin.io.NoSuchFileException r0 = new kotlin.io.NoSuchFileException
            r0.<init>(r7, r11, r13, r14)
            java.util.Objects.requireNonNull(r12)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__UtilsKt.copyRecursively$default(java.io.File, java.io.File, boolean, kotlin.jvm.functions.Function2, int):boolean");
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable, java.io.File] */
    public static File copyTo$default(File file, File file2, boolean z, int i, int i2) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 8192;
        }
        th = 0;
        if (file.exists()) {
            if (file2.exists()) {
                if (!z) {
                    throw new FileAlreadyExistsException(file, file2, "The destination file already exists.");
                } else if (!file2.delete()) {
                    throw new FileAlreadyExistsException(file, file2, "Tried to overwrite the destination, but failed to delete it.");
                }
            }
            if (!file.isDirectory()) {
                File parentFile = file2.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                try {
                    InputKt.copyTo(new FileInputStream(file), new FileOutputStream(file2), i);
                } finally {
                    try {
                        throw th;
                    } finally {
                    }
                }
            } else if (!file2.mkdirs()) {
                throw new FileSystemException(file, file2, "Failed to create target directory.");
            }
            return file2;
        }
        throw new NoSuchFileException(file, th, "The source file doesn't exist.", 2);
    }

    public static final boolean deleteRecursively(File file) {
        FileTreeWalk.FileTreeWalkIterator fileTreeWalkIterator = new FileTreeWalk.FileTreeWalkIterator();
        while (true) {
            boolean z = true;
            while (fileTreeWalkIterator.hasNext()) {
                File next = fileTreeWalkIterator.next();
                if (next.delete() || !next.exists()) {
                    if (z) {
                        break;
                    }
                }
                z = false;
            }
            return z;
        }
    }

    public static final FilePathComponents normalize$FilesKt__UtilsKt(FilePathComponents filePathComponents) {
        File file = filePathComponents.root;
        List<File> list = filePathComponents.segments;
        ArrayList arrayList = new ArrayList(list.size());
        for (File file2 : list) {
            String name = file2.getName();
            if (name != null) {
                int hashCode = name.hashCode();
                if (hashCode != 46) {
                    if (hashCode == 1472 && name.equals("..")) {
                        if (arrayList.isEmpty() || !(!Intrinsics.areEqual(((File) ArraysKt___ArraysKt.last(arrayList)).getName(), ".."))) {
                            arrayList.add(file2);
                        } else {
                            arrayList.remove(arrayList.size() - 1);
                        }
                    }
                } else if (name.equals(".")) {
                }
            }
            arrayList.add(file2);
        }
        return new FilePathComponents(file, arrayList);
    }

    public static final List<String> readLines(Reader reader) {
        ArrayList arrayList = new ArrayList();
        th = null;
        try {
            Sequence<String> linesSequence = new LinesSequence(reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, 8192));
            if (!(linesSequence instanceof ConstrainedOnceSequence)) {
                linesSequence = new ConstrainedOnceSequence(linesSequence);
            }
            for (String str : linesSequence) {
                arrayList.add(str);
                Unit unit = Unit.INSTANCE;
            }
            Unit unit2 = Unit.INSTANCE;
            return arrayList;
        } finally {
            try {
                throw th;
            } finally {
            }
        }
    }

    public static final File resolve(File file, String str) {
        File file2;
        File file3 = new File(str);
        boolean z = true;
        if (InputKt.getRootLength$FilesKt__FilePathComponentsKt(file3.getPath()) > 0) {
            return file3;
        }
        String file4 = file.toString();
        if (file4.length() != 0) {
            z = false;
        }
        if (z || StringsKt__IndentKt.endsWith$default(file4, File.separatorChar, false, 2)) {
            file2 = new File(file4 + file3);
        } else {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13(file4);
            outline13.append(File.separatorChar);
            outline13.append(file3);
            file2 = new File(outline13.toString());
        }
        return file2;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0099 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String toRelativeString(java.io.File r15, java.io.File r16) {
        /*
            kotlin.io.FilePathComponents r0 = com.tidalab.v2board.clash.design.dialog.InputKt.toComponents(r15)
            kotlin.io.FilePathComponents r0 = normalize$FilesKt__UtilsKt(r0)
            kotlin.io.FilePathComponents r1 = com.tidalab.v2board.clash.design.dialog.InputKt.toComponents(r16)
            kotlin.io.FilePathComponents r1 = normalize$FilesKt__UtilsKt(r1)
            java.io.File r2 = r0.root
            java.io.File r3 = r1.root
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            r2 = r2 ^ 1
            if (r2 == 0) goto L_0x001d
            goto L_0x0066
        L_0x001d:
            java.util.List<java.io.File> r2 = r1.segments
            int r2 = r2.size()
            java.util.List<java.io.File> r3 = r0.segments
            int r3 = r3.size()
            r4 = 0
            int r5 = java.lang.Math.min(r3, r2)
        L_0x002e:
            if (r4 >= r5) goto L_0x0049
            java.util.List<java.io.File> r6 = r0.segments
            java.lang.Object r6 = r6.get(r4)
            java.io.File r6 = (java.io.File) r6
            java.util.List<java.io.File> r7 = r1.segments
            java.lang.Object r7 = r7.get(r4)
            java.io.File r7 = (java.io.File) r7
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r7)
            if (r6 == 0) goto L_0x0049
            int r4 = r4 + 1
            goto L_0x002e
        L_0x0049:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            int r6 = r2 + (-1)
            if (r6 < r4) goto L_0x0077
        L_0x0052:
            java.util.List<java.io.File> r7 = r1.segments
            java.lang.Object r7 = r7.get(r6)
            java.io.File r7 = (java.io.File) r7
            java.lang.String r7 = r7.getName()
            java.lang.String r8 = ".."
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r8)
            if (r7 == 0) goto L_0x0068
        L_0x0066:
            r0 = 0
            goto L_0x0097
        L_0x0068:
            r5.append(r8)
            if (r6 == r4) goto L_0x0072
            char r7 = java.io.File.separatorChar
            r5.append(r7)
        L_0x0072:
            if (r6 == r4) goto L_0x0077
            int r6 = r6 + (-1)
            goto L_0x0052
        L_0x0077:
            if (r4 >= r3) goto L_0x0093
            if (r4 >= r2) goto L_0x0080
            char r1 = java.io.File.separatorChar
            r5.append(r1)
        L_0x0080:
            java.util.List<java.io.File> r0 = r0.segments
            java.util.List r6 = kotlin.collections.ArraysKt___ArraysKt.drop(r0, r4)
            java.lang.String r8 = java.io.File.separator
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 124(0x7c, float:1.74E-43)
            r7 = r5
            kotlin.collections.ArraysKt___ArraysKt.joinTo$default(r6, r7, r8, r9, r10, r11, r12, r13, r14)
        L_0x0093:
            java.lang.String r0 = r5.toString()
        L_0x0097:
            if (r0 == 0) goto L_0x009a
            return r0
        L_0x009a:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "this and base files have different roots: "
            r1.append(r2)
            r2 = r15
            r1.append(r15)
            java.lang.String r2 = " and "
            r1.append(r2)
            r2 = r16
            r1.append(r2)
            r2 = 46
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__UtilsKt.toRelativeString(java.io.File, java.io.File):java.lang.String");
    }
}
