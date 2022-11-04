package be.abvv.bali.member.persistence.domain;

public interface IMemberEntity {
    String getId();

    void setId(String id);

    String getNom();
    void setNom(String nom);

    String getPrenom();
    void setPrenom(String prenom);

    String getTelephone();
    void setTelephone(String telephone);

}
