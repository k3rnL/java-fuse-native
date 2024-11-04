package com.k3rnl.fuse.fuse;

public enum FuseFillDirFlags {
    NONE(0),                    // Custom "no flag" value
    FUSE_FILL_DIR_PLUS(1 << 1); // Corresponds to the expected native value (1 << 1)

    private final int value;

    FuseFillDirFlags(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}