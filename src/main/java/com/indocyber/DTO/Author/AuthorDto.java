package com.indocyber.DTO.Author;

import com.indocyber.Entities.Author;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AuthorDto {
    private Integer id;
    private String title;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deceasedDate;
    private String education;
    private String summary;
    private String status;
    private Integer age;
    private String fullName;

    public AuthorDto(){}

    public AuthorDto(String title, String firstName, String lastName, LocalDate birthDate, LocalDate deceasedDate, String education, String summary) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.deceasedDate = deceasedDate;
        this.education = education;
        this.summary = summary;
    }

    public AuthorDto(Author author) {
        this.id = author.getId();
        this.fullName = author.getTitle() + " " + author.getFirstName() + " " + author.getLastName();
        this.age = (int) ChronoUnit.YEARS.between(author.getBirthDate(), LocalDate.now());
        this.status = author.getDeceasedDate() == null ? "Alive" : "Deceased";
        this.education = author.getEducation();

        if(author.getDeceasedDate() != null && author.getDeceasedDate().getYear() > author.getBirthDate().getYear()) {
          this.age = (int) ChronoUnit.YEARS.between(author.getBirthDate(),author.getDeceasedDate());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getDeceasedDate() {
        return deceasedDate;
    }

    public void setDeceasedDate(LocalDate deceasedDate) {
        this.deceasedDate = deceasedDate;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "AuthorDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", deceasedDate=" + deceasedDate +
                ", education='" + education + '\'' +
                ", summary='" + summary + '\'' +
                ", status='" + status + '\'' +
                ", age=" + age +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
