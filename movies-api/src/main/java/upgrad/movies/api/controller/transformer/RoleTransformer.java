package upgrad.movies.api.controller.transformer;

import java.util.List;
import java.util.stream.Collectors;

import upgrad.movies.api.model.PermissionsType;
import upgrad.movies.api.model.RoleDetailsType;
import upgrad.movies.api.model.RoleType;
import upgrad.movies.service.user.entity.RoleEntity;
import upgrad.movies.service.user.entity.RolePermissionEntity;

public final class RoleTransformer {

    private RoleTransformer(){}

    public static RoleType toRoleType(final RoleEntity roleEntity) {
        if(roleEntity == null) {
            return null;
        }
        return new RoleType().id(roleEntity.getUuid()).name(roleEntity.getName());
    }

    public static RoleDetailsType toRoleDetailsType(RoleEntity roleEntity) {
        if(roleEntity == null) {
            return null;
        }
        return new RoleDetailsType()
                .id(roleEntity.getUuid())
                .name(roleEntity.getName())
                .permissions(toPermissionsType(roleEntity.getPermissions()));
    }

    private static List<PermissionsType> toPermissionsType(List<RolePermissionEntity> permissions) {
        return permissions.stream().map(rolePermissionEntity -> {
            return new PermissionsType().id(rolePermissionEntity.getPermissionEntity().getUuid()).name(rolePermissionEntity.getPermissionEntity().getName());
        }).collect(Collectors.toList());

    }

}