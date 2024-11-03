package com.k3rnl.fuse;

import com.k3rnl.fuse.api.JavaFuseOperations;
import com.k3rnl.fuse.callbacks.*;
import com.k3rnl.fuse.fuse.*;
import com.k3rnl.fuse.libc.*;
import org.graalvm.nativeimage.*;
import org.graalvm.nativeimage.c.function.CEntryPointLiteral;
import org.graalvm.nativeimage.c.struct.SizeOf;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.VoidPointer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FuseNative {

    final JavaFuseOperations fuseOps;

    public FuseNative(JavaFuseOperations fuseOps) {
        this.fuseOps = fuseOps;
    }

    public void run(String mountingPoint, boolean debug, List<String> fuseArgs) throws IOException {
        FuseOperations nativeOps = StackValue.get(FuseOperations.class);

        LibC.memset(LibC.autoCast(nativeOps), 0, SizeOf.get(FuseOperations.class));

        if (fuseOps.isImplemented("access")) nativeOps.access(access.getFunctionPointer());
        if (fuseOps.isImplemented("bmap")) nativeOps.bmap(bmap.getFunctionPointer());
        if (fuseOps.isImplemented("chmod")) nativeOps.chmod(chmod.getFunctionPointer());
        if (fuseOps.isImplemented("chown")) nativeOps.chown(chown.getFunctionPointer());
        if (fuseOps.isImplemented("copy_file_range")) nativeOps.copyFileRange(copy_file_range.getFunctionPointer());
        if (fuseOps.isImplemented("create")) nativeOps.create(create.getFunctionPointer());
        if (fuseOps.isImplemented("destroy")) nativeOps.destroy(destroy.getFunctionPointer());
        if (fuseOps.isImplemented("fallocate")) nativeOps.fallocate(fallocate.getFunctionPointer());
        if (fuseOps.isImplemented("flock")) nativeOps.flock(flock.getFunctionPointer());
        if (fuseOps.isImplemented("flush")) nativeOps.flush(flush.getFunctionPointer());
        if (fuseOps.isImplemented("getattr")) nativeOps.getattr(getattr.getFunctionPointer());
        if (fuseOps.isImplemented("init")) nativeOps.init(init.getFunctionPointer());
        if (fuseOps.isImplemented("ioctl")) nativeOps.ioctl(ioctl.getFunctionPointer());
        if (fuseOps.isImplemented("link")) nativeOps.link(link.getFunctionPointer());
        if (fuseOps.isImplemented("lock")) nativeOps.lock(lock.getFunctionPointer());
        if (fuseOps.isImplemented("lseek")) nativeOps.lseek(lseek.getFunctionPointer());
        if (fuseOps.isImplemented("mkdir")) nativeOps.mkdir(mkdir.getFunctionPointer());
        if (fuseOps.isImplemented("mknod")) nativeOps.mknod(mknod.getFunctionPointer());
        if (fuseOps.isImplemented("opendir")) nativeOps.opendir(opendir.getFunctionPointer());
        if (fuseOps.isImplemented("open")) nativeOps.open(open.getFunctionPointer());
        if (fuseOps.isImplemented("poll")) nativeOps.poll(poll.getFunctionPointer());
        if (fuseOps.isImplemented("read_buf")) nativeOps.readBuf(read_buf.getFunctionPointer());
        if (fuseOps.isImplemented("readdir")) nativeOps.readdir(readdir.getFunctionPointer());
        if (fuseOps.isImplemented("read")) nativeOps.read(read.getFunctionPointer());
        if (fuseOps.isImplemented("readlink")) nativeOps.readlink(readlink.getFunctionPointer());
        if (fuseOps.isImplemented("releasedir")) nativeOps.releasedir(releasedir.getFunctionPointer());
        if (fuseOps.isImplemented("release")) nativeOps.release(release.getFunctionPointer());
        if (fuseOps.isImplemented("rename")) nativeOps.rename(rename.getFunctionPointer());
        if (fuseOps.isImplemented("rmdir")) nativeOps.rmdir(rmdir.getFunctionPointer());
        if (fuseOps.isImplemented("statfs")) nativeOps.statfs(statfs.getFunctionPointer());
        if (fuseOps.isImplemented("symlink")) nativeOps.symlink(symlink.getFunctionPointer());
        if (fuseOps.isImplemented("truncate")) nativeOps.truncate(truncate.getFunctionPointer());
        if (fuseOps.isImplemented("unlink")) nativeOps.unlink(unlink.getFunctionPointer());
        if (fuseOps.isImplemented("utimens")) nativeOps.utimens(utimens.getFunctionPointer());
        if (fuseOps.isImplemented("write_buf")) nativeOps.writeBuf(write_buf.getFunctionPointer());
        if (fuseOps.isImplemented("write")) nativeOps.write(write.getFunctionPointer());

        List<String> allFuseArgs = new ArrayList<>();
        allFuseArgs.add("programName");
        allFuseArgs.addAll(fuseArgs);
        if (debug) allFuseArgs.add("-d");
        allFuseArgs.add("-f");
        allFuseArgs.add(mountingPoint);

        ObjectHandle objectHandle = ObjectHandles.getGlobal().create(fuseOps);
        var isolate = CurrentIsolate.getIsolate();

        PrivateData privateData = StackValue.get(PrivateData.class);
        privateData.javaData(objectHandle);
        privateData.isolate(LibC.autoCast(isolate));


        FuseLibrary.fuseMain(allFuseArgs, nativeOps, LibC.autoCast(privateData));

        System.out.println("fuseMain Ended");

    }

    static final CEntryPointLiteral<AccessFunction> access = CEntryPointLiteral.create(NativeBridger.class, "access", CCharPointer.class, int.class);
    static final CEntryPointLiteral<BmapFunction> bmap = CEntryPointLiteral.create(NativeBridger.class, "bmap", CCharPointer.class, long.class, VoidPointer.class);
    static final CEntryPointLiteral<ChmodFunction> chmod = CEntryPointLiteral.create(NativeBridger.class, "chmod", CCharPointer.class, int.class, FuseFileInfo.class);
    static final CEntryPointLiteral<ChownFunction> chown = CEntryPointLiteral.create(NativeBridger.class, "chown", CCharPointer.class, int.class, int.class, FuseFileInfo.class);
    static final CEntryPointLiteral<CopyFileRangeFunction> copy_file_range = CEntryPointLiteral.create(NativeBridger.class, "copy_file_range", CCharPointer.class, FuseFileInfo.class, long.class, CCharPointer.class, FuseFileInfo.class, long.class, long.class, int.class);
    static final CEntryPointLiteral<CreateFunction> create = CEntryPointLiteral.create(NativeBridger.class, "create", CCharPointer.class, int.class, FuseFileInfo.class);
    static final CEntryPointLiteral<DestroyFunction> destroy = CEntryPointLiteral.create(NativeBridger.class, "destroy", VoidPointer.class);
    static final CEntryPointLiteral<FallocateFunction> fallocate = CEntryPointLiteral.create(NativeBridger.class, "fallocate", CCharPointer.class, int.class, long.class, long.class, FuseFileInfo.class);
    static final CEntryPointLiteral<FlockFunction> flock = CEntryPointLiteral.create(NativeBridger.class, "flock", CCharPointer.class, FuseFileInfo.class, int.class);
    static final CEntryPointLiteral<FlushFunction> flush = CEntryPointLiteral.create(NativeBridger.class, "flush", CCharPointer.class, FuseFileInfo.class);
    static final CEntryPointLiteral<GetAttrFunction> getattr = CEntryPointLiteral.create(NativeBridger.class, "getattr", CCharPointer.class, FileStat.class, FuseFileInfo.class);
    static final CEntryPointLiteral<InitFunction> init = CEntryPointLiteral.create(NativeBridger.class, "init", VoidPointer.class, FuseConfig.class);
    // xattr ops not implemented
    static final CEntryPointLiteral<IoctlFunction> ioctl = CEntryPointLiteral.create(NativeBridger.class, "ioctl", CCharPointer.class, int.class, VoidPointer.class, FuseFileInfo.class, int.class, VoidPointer.class);
    static final CEntryPointLiteral<LinkFunction> link = CEntryPointLiteral.create(NativeBridger.class, "link", CCharPointer.class, CCharPointer.class);
    static final CEntryPointLiteral<LockFunction> lock = CEntryPointLiteral.create(NativeBridger.class, "lock", CCharPointer.class, FuseFileInfo.class, int.class, Flock.class);
    static final CEntryPointLiteral<LseekFunction> lseek = CEntryPointLiteral.create(NativeBridger.class, "lseek", CCharPointer.class, long.class, int.class, FuseFileInfo.class);
    static final CEntryPointLiteral<MkDirFunction> mkdir = CEntryPointLiteral.create(NativeBridger.class, "mkdir", CCharPointer.class, int.class);
    static final CEntryPointLiteral<MkNodFunction> mknod = CEntryPointLiteral.create(NativeBridger.class, "mknod", CCharPointer.class, int.class, int.class);
    static final CEntryPointLiteral<OpenDirFunction> opendir = CEntryPointLiteral.create(NativeBridger.class, "opendir", CCharPointer.class, FuseFileInfo.class);
    static final CEntryPointLiteral<OpenFunction> open = CEntryPointLiteral.create(NativeBridger.class, "open", CCharPointer.class, FuseFileInfo.class);
    static final CEntryPointLiteral<PollFunction> poll = CEntryPointLiteral.create(NativeBridger.class, "poll", CCharPointer.class, FuseFileInfo.class, VoidPointer.class, VoidPointer.class);
    static final CEntryPointLiteral<ReadBufFunction> read_buf = CEntryPointLiteral.create(NativeBridger.class, "read_buf", CCharPointer.class, FuseBufVec.FuseBufVecPointer.class, long.class, long.class, FuseFileInfo.class);
    static final CEntryPointLiteral<ReadDirFunction> readdir = CEntryPointLiteral.create(NativeBridger.class, "readdir", CCharPointer.class, VoidPointer.class, FillDirFunction.class, long.class, FuseFileInfo.class);
    static final CEntryPointLiteral<ReadFunction> read = CEntryPointLiteral.create(NativeBridger.class, "read", CCharPointer.class, CCharPointer.class, long.class, long.class, FuseFileInfo.class);
    static final CEntryPointLiteral<ReadLinkFunction> readlink = CEntryPointLiteral.create(NativeBridger.class, "readlink", CCharPointer.class, CCharPointer.class, long.class);
    static final CEntryPointLiteral<ReleaseDirFunction> releasedir = CEntryPointLiteral.create(NativeBridger.class, "releasedir", CCharPointer.class, FuseFileInfo.class);
    static final CEntryPointLiteral<ReleaseFunction> release = CEntryPointLiteral.create(NativeBridger.class, "release", CCharPointer.class, FuseFileInfo.class);
    static final CEntryPointLiteral<RenameFunction> rename = CEntryPointLiteral.create(NativeBridger.class, "rename", CCharPointer.class, CCharPointer.class, int.class);
    static final CEntryPointLiteral<RmdirFunction> rmdir = CEntryPointLiteral.create(NativeBridger.class, "rmdir", CCharPointer.class);
    static final CEntryPointLiteral<StatVfsFunction> statfs = CEntryPointLiteral.create(NativeBridger.class, "statfs", CCharPointer.class, StatVFS.class);
    static final CEntryPointLiteral<SymlinkFunction> symlink = CEntryPointLiteral.create(NativeBridger.class, "symlink", CCharPointer.class, CCharPointer.class);
    static final CEntryPointLiteral<TruncateFunction> truncate = CEntryPointLiteral.create(NativeBridger.class, "truncate", CCharPointer.class, long.class, FuseFileInfo.class);
    static final CEntryPointLiteral<UnlinkFunction> unlink = CEntryPointLiteral.create(NativeBridger.class, "unlink", CCharPointer.class);
    static final CEntryPointLiteral<UtimensFunction> utimens = CEntryPointLiteral.create(NativeBridger.class, "utimens", CCharPointer.class, TimeSpec.class, FuseFileInfo.class);
    static final CEntryPointLiteral<WriteBufFunction> write_buf = CEntryPointLiteral.create(NativeBridger.class, "write_buf", CCharPointer.class, FuseBufVec.class, long.class, FuseFileInfo.class);
    static final CEntryPointLiteral<WriteFunction> write = CEntryPointLiteral.create(NativeBridger.class, "write", CCharPointer.class, CCharPointer.class, long.class, long.class, FuseFileInfo.class);


}