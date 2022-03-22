package com.tmall.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @Author : DongWJ
 * @Date : 2022/3/19 12:29
 */
@Data
public class Page<T> implements Serializable {

    private Integer pageNum;
    private Integer pageSize;
    private Integer total;
    private List<T> data;
}
