package com.cs.qsx.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_msg")
public class Msg {
    @Id //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Integer id;
    @Column(length = 10)
    private String name;
    @Column(length = 100)
    private String context;
    @Column(columnDefinition = "DATE") //指定属性在表中的字段类型
    @JSONField(format = "yyyy-MM-dd")  //指定json格式化时如何格式化date
    private Date createDate;

    public Msg() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
