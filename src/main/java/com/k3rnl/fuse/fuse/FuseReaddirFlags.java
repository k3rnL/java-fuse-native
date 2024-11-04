package com.k3rnl.fuse.fuse;

public enum FuseReaddirFlags {
    NONE(0),
    FUSE_READDIR_PLUS(1);  // (1 << 0)

    private final int value;

    FuseReaddirFlags(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static FuseReaddirFlags fromValue(int value) {
        for (FuseReaddirFlags flag : FuseReaddirFlags.values()) {
            if (flag.getValue() == value) {
                return flag;
            }
        }
        return null;
    }
}