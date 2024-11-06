package com.k3rnl.fuse.fuse;

import com.k3rnl.fuse.callbacks.*;
import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

/**
 * The {@code FuseOperations} interface represents the native "fuse_operations" structure,
 * which defines the set of callback functions that implement the behavior of the
 * FUSE (Filesystem in Userspace) filesystem. Each method corresponds to a specific
 * filesystem operation, allowing the filesystem to handle various requests from the
 * operating system.
 */
@CContext(FuseLibrary.Directives.class)
@CStruct(value = "fuse_operations", addStructKeyword = true)
public interface FuseOperations extends PointerBase {

    /**
     * Retrieves the callback function for the {@code getattr} operation,
     * which is used to get file attributes.
     *
     * @return the {@link GetAttrFunction} callback.
     */
    @CField("getattr")
    GetAttrFunction getattr();

    /**
     * Sets the callback function for the {@code getattr} operation.
     *
     * @param func the {@link GetAttrFunction} to handle {@code getattr} requests.
     */
    @CField("getattr")
    void getattr(GetAttrFunction func);

    /**
     * Retrieves the callback function for the {@code readdir} operation,
     * which is used to read directory entries.
     *
     * @return the {@link ReadDirFunction} callback.
     */
    @CField("readdir")
    ReadDirFunction readdir();

    /**
     * Sets the callback function for the {@code readdir} operation.
     *
     * @param func the {@link ReadDirFunction} to handle {@code readdir} requests.
     */
    @CField("readdir")
    void readdir(ReadDirFunction func);

    /**
     * Retrieves the callback function for the {@code opendir} operation,
     * which is used to open a directory.
     *
     * @return the {@link OpenDirFunction} callback.
     */
    @CField("opendir")
    OpenDirFunction opendir();

    /**
     * Sets the callback function for the {@code opendir} operation.
     *
     * @param func the {@link OpenDirFunction} to handle {@code opendir} requests.
     */
    @CField("opendir")
    void opendir(OpenDirFunction func);

    /**
     * Retrieves the callback function for the {@code releasedir} operation,
     * which is used to release (close) a directory.
     *
     * @return the {@link ReleaseDirFunction} callback.
     */
    @CField("releasedir")
    ReleaseDirFunction releasedir();

    /**
     * Sets the callback function for the {@code releasedir} operation.
     *
     * @param func the {@link ReleaseDirFunction} to handle {@code releasedir} requests.
     */
    @CField("releasedir")
    void releasedir(ReleaseDirFunction func);

    /**
     * Retrieves the callback function for the {@code open} operation,
     * which is used to open a file.
     *
     * @return the {@link OpenFunction} callback.
     */
    @CField("open")
    OpenFunction open();

    /**
     * Sets the callback function for the {@code open} operation.
     *
     * @param func the {@link OpenFunction} to handle {@code open} requests.
     */
    @CField("open")
    void open(OpenFunction func);

    /**
     * Retrieves the callback function for the {@code read} operation,
     * which is used to read data from a file.
     *
     * @return the {@link ReadFunction} callback.
     */
    @CField("read")
    ReadFunction read();

    /**
     * Sets the callback function for the {@code read} operation.
     *
     * @param func the {@link ReadFunction} to handle {@code read} requests.
     */
    @CField("read")
    void read(ReadFunction func);

    /**
     * Retrieves the callback function for the {@code write} operation,
     * which is used to write data to a file.
     *
     * @return the {@link WriteFunction} callback.
     */
    @CField("write")
    WriteFunction write();

    /**
     * Sets the callback function for the {@code write} operation.
     *
     * @param func the {@link WriteFunction} to handle {@code write} requests.
     */
    @CField("write")
    void write(WriteFunction func);

    /**
     * Retrieves the callback function for the {@code create} operation,
     * which is used to create a new file.
     *
     * @return the {@link CreateFunction} callback.
     */
    @CField("create")
    CreateFunction create();

    /**
     * Sets the callback function for the {@code create} operation.
     *
     * @param func the {@link CreateFunction} to handle {@code create} requests.
     */
    @CField("create")
    void create(CreateFunction func);

    /**
     * Retrieves the callback function for the {@code release} operation,
     * which is used to release (close) a file.
     *
     * @return the {@link ReleaseFunction} callback.
     */
    @CField("release")
    ReleaseFunction release();

    /**
     * Sets the callback function for the {@code release} operation.
     *
     * @param func the {@link ReleaseFunction} to handle {@code release} requests.
     */
    @CField("release")
    void release(ReleaseFunction func);

    /**
     * Retrieves the callback function for the {@code rmdir} operation,
     * which is used to remove a directory.
     *
     * @return the {@link RmdirFunction} callback.
     */
    @CField("rmdir")
    RmdirFunction rmdir();

