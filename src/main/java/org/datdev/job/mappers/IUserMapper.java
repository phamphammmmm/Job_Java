package org.datdev.job.mappers;

import org.datdev.job.DTO.user.UserDTO;
import org.datdev.job.entities.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullname", source = "fullname")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "birthday", source = "birthday")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "isPremium", source = "isPremium")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)

    User toEntity(UserDTO userDTO);
}
