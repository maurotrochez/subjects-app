package com.example.davidmautro.subjectsapp.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class University {

    @SerializedName("id_university")
    @Expose
    private Integer idUniversity;
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     *
     * @return
     * The idUniversity
     */
    public Integer getIdUniversity() {
        return idUniversity;
    }

    /**
     *
     * @param idUniversity
     * The id_university
     */
    public void setIdUniversity(Integer idUniversity) {
        this.idUniversity = idUniversity;
    }

    /**
     *
     * @return
     * The idUser
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     *
     * @param idUser
     * The id_user
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

}
