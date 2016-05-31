package com.example.davidmautro.subjectsapp.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Calification {

    @SerializedName("final_note")
    @Expose
    private Double finalNote;
    @SerializedName("id_subject")
    @Expose
    private Integer idSubject;
    @SerializedName("note_1")
    @Expose
    private Double note1;
    @SerializedName("note_2")
    @Expose
    private Double note2;
    @SerializedName("note_3")
    @Expose
    private Double note3;
    @SerializedName("note_add")
    @Expose
    private Double noteAdd;

    /**
     *
     * @return
     * The finalNote
     */
    public Double getFinalNote() {
        return finalNote;
    }

    /**
     *
     * @param finalNote
     * The final_note
     */
    public void setFinalNote(Double finalNote) {
        this.finalNote = finalNote;
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
     * The note1
     */
    public Double getNote1() {
        return note1;
    }

    /**
     *
     * @param note1
     * The note_1
     */
    public void setNote1(Double note1) {
        this.note1 = note1;
    }

    /**
     *
     * @return
     * The note2
     */
    public Double getNote2() {
        return note2;
    }

    /**
     *
     * @param note2
     * The note_2
     */
    public void setNote2(Double note2) {
        this.note2 = note2;
    }

    /**
     *
     * @return
     * The note3
     */
    public Double getNote3() {
        return note3;
    }

    /**
     *
     * @param note3
     * The note_3
     */
    public void setNote3(Double note3) {
        this.note3 = note3;
    }

    /**
     *
     * @return
     * The noteAdd
     */
    public Double getNoteAdd() {
        return noteAdd;
    }

    /**
     *
     * @param noteAdd
     * The note_add
     */
    public void setNoteAdd(Double noteAdd) {
        this.noteAdd = noteAdd;
    }

}