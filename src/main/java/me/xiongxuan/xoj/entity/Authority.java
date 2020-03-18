package me.xiongxuan.xoj.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "oj_authority")
public class Authority {

    @Id
    @TableGenerator( name = "AppSeqStore", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "AppSeqStore" )
    private Integer id;

    private String authorityName;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Authority)) {
            return false;
        }
        Authority role = (Authority) o;
        return id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authorityName='" + authorityName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
