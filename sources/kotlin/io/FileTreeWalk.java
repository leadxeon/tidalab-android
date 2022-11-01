package kotlin.io;

import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
/* compiled from: FileTreeWalk.kt */
/* loaded from: classes.dex */
public final class FileTreeWalk implements Sequence<File> {
    public final int direction;
    public final int maxDepth;
    public final Function1<File, Boolean> onEnter;
    public final Function2<File, IOException, Unit> onFail;
    public final Function1<File, Unit> onLeave;
    public final File start;

    /* compiled from: FileTreeWalk.kt */
    /* loaded from: classes.dex */
    public static abstract class DirectoryState extends WalkState {
        public DirectoryState(File file) {
            super(file);
        }
    }

    /* compiled from: FileTreeWalk.kt */
    /* loaded from: classes.dex */
    public final class FileTreeWalkIterator extends AbstractIterator<File> {
        public final ArrayDeque<WalkState> state;

        /* compiled from: FileTreeWalk.kt */
        /* loaded from: classes.dex */
        public final class BottomUpDirectoryState extends DirectoryState {
            public boolean failed;
            public int fileIndex;
            public File[] fileList;
            public boolean rootVisited;

            public BottomUpDirectoryState(File file) {
                super(file);
            }

            @Override // kotlin.io.FileTreeWalk.WalkState
            public File step() {
                int i;
                if (!this.failed && this.fileList == null) {
                    Function1<File, Boolean> function1 = FileTreeWalk.this.onEnter;
                    if (function1 != null && !function1.invoke(this.root).booleanValue()) {
                        return null;
                    }
                    File[] listFiles = this.root.listFiles();
                    this.fileList = listFiles;
                    if (listFiles == null) {
                        Function2<File, IOException, Unit> function2 = FileTreeWalk.this.onFail;
                        if (function2 != null) {
                            function2.invoke(this.root, new AccessDeniedException(this.root, null, "Cannot list files in a directory", 2));
                        }
                        this.failed = true;
                    }
                }
                File[] fileArr = this.fileList;
                if (fileArr != null && (i = this.fileIndex) < fileArr.length) {
                    this.fileIndex = i + 1;
                    return fileArr[i];
                } else if (!this.rootVisited) {
                    this.rootVisited = true;
                    return this.root;
                } else {
                    Function1<File, Unit> function12 = FileTreeWalk.this.onLeave;
                    if (function12 != null) {
                        function12.invoke(this.root);
                    }
                    return null;
                }
            }
        }

        /* compiled from: FileTreeWalk.kt */
        /* loaded from: classes.dex */
        public final class SingleFileState extends WalkState {
            public boolean visited;

            public SingleFileState(FileTreeWalkIterator fileTreeWalkIterator, File file) {
                super(file);
            }

            @Override // kotlin.io.FileTreeWalk.WalkState
            public File step() {
                if (this.visited) {
                    return null;
                }
                this.visited = true;
                return this.root;
            }
        }

        /* compiled from: FileTreeWalk.kt */
        /* loaded from: classes.dex */
        public final class TopDownDirectoryState extends DirectoryState {
            public int fileIndex;
            public File[] fileList;
            public boolean rootVisited;

            public TopDownDirectoryState(File file) {
                super(file);
            }

            @Override // kotlin.io.FileTreeWalk.WalkState
            public File step() {
                Function2<File, IOException, Unit> function2;
                if (!this.rootVisited) {
                    Function1<File, Boolean> function1 = FileTreeWalk.this.onEnter;
                    if (function1 != null && !function1.invoke(this.root).booleanValue()) {
                        return null;
                    }
                    this.rootVisited = true;
                    return this.root;
                }
                File[] fileArr = this.fileList;
                if (fileArr == null || this.fileIndex < fileArr.length) {
                    if (fileArr == null) {
                        File[] listFiles = this.root.listFiles();
                        this.fileList = listFiles;
                        if (listFiles == null && (function2 = FileTreeWalk.this.onFail) != null) {
                            function2.invoke(this.root, new AccessDeniedException(this.root, null, "Cannot list files in a directory", 2));
                        }
                        File[] fileArr2 = this.fileList;
                        if (fileArr2 == null || fileArr2.length == 0) {
                            Function1<File, Unit> function12 = FileTreeWalk.this.onLeave;
                            if (function12 != null) {
                                function12.invoke(this.root);
                            }
                            return null;
                        }
                    }
                    File[] fileArr3 = this.fileList;
                    int i = this.fileIndex;
                    this.fileIndex = i + 1;
                    return fileArr3[i];
                }
                Function1<File, Unit> function13 = FileTreeWalk.this.onLeave;
                if (function13 != null) {
                    function13.invoke(this.root);
                }
                return null;
            }
        }

        public FileTreeWalkIterator() {
            ArrayDeque<WalkState> arrayDeque = new ArrayDeque<>();
            this.state = arrayDeque;
            if (FileTreeWalk.this.start.isDirectory()) {
                arrayDeque.push(directoryState(FileTreeWalk.this.start));
            } else if (FileTreeWalk.this.start.isFile()) {
                arrayDeque.push(new SingleFileState(this, FileTreeWalk.this.start));
            } else {
                super.state = 3;
            }
        }

        public final DirectoryState directoryState(File file) {
            int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(FileTreeWalk.this.direction);
            if ($enumboxing$ordinal == 0) {
                return new TopDownDirectoryState(file);
            }
            if ($enumboxing$ordinal == 1) {
                return new BottomUpDirectoryState(file);
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    /* compiled from: FileTreeWalk.kt */
    /* loaded from: classes.dex */
    public static abstract class WalkState {
        public final File root;

        public WalkState(File file) {
            this.root = file;
        }

        public abstract File step();
    }

    public FileTreeWalk(File file, int i) {
        this.start = file;
        this.direction = i;
        this.onEnter = null;
        this.onLeave = null;
        this.onFail = null;
        this.maxDepth = Integer.MAX_VALUE;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<File> iterator() {
        return new FileTreeWalkIterator();
    }

    /* JADX WARN: Incorrect types in method signature: (Ljava/io/File;Ljava/lang/Object;Lkotlin/jvm/functions/Function1<-Ljava/io/File;Ljava/lang/Boolean;>;Lkotlin/jvm/functions/Function1<-Ljava/io/File;Lkotlin/Unit;>;Lkotlin/jvm/functions/Function2<-Ljava/io/File;-Ljava/io/IOException;Lkotlin/Unit;>;I)V */
    public FileTreeWalk(File file, int i, Function1 function1, Function1 function12, Function2 function2, int i2) {
        this.start = file;
        this.direction = i;
        this.onEnter = function1;
        this.onLeave = function12;
        this.onFail = function2;
        this.maxDepth = i2;
    }
}
