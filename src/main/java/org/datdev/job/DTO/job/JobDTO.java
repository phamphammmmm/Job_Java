package org.datdev.job.DTO.job;

import lombok.Data;

@Data
public class JobDTO {
    private String title;
    private Integer userId;
    private Integer companyId;
    private Integer categoryId;
    private String description;
    private String emailContact;
    private String location;
    private String salary;
    private String tags;
    private String skill;
    private String requirement;
    private String workTime;
    private String benefits;
    private String imagePath;
}
