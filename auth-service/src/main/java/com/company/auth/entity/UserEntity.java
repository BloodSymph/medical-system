package com.company.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "User")
@Table(name = "users")
@NamedEntityGraph(
        name = "user-roles-named-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("roles")
        }
)
public class UserEntity implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_username", unique = true, nullable = false, length = 120)
    private String username;

    @Column(name = "user_password", nullable = false, unique = true, length = 120)
    private String password;

    @CreationTimestamp
    @Column(name = "user_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "user_updated")
    private LocalDateTime updated;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "user_roles",
            joinColumns = {
                    @JoinColumn(name = "user_username", referencedColumnName = "user_username")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_name", referencedColumnName = "role_name")
            }
    )
    private List<RoleEntity> roles;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private List<TokenEntity> tokens;

    @Version
    @Column(name = "user_version", nullable = false, unique = true)
    private Long version;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(
                        roleEntity -> new SimpleGrantedAuthority(
                                roleEntity.getName()
                        )
                ).collect(
                        Collectors.toSet()
                );
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
