package be.abvv.bali.member.persistence.rpg.domain;

import be.abvv.bali.member.persistence.domain.IMemberEntity;

public class MemberEntity implements IMemberEntity {
private String id;
private String nom;
private String prenom;

private String telephone;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id=id;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public void setNom(String nom) {
        this.nom=nom;
    }

    @Override
    public String getPrenom() {
        return prenom;
    }

    @Override
    public void setPrenom(String prenom) {
        this.prenom=prenom;
    }

    @Override
    public String getTelephone() {
        return telephone;
    }

    @Override
    public void setTelephone(String telephone) {
        this.telephone=telephone;
    }
}
