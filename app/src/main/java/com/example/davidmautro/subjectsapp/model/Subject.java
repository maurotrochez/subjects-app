

package com.example.davidmautro.subjectsapp.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Subject {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("id_semester")
    @Expose
    private Integer idSemester;
    @SerializedName("id_subject")
    @Expose
    private Integer idSubject;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("teacher")
    @Expose
    private String teacher;

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
     * The idSubject
     */
    public Integer getIdSubject() {
        return idSubject;
    }

    /**
     *
     * @param idSubject
     * The id_subject
     */
    public void setIdSubject(Integer idSubject) {
        this.idSubject = idSubject;
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

    /**
     *
     * @return
     * The teacher
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     *
     * @param teacher
     * The teacher
     */
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

}