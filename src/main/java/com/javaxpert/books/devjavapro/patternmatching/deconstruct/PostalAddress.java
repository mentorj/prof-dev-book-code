package com.javaxpert.books.devjavapro.patternmatching.deconstruct;
/**
 * Model the postal data required to send letters or parcels tp
 * any customer
 * @author deadbrain
 */
public class PostalAddress implements ContactType{
    private final String zipCode;
    private final String lineAddress1;
    private final String lineAddress2;

    private final String countryName;

    private final String townName;

    public String getCountryName() {
        return countryName;
    }

    public String getTownName() {
        return townName;
    }

    private PostalAddress(String country, String town, String zip, String line1, String line2){
        countryName=country;
        townName=town;
        zipCode=zip;
        lineAddress1=line1;
        lineAddress2=line2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getLineAddress1() {
        return lineAddress1;
    }

    public String getLineAddress2() {
        return lineAddress2;
    }

    public static class Builder{
        private String zipCode;
        private String lineAddress1;
        private String lineAddress2;


        private String countryName;

        private String townName;

        public PostalAddress build(){
            return new PostalAddress(countryName,townName,zipCode,lineAddress1,lineAddress2);
        }

        public Builder setCountryName(String countryName) {
            this.countryName = countryName;
            return this;
        }

        public Builder setTownName(String townName) {
            this.townName = townName;
            return this;
        }

        public Builder setZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder setLineAddress1(String lineAddress1) {
            this.lineAddress1 = lineAddress1;
            return this;
        }

        public Builder setLineAddress2(String lineAddress2) {
            this.lineAddress2 = lineAddress2;
            return  this;
        }
    }
}
