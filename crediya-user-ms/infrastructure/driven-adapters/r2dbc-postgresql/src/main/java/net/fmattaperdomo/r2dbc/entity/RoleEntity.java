package net.fmattaperdomo.r2dbc.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("role")
@Builder
public class RoleEntity {
    @Id
    @Column(value = "id_role")
    private Integer roleId;
    private String name;
    private String description;
}
