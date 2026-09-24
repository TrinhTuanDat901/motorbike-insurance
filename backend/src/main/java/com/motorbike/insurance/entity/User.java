package com.motorbike.insurance.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;
import com.motorbike.insurance.entity.Role;
import org.hibernate.mapping.ToOne;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@SecondaryTable(
        name = "user_roles",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id")
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Integer userId;

    @Column(name = "first_name", nullable = false, length = 50)
    String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    String lastName;

    @Transient
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Column(name = "password", nullable = false, length = 255)
    String password;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    String email;

    @Column(name = "phone", length = 20)
    String phone;

    @Column(name = "avatar_path", length = 500)
    String avatarPath;

    @ManyToOne
    @JoinColumn(table = "user_roles", name = "role_name")
    Role role;

    // @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @Builder.Default
    String status = "ACTIVE";

    @Column(name = "created_at")
    @Builder.Default
    LocalDateTime createdAt = LocalDateTime.now();
}
