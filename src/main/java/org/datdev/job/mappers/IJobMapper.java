package org.datdev.job.mappers;

import org.datdev.job.DTO.job.JobDTO;
import org.datdev.job.entities.Job;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface IJobMapper {
    IJobMapper INSTANCE = Mappers.getMapper(IJobMapper.class);

    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    JobDTO jobToJobDTO(Job job);

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Job jobDTOToJob(JobDTO jobDTO);
}
