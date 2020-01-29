package com.msg91.sendotp.sample;

public class Cheque3 {

    private String image;
    private String status;
    private String user;
    private String prize;
    private String prize1;
    private String prize2;
    private String prize3;
    private String prize4;
    private String prize5;

    public Cheque3(String image, String status, String user, String prize, String prize1, String prize2, String prize3, String prize4, String prize5) {

        this.image = image;
        this.status = status;
        this.user=user;
        this.prize=prize;
        this.prize1=prize1;
        this.prize2=prize2;
        this.prize3=prize3;
        this.prize4=prize4;
        this.prize5=prize5;


    }

    public Cheque3() {
    }


    public String getImage() {
        return image;
    }
    public String getStatus() {
        return status;
    }
    public String getUser() {
        return user;
    }
    public String getPrize() { return  prize; }
   public String getPrize1() { return  prize1; }
   public String getPrize2() { return  prize2; }
   public String getPrize3() { return  prize3; }
   public String getPrize4() { return  prize4; }
    public String getPrize5() { return  prize5; }


}