    /**
     * Sets the callback function for the {@code rmdir} operation.
     *
     * @param func the {@link RmdirFunction} to handle {@code rmdir} requests.
     */
    @CField("rmdir")
    void rmdir(RmdirFunction func);

    /**
     * Retrieves the callback function for the {@code unlink} operation,
     * which is used to remove a file.
     *
     * @return the {@link UnlinkFunction} callback.
     */
    @CField("unlink")
    UnlinkFunction unlink();

    /**
     * Sets the callback function for the {@code unlink} operation.
     *
     * @param func the {@link UnlinkFunction} to handle {@code unlink} requests.
     */
    @CField("unlink")
    void unlink(UnlinkFunction func);

    /**
     * Retrieves the callback function for the {@code init} operation,
     * which is used to initialize the filesystem.
     *
     * @return the {@link InitFunction} callback.
     */
    @CField("init")
    InitFunction init();

    /**
     * Sets the callback function for the {@code init} operation.
     *
     * @param func the {@link InitFunction} to handle {@code init} requests.
     */
    @CField("init")
    void init(InitFunction func);

    /**
     * Sets the callback function for the {@code setxattr} operation.
     *
     * @param func the {@link SetXAttrFunction} to handle {@code setxattr} requests.
     */
    @CField("setxattr")
    void setxattr(SetXAttrFunction func);

    /**
     * Retrieves the callback function for the {@code setxattr} operation,
     * which is used to set an extended attribute.
     *
     * @return the {@link SetXAttrFunction} callback.
     */
    @CField("setxattr")
    SetXAttrFunction setxattr();

    /**
     * Retrieves the callback function for the {@code getxattr} operation,
     * which is used to get an extended attribute.
     *
     * @return the {@link GetXAttrFunction} callback.
     */
    @CField("getxattr")
    GetXAttrFunction getxattr();

    /**
     * Sets the callback function for the {@code getxattr} operation.
     *
     * @param func the {@link GetXAttrFunction} to handle {@code getxattr} requests.
     */
    @CField("getxattr")
    void getxattr(GetXAttrFunction func);

    /**
     * Retrieves the callback function for the {@code listxattr} operation,
     * which is used to list extended attributes.
     *
     * @return the {@link ListXAttrFunction} callback.
     */
    @CField("listxattr")
    ListXAttrFunction listxattr();

    /**
     * Sets the callback function for the {@code listxattr} operation.
     *
     * @param func the {@link ListXAttrFunction} to handle {@code listxattr} requests.
     */
    @CField("listxattr")
    void listxattr(ListXAttrFunction func);

    /**
     * Retrieves the callback function for the {@code removexattr} operation,
     * which is used to remove an extended attribute.
     *
     * @return the {@link RemoveXAttrFunction} callback.
     */
    @CField("removexattr")
    RemoveXAttrFunction removexattr();

    /**
     * Sets the callback function for the {@code removexattr} operation.
     *
     * @param func the {@link RemoveXAttrFunction} to handle {@code removexattr} requests.
     */
    @CField("removexattr")
    void removexattr(RemoveXAttrFunction func);

    /**
     * Retrieves the callback function for the {@code access} operation,
     * which is used to check file access permissions.
     *
     * @return the {@link AccessFunction} callback.
     */
    @CField("access")
    AccessFunction access();

    /**
     * Sets the callback function for the {@code access} operation.
     *
     * @param func the {@link AccessFunction} to handle {@code access} requests.
     */
    @CField("access")
    void access(AccessFunction func);

    /**
     * Retrieves the callback function for the {@code bmap} operation,
     * which is used to map block numbers.
     *
     * @return the {@link BmapFunction} callback.
     */
    @CField("bmap")
    BmapFunction bmap();

    /**
     * Sets the callback function for the {@code bmap} operation.
     *
     * @param func the {@link BmapFunction} to handle {@code bmap} requests.
     */
    @CField("bmap")
    void bmap(BmapFunction func);

    /**
     * Retrieves the callback function for the {@code chmod} operation,
     * which is used to change file permissions.
     *
     * @return the {@link ChmodFunction} callback.
     */
    @CField("chmod")
    ChmodFunction chmod();

    /**
     * Sets the callback function for the {@code chmod} operation.
     *
     * @param func the {@link ChmodFunction} to handle {@code chmod} requests.
     */
    @CField("chmod")
    void chmod(ChmodFunction func);

    /**
     * Retrieves the callback function for the {@code chown} operation,
     * which is used to change file ownership.
     *
     * @return the {@link ChownFunction} callback.
     */
    @CField("chown")
    ChownFunction chown();

