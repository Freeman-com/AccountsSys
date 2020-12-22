package com.root.role;

public enum Role {

    OWNER("owner") {
        @Override
        public void apply() {

        }
    },
    ADMIN("administrator") {
        @Override
        public void apply() {

        }
    },
    SUPER_ADMIN("super administrator") {
        @Override
        public void apply() {

        }
    },
    CUSTOMER("my cutomer") {
        @Override
        public void apply() {

        }
    };

    private String theName;

    public abstract void apply();

    Role(String theName) {
        this.theName = theName;
    }

    @Override
    public String toString() {
        return this.theName;
    }
}
