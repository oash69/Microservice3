package be.abvv.bali.member.persistence.rpg.domain;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Table(name="FLI", indexes = {@Index(name = "fli_index",  columnList="LILINR", unique = true)})
public class BaliFLIEntity implements Serializable {
    @Id
    @Column(name="LILINR", columnDefinition = "decimal", nullable = false)
    private String memberNumber;

    @Column(name="LINPEN", columnDefinition = "char")
    private String niss;

    @Column(name="LICHDI", columnDefinition = "char")
    private String nissCD;

    @Column(name="LINOMX", columnDefinition = "char")
    private String longName; /* Lastname,FirstName */

    @Column(name="LIC8XX", columnDefinition = "char")
    private char lic8xx; /* Used for C1 test */

    @Column(name="LIONTK", columnDefinition = "char")
    private String resigned; /* If this field is not empty, it means the member resigned */

    @Column(name="LIONTD", columnDefinition = "decimal")
    private String resignDate; /* Date at which the member resigned */

    @Column(name="LIROLI", columnDefinition = "char")
    private String liroli;

    @Column(name="LILAMA", columnDefinition = "char")
    private String lilama;

    @Column(name="LICREG", columnDefinition = "char")
    private String region;

    @Column(name="LIADRE", columnDefinition = "char")
    private String street;

    @Column(name="LICPOS", columnDefinition = "char")
    private String zipCode;

    @Column(name="LILOCA", columnDefinition = "char")
    private String city;

    public String getNiss() {
        return niss;
    }
}
