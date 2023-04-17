package com.javaxpert.books.devjavapro.patternmatching.deconstruct;

/**
 * Model for an holder of the various ways tp reach a customer
 * through social media
 */
public class ElectronicAddress implements ContactType{
    private String emailAddress;
    private String facebookNick;
    private String instaNick;
    private String gsmNumber;

    private ElectronicAddress(String email,String facebook,String insta,String gsm){
        emailAddress=email;
        facebookNick=facebook;
        instaNick=insta;
        gsmNumber=gsm;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFacebookNick() {
        return facebookNick;
    }

    public String getInstaNick() {
        return instaNick;
    }

    public String getGsmNumber() {
        return gsmNumber;
    }

    public static class Builder{
        private String emailAddress;
        private String facebookNick;
        private String instaNick;
        private String gsmNumber;

        public Builder(){

        }

        public Builder setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder setFacebookNick(String facebookNick) {
            this.facebookNick = facebookNick;
            return this;
        }

        public Builder setInstaNick(String instaNick) {
            this.instaNick = instaNick;
            return this;
        }

        public Builder setGsmNumber(String gsmNumber) {
            this.gsmNumber = gsmNumber;
            return this;
        }

        public ElectronicAddress build(){
            return new ElectronicAddress(emailAddress,facebookNick,instaNick,gsmNumber);
        }
    }
}
