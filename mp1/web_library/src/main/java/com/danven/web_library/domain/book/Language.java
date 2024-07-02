package com.danven.web_library.domain.book;

import com.danven.web_library.exceptions.ValidationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Language implements Serializable {

    @Serial
    private static final long serialVersionUID = 5L;

    private static final List<Language> records = new ArrayList<>();

    private String name;

    private String acronym;

    private Language(LanguageBuilder builder) {
        validateName(builder.name);
        validateAcronym(builder.acronym);

        this.name = builder.name;
        this.acronym = builder.acronym;

        records.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        validateAcronym(acronym);
        this.acronym = acronym;
    }

    public static class LanguageBuilder {
        private String name;
        private String acronym;

        public LanguageBuilder() {
        }

        public LanguageBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public LanguageBuilder setAcronym(String acronym) {
            this.acronym = acronym;
            return this;
        }

        public Language build() {
            return new Language(this);
        }
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name is invalid");
        }
    }

    private static void validateAcronym(String acronym) {
        if (acronym == null || acronym.isBlank()) {
            throw new ValidationException("Acronym is invalid");
        }
    }

    public static List<Language> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return Objects.equals(name, language.name) && Objects.equals(acronym, language.acronym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, acronym);
    }

    @Override
    public String toString() {
        return "Language{" +
                "name='" + name + '\'' +
                ", acronym='" + acronym + '\'' +
                '}';
    }

}
