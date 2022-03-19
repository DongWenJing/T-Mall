package com.tmall.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author : DongWJ
 * @Date : 2022/3/19 12:29
 */
@Data
public class Page<T> {

    private Integer pageNum;
    private Integer pageSize;
    private Integer total;
    private List<T> data;
}
