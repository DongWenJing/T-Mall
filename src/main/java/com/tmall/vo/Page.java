package com.tmall.vo;

import com.tmall.pojo.User;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * @Author : DongWJ
 * @Date : 2022/3/19 12:29
 */
@Data
@Accessors(chain = true)
@Component
public class Page<T>  implements Serializable {

    private Integer pageNum;
    private Integer pageSize;
    private Integer total;
    private List<T> data;
}
