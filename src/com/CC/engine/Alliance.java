package com.CC.engine;

/**
 * Created by all4win78 on 8/30/2017.
 */
public enum Alliance {
    RED {
        @Override
        public int getDirection() {
            return 1;
        }
    },
    BLACK {
        @Override
        public int getDirection() {
            return -1;
        }
    };

    public abstract int getDirection();
}
