package com.smallstudy.error.errordto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailSendErrorDTO {

    private int errorCode;
    private String msg;
}
