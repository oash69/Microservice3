package be.abvv.bali.member.persistence.rpg.domain;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Table(name="FUS")
public class BaliFUSEntity implements Serializable {
    @Id
    @Column(name="GEBRUIKER_CHAR8", columnDefinition = "char")
    private String systemUsername;

    @Column(name="CODE_TABEL", columnDefinition = "char")
    private String codeTable;

    @Column(name="TAALCODE", columnDefinition = "char")
    private String languageCode;

    @Column(name="DEFAULT_GEWEST_VOOR_GEBRUIKER", columnDefinition = "char")
    private String defaultUserRegion;

    @Column(name="DEFAULT_SUBGEWEST", columnDefinition = "char")
    private String defaultSubRegion;

    @Column(name="DEFAULT_CENTRALE_VOOR_GEBRUIKE", columnDefinition = "char")
    private String defaultUserCentral;

    @Column(name="NAAM_VAN_DE_GEBRUIKER", columnDefinition = "char")
    private String nameUser;

    @Column(name="INITIALEN_GEBRUIKER", columnDefinition = "char")
    private String userInitials;

    @Column(name="TEST_LIMITED_USERS", columnDefinition = "char")
    private String testLimitedUsers;

    @Column(name="GEBRUIKER_IBMER", columnDefinition = "char")
    private String ibmUser;

    @Column(name="HOOFDGEBRUIKER_MASTER", columnDefinition = "char")
    private String mainUserMaster;

    @Column(name="LOKALISATIE", columnDefinition = "char")
    private String localisation;

    @Column(name="AFDELING", columnDefinition = "char")
    private String department;

    @Column(name="PERMANENTIE", columnDefinition = "char")
    private String permanence;

    @Column(name="INNINGSCODE", columnDefinition = "char")
    private String inningCode;

    @Column(name="GEBRUIKERSGROEP", columnDefinition = "char")
    private String userGroup;

    @Column(name="GROUP_PROFILE", columnDefinition = "char")
    private String groupProfile;

    @Column(name="AUTHORISATION_LIST", columnDefinition = "char")
    private String authorisationList;

    @Column(name="AS_400_SYSTEM_NAME", columnDefinition = "char")
    private String as400SystemName;

    @Column(name="GEBRUIKER_WIJZIGING", columnDefinition = "char")
    private String changeUser;

    @Column(name="DATUM_WIJZIGING", columnDefinition = "decimal")
    private String changeDate;

    @Column(name="IDENTIFICATIENR", columnDefinition = "char")
    private String identificatieNr;

    @Column(name="CHECK_DIGIT", columnDefinition = "char")
    private String checkDigit;

    @Column(name="TYPE_IDENTIFICATIENR", columnDefinition = "char")
    private String typeIdentificatieNr;

    @Column(name="USER_ACTIVE_DIRECTORY", columnDefinition = "char")
    private String userActiveDirectory;


}
