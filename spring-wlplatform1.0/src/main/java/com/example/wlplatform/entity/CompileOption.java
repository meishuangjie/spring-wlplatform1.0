package com.example.wlplatform.entity;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table compile_option
 */
public class CompileOption {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column compile_option.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   ͨѶ����
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column compile_option.communication_type
     *
     * @mbg.generated
     */
    private String communicationType;

    /**
     * Database Column Remarks:
     *   Э������
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column compile_option.protocol_name
     *
     * @mbg.generated
     */
    private String protocolName;

    /**
     * Database Column Remarks:
     *   �ͻ�����
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column compile_option.library
     *
     * @mbg.generated
     */
    private String library;

    /**
     * Database Column Remarks:
     *   ���ӹ���
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column compile_option.regulation
     *
     * @mbg.generated
     */
    private String regulation;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column compile_option.id
     *
     * @return the value of compile_option.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column compile_option.id
     *
     * @param id the value for compile_option.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column compile_option.communication_type
     *
     * @return the value of compile_option.communication_type
     *
     * @mbg.generated
     */
    public String getCommunicationType() {
        return communicationType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column compile_option.communication_type
     *
     * @param communicationType the value for compile_option.communication_type
     *
     * @mbg.generated
     */
    public void setCommunicationType(String communicationType) {
        this.communicationType = communicationType == null ? null : communicationType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column compile_option.protocol_name
     *
     * @return the value of compile_option.protocol_name
     *
     * @mbg.generated
     */
    public String getProtocolName() {
        return protocolName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column compile_option.protocol_name
     *
     * @param protocolName the value for compile_option.protocol_name
     *
     * @mbg.generated
     */
    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName == null ? null : protocolName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column compile_option.library
     *
     * @return the value of compile_option.library
     *
     * @mbg.generated
     */
    public String getLibrary() {
        return library;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column compile_option.library
     *
     * @param library the value for compile_option.library
     *
     * @mbg.generated
     */
    public void setLibrary(String library) {
        this.library = library == null ? null : library.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column compile_option.regulation
     *
     * @return the value of compile_option.regulation
     *
     * @mbg.generated
     */
    public String getRegulation() {
        return regulation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column compile_option.regulation
     *
     * @param regulation the value for compile_option.regulation
     *
     * @mbg.generated
     */
    public void setRegulation(String regulation) {
        this.regulation = regulation == null ? null : regulation.trim();
    }
}