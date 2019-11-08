package com.mxys.febs.common.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class QueryRequest implements Serializable {

    /**
     * 当前页面数据量
     */
    private int pageSize = 10;
    /**
     * 当前页码
     */
    private int pageNum = 1;
    /**
     * 排序字段
     */
    private String field;
    /**
     * 排序规则，asc升序，desc降序
     */
    private String order;
}
