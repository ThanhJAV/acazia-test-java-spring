package com.acazia.testjavaspring.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponse<T> {

    int status;
    String message;
    String error;
    T data;

    public MessageResponse(T data) {
        super();
        this.message = "success";
        this.data = data;
    }
}
