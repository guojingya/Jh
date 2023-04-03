package com.jh.demo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;

import java.math.BigDecimal;

public class ProductPojo {
    @ExcelProperty(value = "产品编号")
    private int pid;
    @ExcelProperty(value = "产品名称")
    private String pname;
    @ExcelProperty(value = "价格")
    private BigDecimal price;
    @ExcelProperty(value = "库存")
    private int pcount;
    @ExcelProperty(value = "添加人员")
    private String createuser;
    @ExcelProperty(value = "添加时间")
    private String createtime;
    @ExcelProperty(value = "修改人员")
    private String updateuser;
    @ExcelProperty(value = "修改时间")
    private String updatetime;
    @ExcelProperty(value = "备注")
    private String remarks;

    public ProductPojo() {
    }

    public ProductPojo(int pid, String pname, BigDecimal price, int pcount, String createuser, String createtime, String updateuser, String updatetime, String remarks) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.pcount = pcount;
        this.createuser = createuser;
        this.createtime = createtime;
        this.updateuser = updateuser;
        this.updatetime = updatetime;
        this.remarks = remarks;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getPcount() {
        return pcount;
    }

    public void setPcount(int pcount) {
        this.pcount = pcount;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
