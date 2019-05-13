package com.btjf.model;

import java.io.Serializable;

public class EvaluateLabel implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_evaluate_label.FID
     *
     * @mbg.generated
     */
    private Integer ID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_evaluate_label.FEvaluateID
     *
     * @mbg.generated
     */
    private Integer evaluateID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_evaluate_label.FLabel
     *
     * @mbg.generated
     */
    private String label;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_evaluate_label
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_evaluate_label.FID
     *
     * @return the value of t_evaluate_label.FID
     *
     * @mbg.generated
     */
    public Integer getID() {
        return ID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_evaluate_label.FID
     *
     * @param ID the value for t_evaluate_label.FID
     *
     * @mbg.generated
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_evaluate_label.FEvaluateID
     *
     * @return the value of t_evaluate_label.FEvaluateID
     *
     * @mbg.generated
     */
    public Integer getEvaluateID() {
        return evaluateID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_evaluate_label.FEvaluateID
     *
     * @param evaluateID the value for t_evaluate_label.FEvaluateID
     *
     * @mbg.generated
     */
    public void setEvaluateID(Integer evaluateID) {
        this.evaluateID = evaluateID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_evaluate_label.FLabel
     *
     * @return the value of t_evaluate_label.FLabel
     *
     * @mbg.generated
     */
    public String getLabel() {
        return label;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_evaluate_label.FLabel
     *
     * @param label the value for t_evaluate_label.FLabel
     *
     * @mbg.generated
     */
    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_evaluate_label
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        EvaluateLabel other = (EvaluateLabel) that;
        return (this.getID() == null ? other.getID() == null : this.getID().equals(other.getID()))
            && (this.getEvaluateID() == null ? other.getEvaluateID() == null : this.getEvaluateID().equals(other.getEvaluateID()))
            && (this.getLabel() == null ? other.getLabel() == null : this.getLabel().equals(other.getLabel()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_evaluate_label
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getID() == null) ? 0 : getID().hashCode());
        result = prime * result + ((getEvaluateID() == null) ? 0 : getEvaluateID().hashCode());
        result = prime * result + ((getLabel() == null) ? 0 : getLabel().hashCode());
        return result;
    }
}