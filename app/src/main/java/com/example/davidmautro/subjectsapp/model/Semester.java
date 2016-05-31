package com.example.davidmautro.subjectsapp.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Semester {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("id_semester")
    @Expose
    private Integer idSemester;
    @SerializedName("id_university")
    @Expose
    private Integer idUniversity;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     *
     * @return
     * The code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return
     * The idSemester
     */
    public Integer getIdSemester() {
        return idSemester;
    }

    /**
     *
     * @param idSemester
     * The id_semester
     */
    public void setIdSemester(Integer idSemester) {
        this.idSemester = idSemester;
    }

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
