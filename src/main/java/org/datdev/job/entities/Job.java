package org.datdev.job.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@EqualsAndHashCode(callSuper = true)
@Table(name = "jobs")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
public class Job extends _BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "email_contact")
    private String emailContact;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "salary")
    private String salary;

    @Column(name = "tags")
    private String tags;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "skill")
    private String skill;

    @Column(name = "requirement")
    private String requirement;

    @Column(name = "work_time")
    private String workTime;

    @Column(name = "benefits")
    private String benefits;

    @Column(name = "image_path")
    private String imagePath;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
