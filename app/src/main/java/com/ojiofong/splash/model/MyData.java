package com.ojiofong.splash.model;


import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;

/**
 * Created by ojiofong on 7/22/16.
 */

@Generated("org.jsonschema2pojo")
public class MyData {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    /**
     * No args constructor for use in serialization
     */
    public MyData() {
    }

    /**
     * @param id
     * @param body
     * @param title
     * @param userId
     */
    public MyData(Integer userId, Integer id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    /**
     * @return The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId The userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body The body
     */
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
