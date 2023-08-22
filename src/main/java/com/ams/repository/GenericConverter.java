/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.repository;

import com.ams.model.AbstractEntity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author avni
 */
public abstract class GenericConverter implements Converter {

    protected abstract AbstractRepository getRepo();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            return getRepo().findById(id);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof AbstractEntity) {
            AbstractEntity entity = (AbstractEntity) value;
            return String.valueOf(entity.getId());
        }
        return null;
    }
}
