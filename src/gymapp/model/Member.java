package gymapp.model;

import java.util.Date;

public class Member extends Person {

    private String membershipType;
    private int months;
    private int totalPrice;
    private Date birthDate;
    private String note;
    private boolean acceptedRules;

    public Member(String firstName, String lastName, String gender,
                  String membershipType, int months, int totalPrice,
                  Date birthDate, String note, boolean acceptedRules) {

        super(firstName, lastName, gender);
        this.membershipType = membershipType;
        this.months = months;
        this.totalPrice = totalPrice;
        this.birthDate = birthDate;
        this.note = note;
        this.acceptedRules = acceptedRules;
    }

    @Override
    public String getInfo() {
        return firstName + " " + lastName + " (" + membershipType + ")";
    }

    public int getTotalPrice() {
        return totalPrice;
    }
    
    public String getMembershipType() {
        return membershipType;
    }

    public int getMonths() {
        return months;
    }

    public java.util.Date getBirthDate() {
        return birthDate;
    }

    public String getNote() {
        return note;
    }

}