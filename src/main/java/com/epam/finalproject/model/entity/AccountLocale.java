package com.epam.finalproject.model.entity;

    public enum AccountLocale {
        RUSSIAN("ru"),
        ENGLISH("en");

        private final String postfix;

        AccountLocale(String postfix) {
            this.postfix = postfix;
        }
        public static AccountLocale localeByPostfix(String postfix) {
            for (AccountLocale value : values()) {
                if (value.postfix.equals(postfix)) {
                    return value;
                }
            }
            return null;
        }
        public String getPostfix() {
            return postfix;
        }
    }
