package br.com.jogoemequipe.mappers;

import br.com.jogoemequipe.dto.response.UserResponseDTO;
import br.com.jogoemequipe.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper  INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDTO toUserDTO(Usuario user);

    Usuario toEntity(UserResponseDTO userDTO);
}
