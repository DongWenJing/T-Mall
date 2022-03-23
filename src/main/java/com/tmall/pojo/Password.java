package com.tmall.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class Password implements Serializable {
    private BigInteger userId;
    private String password;
    private String newPassword;
    private String twicePassword;
}