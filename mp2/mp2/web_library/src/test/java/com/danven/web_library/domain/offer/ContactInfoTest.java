package com.danven.web_library.domain.offer;

import com.danven.web_library.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ContactInfoTest {

    private static final String VALID_EMAIL_1 = "danven@gmail.com";

    private static final String VALID_EMAIL_2 = "s2870821@pjwtk.edu.pl";

    private static final String INVALID_EMAIL_1 = "danven2018gmail.com";

    private static final String VALID_TELEPHONE_NUMBER_1 = "9873734";

    private static final String VALID_TELEPHONE_NUMBER_2 = "+489873794";

    private static final String INVALID_TELEPHONE_NUMBER_1 = "+48T9873734";

    private static final String VALID_SOCIAL_MEDIA_LINK_1 = "https://instagram.com/account/sdafsadfsdaf";

    private static final String VALID_SOCIAL_MEDIA_LINK_2 = "https://telegram.com/page/h2345hj324jk5";

    private static final String INVALID_SOCIAL_MEDIA_LINK_1 = "//telegram.com/page/h2345hj324jk5";

    @Test
    public void createContactInfoWitValidParameters() {

        ContactInfo contactInfo = new ContactInfo.Builder()
                .setEmail(VALID_EMAIL_1)
                .setSocialMediaLink(VALID_SOCIAL_MEDIA_LINK_1)
                .setTelephoneNumber(VALID_TELEPHONE_NUMBER_1)
                .build();

        assertEquals(VALID_EMAIL_1, contactInfo.getEmail());
        assertEquals(VALID_TELEPHONE_NUMBER_1, contactInfo.getTelephoneNumber());
        assertEquals(VALID_SOCIAL_MEDIA_LINK_1, contactInfo.getSocialMediaLink());
    }

    @Test
    public void updateContactInfoWithValidParameters() {
        ContactInfo contactInfo = new ContactInfo.Builder()
                .setEmail(VALID_EMAIL_1)
                .setSocialMediaLink(VALID_SOCIAL_MEDIA_LINK_1)
                .setTelephoneNumber(VALID_TELEPHONE_NUMBER_1)
                .build();

        contactInfo.setEmail(VALID_EMAIL_2);
        contactInfo.setTelephoneNumber(VALID_TELEPHONE_NUMBER_2);
        contactInfo.setSocialMediaLink(VALID_SOCIAL_MEDIA_LINK_2);

        Assertions.assertEquals(VALID_EMAIL_2, contactInfo.getEmail());
        Assertions.assertEquals(VALID_TELEPHONE_NUMBER_2, contactInfo.getTelephoneNumber());
        Assertions.assertEquals(VALID_SOCIAL_MEDIA_LINK_2, contactInfo.getSocialMediaLink());
    }

    @Test
    public void createContactInfoWithInvalidEmail() {
        Assertions.assertThrows(ValidationException.class,
                () -> new ContactInfo.Builder()
                        .setEmail(INVALID_EMAIL_1)
                        .setSocialMediaLink(VALID_SOCIAL_MEDIA_LINK_1)
                        .setTelephoneNumber(VALID_TELEPHONE_NUMBER_1)
                        .build()
        );
    }

    @Test
    public void createContactInfoWithInvalidTelephoneNumber() {
        Assertions.assertThrows(ValidationException.class,
                () -> new ContactInfo.Builder()
                        .setEmail(VALID_EMAIL_1)
                        .setSocialMediaLink(VALID_SOCIAL_MEDIA_LINK_1)
                        .setTelephoneNumber(INVALID_TELEPHONE_NUMBER_1)
                        .build()
        );
    }

    @Test
    public void createContactInfoWithInvalidSocialMediaLink() {
        Assertions.assertThrows(ValidationException.class,
                () -> new ContactInfo.Builder()
                        .setEmail(VALID_EMAIL_1)
                        .setSocialMediaLink(INVALID_SOCIAL_MEDIA_LINK_1)
                        .setTelephoneNumber(VALID_TELEPHONE_NUMBER_1)
                        .build()
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingNotAllArguments() {

        Assertions.assertThrows(ValidationException.class,
                () -> new ContactInfo.Builder()
                        .setEmail(VALID_EMAIL_1)
                        .build()
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidEmail() {
        ContactInfo contactInfo = new ContactInfo.Builder()
                .setEmail(VALID_EMAIL_1)
                .setSocialMediaLink(VALID_SOCIAL_MEDIA_LINK_1)
                .setTelephoneNumber(VALID_TELEPHONE_NUMBER_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> contactInfo.setEmail(INVALID_EMAIL_1)
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidSocialMedia() {
        ContactInfo contactInfo = new ContactInfo.Builder()
                .setEmail(VALID_EMAIL_1)
                .setSocialMediaLink(VALID_SOCIAL_MEDIA_LINK_1)
                .setTelephoneNumber(VALID_TELEPHONE_NUMBER_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> contactInfo.setSocialMediaLink(INVALID_SOCIAL_MEDIA_LINK_1)
        );
    }

    @Test
    public void testThrowValidationExceptionWhenSettingInvalidTelephoneNumber() {

        ContactInfo contactInfo = new ContactInfo.Builder()
                .setEmail(VALID_EMAIL_1)
                .setSocialMediaLink(VALID_SOCIAL_MEDIA_LINK_1)
                .setTelephoneNumber(VALID_TELEPHONE_NUMBER_1)
                .build();

        Assertions.assertThrows(ValidationException.class,
                () -> contactInfo.setTelephoneNumber(INVALID_TELEPHONE_NUMBER_1)
        );
    }


}
