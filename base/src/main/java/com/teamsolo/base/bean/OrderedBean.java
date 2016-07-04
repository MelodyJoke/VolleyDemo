package com.teamsolo.base.bean;

/**
 * description: bean super class with an id
 * author: Melody
 * date: 2016/6/17
 * version: 0.0.0.1
 */
@SuppressWarnings("unused, WeakerAccess")
public abstract class OrderedBean extends Bean {

    /**
     * id, to order
     */
    protected String id;

    public OrderedBean() {
    }

    public OrderedBean(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof OrderedBean && id != null && id.equals(((OrderedBean) o).id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
