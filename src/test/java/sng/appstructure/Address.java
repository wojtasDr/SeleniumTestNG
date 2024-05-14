package sng.appstructure;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
public class Address {
    private final String firstName;
    private final String lastName;
    private final String company;
    private final String addressLine1;
    private final String addressLine2;
    private final String zipCode;
    private final String city;
    private final String country;
    private final String homePhone;
    private final String mobilePhone;
    private final String additionalInfo;
    @Setter
    private String title;

    public Address(String firstName, String lastName, String company, String addressLine1, String addressLine2, String zipCode, String city,
                   String country, String homePhone, String mobilePhone, String additionalInfo, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.additionalInfo = additionalInfo;
        this.title = title;
    }

    public List<String> getAddress() {
        return List.of(firstName, lastName, company, addressLine1, addressLine2, zipCode, city, country, homePhone,
                mobilePhone, additionalInfo, title);
    }
}
