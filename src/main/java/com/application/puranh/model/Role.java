package com.application.puranh.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    public enum Type {
        ROLE_ADMIN("ADMIN"), ROLE_USER("USER");
        private final String authority;
        Type(String authority) {
            this.authority = authority;
        }

        public String getAuthority() {
            return authority;
        }

//        @JsonCreator
//        public static Type getType(String value) {
//            for (Type type : Type.values()) {
//                if (type.getAuthority().equals(value)) {
//                    return type;
//                }
//            }
//            return null;
//        }
    }

    @Override
    public String getAuthority() {
        return getRole().name();
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Type role;

    public Role() {
    }

    public Role(Long roleId, Type role) {
        this.roleId = roleId;
        this.role = role;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Type getRole() {
        return role;
    }

    public void setRole(Type role) {
        this.role = role;
    }
}
