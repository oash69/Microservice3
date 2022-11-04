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
@Table(name="FZZ", indexes = {@Index(name = "fzz_index",  columnList="LIDNUMMER_INTERNAL_KEY", unique = true)})
public class BaliFZZEntity implements Serializable {
    @Id
    @Column(name="LIDNUMMER_INTERNAL_KEY", columnDefinition = "decimal")
    private String internalLidNumber;

    @Column(name="GEBRUIKER_WIJZIGING", columnDefinition = "char")
    private String changeUser;

    @Column(name="DATUM_WIJZIGING", columnDefinition = "decimal")
    private String changeDate;

    @Column(name="DATUM_OVERLIJDEN", columnDefinition = "decimal")
    private String deathDate;

    @Column(name="NATIONALITEIT_NIS", columnDefinition = "char")
    private String nationalityNis;

    @Column(name="STATUS_GSM", columnDefinition = "char")
    private String gsmStatus;

    @Column(name="STATUS_EMAIL", columnDefinition = "char")
    private String emailStatus;

    @Column(name="NUMMER_MUTUALITEIT", columnDefinition = "decimal")
    private String mutualityNumber;

    @Column(name="NUMMER_SOCIALE_ZEKERHEID", columnDefinition = "decimal")
    private String socialSecurityNumber;

    @Column(name="NAAM_TITULARIS", columnDefinition = "decimal")
    private String holderName;

    @Column(name="RESERVE", columnDefinition = "decimal")
    private String reserve;

    @Column(name="GSM", columnDefinition = "decimal")
    private String gsmNumber;

    @Column(name="EMAIL_ADRES", columnDefinition = "decimal")
    private String emailAddress;

    @Column(name="TEKST_CHAR50", columnDefinition = "decimal")
    private String text;

    @Column(name="TIME_STAMP", columnDefinition = "timestamp")
    private String timeStamp;

    @Column(name="ERROR_EMAIL", columnDefinition = "char")
    private String emailError;

    @Column(name="VOORNAAM", columnDefinition = "decimal")
    private String firstName;

    @Column(name="ACHTERNAAM", columnDefinition = "decimal")
    private String lastName;

    @Column(name="CODE_LAND_GRENSARBEIDERS", columnDefinition = "char")
    private String CountryBorderWorkersCode;

    @Column(name="DATUM_EMAIL", columnDefinition = "decimal")
    private String emailDate;

}