    /**
     * Sets the callback function for the {@code chown} operation.
     *
     * @param func the {@link ChownFunction} to handle {@code chown} requests.
     */
    @CField("chown")
    void chown(ChownFunction func);

    /**
     * Retrieves the callback function for the {@code copy_file_range} operation,
     * which is used to copy a range of data from one file to another.
     *
     * @return the {@link CopyFileRangeFunction} callback.
     */
    @CField("copy_file_range")
    CopyFileRangeFunction copyFileRange();

    /**
     * Sets the callback function for the {@code copy_file_range} operation.
     *
     * @param func the {@link CopyFileRangeFunction} to handle {@code copy_file_range} requests.
     */
    @CField("copy_file_range")
    void copyFileRange(CopyFileRangeFunction func);

    /**
     * Retrieves the callback function for the {@code destroy} operation,
     * which is used to clean up and unmount the filesystem.
     *
     * @return the {@link DestroyFunction} callback.
     */
    @CField("destroy")
    DestroyFunction destroy();

    /**
     * Sets the callback function for the {@code destroy} operation.
     *
     * @param func the {@link DestroyFunction} to handle {@code destroy} requests.
     */
    @CField("destroy")
    void destroy(DestroyFunction func);

    /**
     * Retrieves the callback function for the {@code fallocate} operation,
     * which is used to allocate space for a file.
     *
     * @return the {@link FallocateFunction} callback.
     */
    @CField("fallocate")
    FallocateFunction fallocate();

    /**
     * Sets the callback function for the {@code fallocate} operation.
     *
     * @param func the {@link FallocateFunction} to handle {@code fallocate} requests.
     */
    @CField("fallocate")
    void fallocate(FallocateFunction func);

    /**
     * Retrieves the callback function for the {@code flock} operation,
     * which is used to apply or remove an advisory lock on an open file.
     *
     * @return the {@link FlockFunction} callback.
     */
    @CField("flock")
    FlockFunction flock();

    /**
     * Sets the callback function for the {@code flock} operation.
     *
     * @param func the {@link FlockFunction} to handle {@code flock} requests.
     */
    @CField("flock")
    void flock(FlockFunction func);

    /**
     * Retrieves the callback function for the {@code flush} operation,
     * which is used to flush any cached data for an open file to disk.
     *
     * @return the {@link FlushFunction} callback.
     */
    @CField("flush")
    FlushFunction flush();

    /**
     * Sets the callback function for the {@code flush} operation.
     *
     * @param func the {@link FlushFunction} to handle {@code flush} requests.
     */
    @CField("flush")
    void flush(FlushFunction func);

    /**
     * Retrieves the callback function for the {@code ioctl} operation,
     * which is used to perform device-specific input/output operations.
     *
     * @return the {@link IoctlFunction} callback.
     */
    @CField("ioctl")
    IoctlFunction ioctl();

    /**
     * Sets the callback function for the {@code ioctl} operation.
     *
     * @param func the {@link IoctlFunction} to handle {@code ioctl} requests.
     */
    @CField("ioctl")
    void ioctl(IoctlFunction func);

    /**
     * Retrieves the callback function for the {@code link} operation,
     * which is used to create a hard link to a file.
     *
     * @return the {@link LinkFunction} callback.
     */
    @CField("link")
    LinkFunction link();

    /**
     * Sets the callback function for the {@code link} operation.
     *
     * @param func the {@link LinkFunction} to handle {@code link} requests.
     */
    @CField("link")
    void link(LinkFunction func);

    /**
     * Retrieves the callback function for the {@code lock} operation,
     * which is used to apply or remove a file lock.
     *
     * @return the {@link LockFunction} callback.
     */
    @CField("lock")
    LockFunction lock();

    /**
     * Sets the callback function for the {@code lock} operation.
     *
     * @param func the {@link LockFunction} to handle {@code lock} requests.
     */
    @CField("lock")
    void lock(LockFunction func);

    /**
     * Retrieves the callback function for the {@code lseek} operation,
     * which is used to reposition the file offset of an open file.
     *
     * @return the {@link LseekFunction} callback.
     */
    @CField("lseek")
    LseekFunction lseek();

    /**
     * Sets the callback function for the {@code lseek} operation.
     *
     * @param func the {@link LseekFunction} to handle {@code lseek} requests.
     */
    @CField("lseek")
    void lseek(LseekFunction func);

    /**
     * Retrieves the callback function for the {@code mkdir} operation,
     * which is used to create a new directory.
     *
     * @return the {@link MkDirFunction} callback.
     */
    @CField("mkdir")
    MkDirFunction mkdir();

    /**
     * Sets the callback function for the {@code mkdir} operation.
     *
     * @param func the {@link MkDirFunction} to handle {@code mkdir} requests.
     */
    @CField("mkdir")
    void mkdir(MkDirFunction func);

