package com.example.wlplatform.entity;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table user_group
 */
public class UserGroup {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_group.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   ����
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_group.group_name
     *
     * @mbg.generated
     */
    private String groupName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_group.id
     *
     * @return the value of user_group.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_group.id
     *
     * @param id the value for user_group.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_group.group_name
     *
     * @return the value of user_group.group_name
     *
     * @mbg.generated
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_group.group_name
     *
     * @param groupName the value for user_group.group_name
     *
     * @mbg.generated
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }
}