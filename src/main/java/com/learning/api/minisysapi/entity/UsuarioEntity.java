package com.learning.api.minisysapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(indexes = { @Index(name = "IDX_GUID_USU", columnList = "guid") }, name="loginUser")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CODIGO;

    @Column(unique = true)
    private String name;

    private String email;

    private String password;

}
