package com.example.wlplatform.entity;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table user_info
 */

public class UserInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   �û���
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     * Database Column Remarks:
     *   ����
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.passwd
     *
     * @mbg.generated
     */
    private String passwd;

    /**
     * Database Column Remarks:
     *   ����id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.group_id
     *
     * @mbg.generated
     */
    private Byte groupId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.id
     *
     * @return the value of user_info.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.id
     *
     * @param id the value for user_info.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.name
     *
     * @return the value of user_info.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.name
     *
     * @param name the value for user_info.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.passwd
     *
     * @return the value of user_info.passwd
     *
     * @mbg.generated
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.passwd
     *
     * @param passwd the value for user_info.passwd
     *
     * @mbg.generated
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.group_id
     *
     * @return the value of user_info.group_id
     *
     * @mbg.generated
     */
    public Byte getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.group_id
     *
     * @param groupId the value for user_info.group_id
     *
     * @mbg.generated
     */
    public void setGroupId(Byte groupId) {
        this.groupId = groupId;
    }
}