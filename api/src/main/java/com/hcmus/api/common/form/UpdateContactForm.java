package com.hcmus.api.common.form;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateContactForm {
    private final String nickName;

    @JsonCreator
    public UpdateContactForm(@JsonProperty("nick_name") String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return this.nickName;
    }
}
