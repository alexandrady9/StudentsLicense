package com.degree.persistence.user

import com.degree.persistence.base.TimestampedEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import com.degree.persistence.professor.TeacherEntity
import com.degree.persistence.student.StudentEntity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference

@Entity(name = "user")
class UserEntity(
        @field:NotEmpty
        var name: String? = null,

        @field:NotEmpty
        var firstName: String? = null,

        @field:NotEmpty
        var lastName: String? = null,

        @field:NotEmpty
        var pass: String? = null,

        @Column(unique = true)
        @field:NotEmpty
        @field:Email
        var eMail: String? = null,

        @JsonIgnoreProperties("user")
        @OneToOne(cascade = [(CascadeType.ALL)], fetch= FetchType.EAGER)
        @JoinColumn(name = "fk_student")
        var student: StudentEntity? = null,

        @JsonIgnoreProperties("user")
        @OneToOne(cascade = [(CascadeType.ALL)], fetch= FetchType.EAGER)
        @JoinColumn(name = "fk_teacher")
        var teacher: TeacherEntity? = null,

        @field:JsonIgnore
        var jwtHash: String? = null

) : TimestampedEntity(), UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority("authorised"))
        return authorities
    }

    @JsonIgnore
    override fun isEnabled(): Boolean = true

    @JsonIgnore
    override fun getUsername(): String = this.name!!

    @JsonIgnore
    override fun getPassword(): String = this.pass!!

    @JsonIgnore
    override fun isCredentialsNonExpired(): Boolean = true

    @JsonIgnore
    override fun isAccountNonExpired(): Boolean = true

    @JsonIgnore
    override fun isAccountNonLocked(): Boolean = true

    @PrePersist
    override fun prePersist() {
        super.prePersist()
        if (jwtHash == null) {
            jwtHash = UUID.randomUUID().toString()
        }
    }
}
