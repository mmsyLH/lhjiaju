package xyz.lhweb.furns.bean;

import java.math.BigDecimal;

/**
 * 信息文本
 *
 * @author 罗汉
 * @date 2023/06/05
 */
public class InfoText {
    private String address;
    private String name;
    private String telephone;
    private BigDecimal totalPrices;
    private Integer state;


    public InfoText() {
    }

    public InfoText(String address, String name, String telephone, BigDecimal totalPrices, Integer state) {
        this.address = address;
        this.name = name;
        this.telephone = telephone;
        this.totalPrices = totalPrices;
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public BigDecimal getTotalPrices() {
        return totalPrices;
    }

    public void setTotalPrices(BigDecimal totalPrices) {
        this.totalPrices = totalPrices;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "InfoText{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", totalPrices=" + totalPrices +
                '}';
    }
}
