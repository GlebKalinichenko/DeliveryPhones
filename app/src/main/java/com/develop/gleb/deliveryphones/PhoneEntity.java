package com.develop.gleb.deliveryphones;

import java.util.List;

public class PhoneEntity {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    private String name;
    private List<String> phones;

    public PhoneEntity(String name, List<String> phones) {
        this.name = name;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (String phone : phones) {
            builder.append(phone);
        }

        String log = builder.toString();
        return log;
    }
}
