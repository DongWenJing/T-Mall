package com.tmall.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class Password implements Serializable {
    private static final long serialVersionUID = 9183980544522383493L;
    private BigInteger userId;
    private String password;
    private String newPassword;
    private String twicePassword;
}