    /**
     * Retrieves the callback function for the {@code mknod} operation,
     * which is used to create a filesystem node (file, device special file, etc.).
     *
     * @return the {@link MkNodFunction} callback.
     */
    @CField("mknod")
    MkNodFunction mknod();

    /**
     * Sets the callback function for the {@code mknod} operation.
     *
     * @param func the {@link MkNodFunction} to handle {@code mknod} requests.
     */
    @CField("mknod")
    void mknod(MkNodFunction func);

    /**
     * Retrieves the callback function for the {@code poll} operation,
     * which is used to perform polling on a file descriptor.
     *
     * @return the {@link PollFunction} callback.
     */
    @CField("poll")
    PollFunction poll();

    /**
     * Sets the callback function for the {@code poll} operation.
     *
     * @param func the {@link PollFunction} to handle {@code poll} requests.
     */
    @CField("poll")
    void poll(PollFunction func);

    /**
     * Retrieves the callback function for the {@code read_buf} operation,
     * which is used to read data into a buffer.
     *
     * @return the {@link ReadBufFunction} callback.
     */
    @CField("read_buf")
    ReadBufFunction readBuf();

    /**
     * Sets the callback function for the {@code read_buf} operation.
     *
     * @param func the {@link ReadBufFunction} to handle {@code read_buf} requests.
     */
    @CField("read_buf")
    void readBuf(ReadBufFunction func);

    /**
     * Retrieves the callback function for the {@code readlink} operation,
     * which is used to read the target of a symbolic link.
     *
     * @return the {@link ReadLinkFunction} callback.
     */
    @CField("readlink")
    ReadLinkFunction readlink();

    /**
     * Sets the callback function for the {@code readlink} operation.
     *
     * @param func the {@link ReadLinkFunction} to handle {@code readlink} requests.
     */
    @CField("readlink")
    void readlink(ReadLinkFunction func);

    /**
     * Retrieves the callback function for the {@code rename} operation,
     * which is used to rename a file or directory.
     *
     * @return the {@link RenameFunction} callback.
     */
    @CField("rename")
    RenameFunction rename();

    /**
     * Sets the callback function for the {@code rename} operation.
     *
     * @param func the {@link RenameFunction} to handle {@code rename} requests.
     */
    @CField("rename")
    void rename(RenameFunction func);

    /**
     * Retrieves the callback function for the {@code statfs} operation,
     * which is used to get filesystem statistics.
     *
     * @return the {@link StatVfsFunction} callback.
     */
    @CField("statfs")
    StatVfsFunction statfs();

    /**
     * Sets the callback function for the {@code statfs} operation.
     *
     * @param func the {@link StatVfsFunction} to handle {@code statfs} requests.
     */
    @CField("statfs")
    void statfs(StatVfsFunction func);

    /**
     * Retrieves the callback function for the {@code symlink} operation,
     * which is used to create a symbolic link.
     *
     * @return the {@link SymlinkFunction} callback.
     */
    @CField("symlink")
    SymlinkFunction symlink();

    /**
     * Sets the callback function for the {@code symlink} operation.
     *
     * @param func the {@link SymlinkFunction} to handle {@code symlink} requests.
     */
    @CField("symlink")
    void symlink(SymlinkFunction func);

    /**
     * Retrieves the callback function for the {@code truncate} operation,
     * which is used to truncate a file to a specified length.
     *
     * @return the {@link TruncateFunction} callback.
     */
    @CField("truncate")
    TruncateFunction truncate();

    /**
     * Sets the callback function for the {@code truncate} operation.
     *
     * @param func the {@link TruncateFunction} to handle {@code truncate} requests.
     */
    @CField("truncate")
    void truncate(TruncateFunction func);

    /**
     * Retrieves the callback function for the {@code utimens} operation,
     * which is used to update file access and modification times.
     *
     * @return the {@link UtimensFunction} callback.
     */
    @CField("utimens")
    UtimensFunction utimens();

    /**
     * Sets the callback function for the {@code utimens} operation.
     *
     * @param func the {@link UtimensFunction} to handle {@code utimens} requests.
     */
    @CField("utimens")
    void utimens(UtimensFunction func);

    /**
     * Retrieves the callback function for the {@code write_buf} operation,
     * which is used to write data from a buffer to a file.
     *
     * @return the {@link WriteBufFunction} callback.
     */
    @CField("write_buf")
    WriteBufFunction writeBuf();

    /**
     * Sets the callback function for the {@code write_buf} operation.
     *
     * @param func the {@link WriteBufFunction} to handle {@code write_buf} requests.
     */
    @CField("write_buf")
    void writeBuf(WriteBufFunction func);
}
