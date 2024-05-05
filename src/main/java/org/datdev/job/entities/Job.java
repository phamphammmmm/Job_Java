package org.datdev.job.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
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

}
