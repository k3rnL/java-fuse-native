package com.k3rnl.fuse.fuse;

import com.k3rnl.fuse.callbacks.*;
import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

@CContext(FuseLibrary.Directives.class)
@CStruct(value = "fuse_operations", addStructKeyword = true)
public interface FuseOperations extends PointerBase {

    // Existing fields
    @CField("getattr")
    GetAttrFunction getattr();
    @CField("getattr")
    void getattr(GetAttrFunction func);

    @CField("readdir")
    ReadDirFunction readdir();
    @CField("readdir")
    void readdir(ReadDirFunction func);

    @CField("opendir")
    OpenDirFunction opendir();
    @CField("opendir")
    void opendir(OpenDirFunction func);

    @CField("releasedir")
    ReleaseDirFunction releasedir();
    @CField("releasedir")
    void releasedir(ReleaseDirFunction func);

    @CField("open")
    OpenFunction open();
    @CField("open")
    void open(OpenFunction func);

    @CField("read")
    ReadFunction read();
    @CField("read")
    void read(ReadFunction func);

    @CField("write")
    WriteFunction write();
    @CField("write")
    void write(WriteFunction func);

    @CField("create")
    CreateFunction create();
    @CField("create")
    void create(CreateFunction func);

    @CField("release")
    ReleaseFunction release();
    @CField("release")
    void release(ReleaseFunction func);

    @CField("rmdir")
    RmdirFunction rmdir();
    @CField("rmdir")
    void rmdir(RmdirFunction func);

    @CField("unlink")
    UnlinkFunction unlink();
    @CField("unlink")
    void unlink(UnlinkFunction func);

    @CField("init")
    InitFunction init();
    @CField("init")
    void init(InitFunction func);

    // New fields based on additional CEntryPointLiterals
    @CField("access")
    AccessFunction access();
    @CField("access")
    void access(AccessFunction func);

    @CField("bmap")
    BmapFunction bmap();
    @CField("bmap")
    void bmap(BmapFunction func);

    @CField("chmod")
    ChmodFunction chmod();
    @CField("chmod")
    void chmod(ChmodFunction func);

    @CField("chown")
    ChownFunction chown();
    @CField("chown")
    void chown(ChownFunction func);

    @CField("copy_file_range")
    CopyFileRangeFunction copyFileRange();
    @CField("copy_file_range")
    void copyFileRange(CopyFileRangeFunction func);

    @CField("destroy")
    DestroyFunction destroy();
    @CField("destroy")
    void destroy(DestroyFunction func);

    @CField("fallocate")
    FallocateFunction fallocate();
    @CField("fallocate")
    void fallocate(FallocateFunction func);

    @CField("flock")
    FlockFunction flock();
    @CField("flock")
    void flock(FlockFunction func);

    @CField("flush")
    FlushFunction flush();
    @CField("flush")
    void flush(FlushFunction func);

    @CField("ioctl")
    IoctlFunction ioctl();
    @CField("ioctl")
    void ioctl(IoctlFunction func);

    @CField("link")
    LinkFunction link();
    @CField("link")
    void link(LinkFunction func);

    @CField("lock")
    LockFunction lock();
    @CField("lock")
    void lock(LockFunction func);

    @CField("lseek")
    LseekFunction lseek();
    @CField("lseek")
    void lseek(LseekFunction func);

    @CField("mkdir")
    MkDirFunction mkdir();
    @CField("mkdir")
    void mkdir(MkDirFunction func);

    @CField("mknod")
    MkNodFunction mknod();
    @CField("mknod")
    void mknod(MkNodFunction func);

    @CField("poll")
    PollFunction poll();
    @CField("poll")
    void poll(PollFunction func);

    @CField("read_buf")
    ReadBufFunction readBuf();
    @CField("read_buf")
    void readBuf(ReadBufFunction func);

    @CField("readlink")
    ReadLinkFunction readlink();
    @CField("readlink")
    void readlink(ReadLinkFunction func);

    @CField("rename")
    RenameFunction rename();
    @CField("rename")
    void rename(RenameFunction func);

    @CField("statfs")
    StatVfsFunction statfs();
    @CField("statfs")
    void statfs(StatVfsFunction func);

    @CField("symlink")
    SymlinkFunction symlink();
    @CField("symlink")
    void symlink(SymlinkFunction func);

    @CField("truncate")
    TruncateFunction truncate();
    @CField("truncate")
    void truncate(TruncateFunction func);

    @CField("utimens")
    UtimensFunction utimens();
    @CField("utimens")
    void utimens(UtimensFunction func);

    @CField("write_buf")
    WriteBufFunction writeBuf();
    @CField("write_buf")
    void writeBuf(WriteBufFunction func);
}
