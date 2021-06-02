package com.tuannq.tflat.Model;

public class Present {
    private String index;
    private String ten;
    private String mota;

//them index vao nha o day nay
//
    public Present(String index,String ten,String mota) {
        this.index=index;
        this.ten = ten;
        this.mota=mota;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }


}
