package com.k3rnl.fuse;

import com.k3rnl.fuse.api.FillDir;
import com.k3rnl.fuse.api.JavaFuseOperations;
import com.k3rnl.fuse.fuse.*;
import com.k3rnl.fuse.libc.*;
import com.oracle.svm.core.c.function.CEntryPointOptions;
import com.k3rnl.fuse.callbacks.FillDirFunction;
import org.graalvm.nativeimage.ObjectHandles;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;
import org.graalvm.nativeimage.c.type.VoidPointer;

public class NativeBridger {

    public static PrivateData getPrivateData() {
        return LibC.autoCast(FuseLibrary.fuse_get_context().private_data());
    }

    public static JavaFuseOperations getFuseOperations() {
        PrivateData privateData = getPrivateData();
        return ObjectHandles.getGlobal().get(privateData.javaData());
    }

    @CEntryPoint(name = "java_fuse_native_access")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int access(CCharPointer pathPointer, int mask) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().access(path, mask);
    }

    @CEntryPoint(name = "java_fuse_native_bmap")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int bmap(CCharPointer pathPointer, long blockSize, VoidPointer idx) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().bmap(path, blockSize, idx);
    }

    @CEntryPoint(name = "java_fuse_native_chmod")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int chmod(CCharPointer pathPointer, int mode, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().chmod(path, mode, fi);
    }

    @CEntryPoint(name = "java_fuse_native_chown")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int chown(CCharPointer pathPointer, int uid, int gid, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().chown(path, uid, gid, fi);
    }

    @CEntryPoint(name = "java_fuse_native_copy_file_range")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int copy_file_range(CCharPointer pathPointer, FuseFileInfo fiIn, long offIn, CCharPointer pathOutPointer, FuseFileInfo fiOut, long offOut, long len, int flags) {
        String pathIn = CTypeConversion.toJavaString(pathPointer);
        String pathOut = CTypeConversion.toJavaString(pathOutPointer);
        return getFuseOperations().copy_file_range(pathIn, fiIn, offIn, pathOut, fiOut, offOut, len, flags);
    }

    @CEntryPoint(name = "java_fuse_native_create")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int create(CCharPointer pathPointer, int mode, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().create(path, mode, fi);
    }

    @CEntryPoint(name = "java_fuse_native_destroy")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int destroy(VoidPointer privateData) {
        getFuseOperations().destroy(privateData);
        PrivateData privateDataStruct = LibC.autoCast(privateData);
        privateDataStruct.javaData();
        return 0;
    }

    @CEntryPoint(name = "java_fuse_native_fallocate")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int fallocate(CCharPointer pathPointer, int mode, long offset, long len, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().fallocate(path, mode, offset, len, fi);
    }

    @CEntryPoint(name = "java_fuse_native_flock")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int flock(CCharPointer pathPointer, FuseFileInfo fi, int op) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().flock(path, fi, op);
    }

    @CEntryPoint(name = "java_fuse_native_flush")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int flush(CCharPointer pathPointer, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().flush(path, fi);
    }

    @CEntryPoint(name = "java_fuse_native_getattr")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int getattr(CCharPointer pathPointer, FileStat stat, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().getattr(path, stat, fi);
    }

    @CEntryPoint(name = "java_fuse_native_init")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static VoidPointer init(VoidPointer conn, FuseConfig config) {
        PrivateData privateData = LibC.autoCast(FuseLibrary.fuse_get_context().private_data());
        JavaFuseOperations fuseOperations = ObjectHandles.getGlobal().get(privateData.javaData());
        fuseOperations.init(conn, config);

        return LibC.autoCast(privateData);
    }

    @CEntryPoint(name = "java_fuse_native_ioctl")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int ioctl(CCharPointer pathPointer, int cmd, VoidPointer arg, FuseFileInfo fi, int flags, VoidPointer data) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().ioctl(path, cmd, arg, fi, flags, data);
    }

    @CEntryPoint(name = "java_fuse_native_link")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int link(CCharPointer oldPathPointer, CCharPointer newPathPointer) {
        String oldPath = CTypeConversion.toJavaString(oldPathPointer);
        String newPath = CTypeConversion.toJavaString(newPathPointer);
        return getFuseOperations().link(oldPath, newPath);
    }


    @CEntryPoint(name = "java_fuse_native_lock")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int lock(CCharPointer pathPointer, FuseFileInfo fi, int cmd, Flock lock) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().lock(path, fi, cmd, lock);
    }

    @CEntryPoint(name = "java_fuse_native_lseek")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int lseek(CCharPointer pathPointer, long offset, int whence, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().lseek(path, offset, whence, fi);
    }

    @CEntryPoint(name = "java_fuse_native_mkdir")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int mkdir(CCharPointer pathPointer, int mode) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().mkdir(path, mode);
    }

    @CEntryPoint(name = "java_fuse_native_mknod")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int mknod(CCharPointer pathPointer, int mode, int dev) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().mknod(path, mode, dev);
    }

    @CEntryPoint(name = "java_fuse_native_opendir")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int opendir(CCharPointer pathPointer, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().opendir(path, fi);
    }

    @CEntryPoint(name = "java_fuse_native_open")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int open(CCharPointer pathPointer, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().open(path, fi);
    }

    @CEntryPoint(name = "java_fuse_native_poll")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int poll(CCharPointer pathPointer, FuseFileInfo fi, VoidPointer table, VoidPointer reventsp) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().poll(path, fi, table, reventsp);
    }

    @CEntryPoint(name = "java_fuse_native_read_buf")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int read_buf(CCharPointer pathPointer, FuseBufVec.FuseBufVecPointer buf, long size, long offset, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().read_buf(path, buf, size, offset, fi);
    }

    @CEntryPoint(name = "java_fuse_native_readdir")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int readdir(CCharPointer pathPointer, VoidPointer buf, FillDirFunction filter, long offset, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        // lambda not supported
        FillDir fillDir = new FillDir() {
            @Override
            public int apply(VoidPointer buf, String name, FileStat stat, long offset) {
                try (var cString = CTypeConversion.toCString(name)) {
                    return filter.invoke(buf, cString.get(), stat, offset);
                }
            }
        };

//                (bufPointer, name, stat, off) -> {
//            try (var cString = CTypeConversion.toCString(name)) {
//                return filter.invoke(bufPointer, cString.get(), stat, off);
//            }
//        };
        return getFuseOperations().readdir(path, buf, fillDir, offset, fi);
    }

    @CEntryPoint(name = "java_fuse_native_read")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int read(CCharPointer pathPointer, CCharPointer buf, long size, long offset, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        byte[] buffer = new byte[(int) size];
        int read = getFuseOperations().read(path, buffer, size, offset, fi);
        for (int i = 0; i < read; i++) {
            buf.write(i, buffer[i]);
        }
        return read;
    }

    @CEntryPoint(name = "java_fuse_native_readlink")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int readlink(CCharPointer pathPointer, CCharPointer buf, long size) {
        String path = CTypeConversion.toJavaString(pathPointer);
        byte[] buffer = new byte[(int) size];
        int code = getFuseOperations().readlink(path, buffer);
        for (int i = 0; i < size; i++) {
            buf.write(i, buffer[i]);
        }
        return code;
    }

    @CEntryPoint(name = "java_fuse_native_releasedir")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int releasedir(CCharPointer pathPointer, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().releasedir(path, fi);
    }

    @CEntryPoint(name = "java_fuse_native_release")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int release(CCharPointer pathPointer, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().release(path, fi);
    }

    @CEntryPoint(name = "java_fuse_native_rename")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int rename(CCharPointer oldPathPointer, CCharPointer newPathPointer, int flags) {
        String oldPath = CTypeConversion.toJavaString(oldPathPointer);
        String newPath = CTypeConversion.toJavaString(newPathPointer);
        return getFuseOperations().rename(oldPath, newPath, flags);
    }

    @CEntryPoint(name = "java_fuse_native_rmdir")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int rmdir(CCharPointer pathPointer) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().rmdir(path);
    }

    @CEntryPoint(name = "java_fuse_native_statfs")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int statfs(CCharPointer pathPointer, StatVFS stbuf) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().statfs(path, stbuf);
    }

    @CEntryPoint(name = "java_fuse_native_symlink")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int symlink(CCharPointer targetPointer, CCharPointer linkPointer) {
        String target = CTypeConversion.toJavaString(targetPointer);
        String link = CTypeConversion.toJavaString(linkPointer);
        return getFuseOperations().symlink(target, link);
    }

    @CEntryPoint(name = "java_fuse_native_truncate")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int truncate(CCharPointer pathPointer, long size, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().truncate(path, size, fi);
    }

    @CEntryPoint(name = "java_fuse_native_unlink")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int unlink(CCharPointer pathPointer) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().unlink(path);
    }

    @CEntryPoint(name = "java_fuse_native_utimens")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int utimens(CCharPointer pathPointer, TimeSpec timespec, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        TimeSpec[] timespecArray = new TimeSpec[]{timespec, timespec.addressOf(1)};
        return getFuseOperations().utimens(path, timespecArray, fi);
    }

    @CEntryPoint(name = "java_fuse_native_write_buf")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int write_buf(CCharPointer pathPointer, FuseBufVec buf, long offset, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        return getFuseOperations().write_buf(path, buf, offset, fi);
    }

    @CEntryPoint(name = "java_fuse_native_write")
    @CEntryPointOptions(prologue = AgentIsolate.FuseContextPrologue.class,
            epilogue = AgentIsolate.DetachEpilogue.class)
    public static int write(CCharPointer pathPointer, CCharPointer buf, long size, long offset, FuseFileInfo fi) {
        String path = CTypeConversion.toJavaString(pathPointer);
        byte[] buffer = new byte[(int) size];
        for (int i = 0; i < size; i++) {
            buffer[i] = buf.read(i);
        }
        return getFuseOperations().write(path, buffer, size, offset, fi);
    }

}
