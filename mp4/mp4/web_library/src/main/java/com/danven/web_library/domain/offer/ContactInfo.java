package com.danven.web_library.domain.offer;


import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContactInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 7L;

    private static final List<ContactInfo> records = new ArrayList<>();

    private String email;

    private String telephoneNumber;

    private String socialMediaLink;

    public ContactInfo(String email, String telephoneNumber, String socialMediaLink) {

        validateEmail(email);
        validateTelephoneNumber(telephoneNumber);
        validateSocialMediaLink(socialMediaLink);

        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.socialMediaLink = socialMediaLink;

        records.add(this);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        validateTelephoneNumber(telephoneNumber);
        this.telephoneNumber = telephoneNumber;
    }

    public String getSocialMediaLink() {
        return socialMediaLink;
    }

    public void setSocialMediaLink(String socialMediaLink) {
        validateSocialMediaLink(socialMediaLink);
        this.socialMediaLink = socialMediaLink;
    }

    private static void validateEmail(String email) {
        if (email == null || !email.matches("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$")) {
            throw new ValidationException("Incorrect format of email");
        }
    }

    private static void validateTelephoneNumber(String telephoneNumber) {
        if (telephoneNumber == null || !telephoneNumber.matches("^\\+?\\d+$")) {
            throw new ValidationException("Incorrect format of telephone number");
        }
    }

    private static void validateSocialMediaLink(String socialMediaLink) {
        if (socialMediaLink == null || !socialMediaLink.matches("^https://.*")) {
            throw new ValidationException("Incorrect format of social media link");
        }
    }

    public static List<ContactInfo> getRecords() {
        return records;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInfo that = (ContactInfo) o;
        return Objects.equals(email, that.email) && Objects.equals(telephoneNumber, that.telephoneNumber) && Objects.equals(socialMediaLink, that.socialMediaLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, telephoneNumber, socialMediaLink);
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "email='" + email + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", socialMediaLink='" + socialMediaLink + '\'' +
                '}';
    }
}


