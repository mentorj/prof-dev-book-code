package com.javaxpert.books.devjavapro.patternmatching.deconstruct;
import static io.vavr.API.*;

public class DestructuringWithVavr {

    private static void handleContact(ContactType contact){

        String result = Match(contact).of(
                Case(DestructurePatterns.$PostalAddress( $("France") ,$(),$(),$(),$() ) , ()->"I love France"),
                Case($(), () -> "notfound")
        );
        System.out.println("result is = "+ result);

    }
    public static void main(String[] args) {
        ContactType contact =new ElectronicAddress.Builder().setEmailAddress("toto@yahoo.fr").setInstaNick("awesometoto").build();
        ContactType postal = new PostalAddress.Builder().setCountryName("France").setLineAddress1("144 avenue champs elysees").setZipCode("75008").build();
        handleContact(postal);

    }
}

