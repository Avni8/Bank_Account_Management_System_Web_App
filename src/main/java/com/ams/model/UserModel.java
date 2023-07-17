/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.model;

import java.util.List;
import org.primefaces.model.SelectableDataModel;
import javax.faces.model.ListDataModel;

public class UserModel extends ListDataModel<User> implements SelectableDataModel<User> {

    public UserModel() {
    }

    public UserModel(List<User> userList) {
        super(userList);
    }

    @Override
    public Object getRowKey(User user) {
        return user.getId();
    }

    @Override
    public User getRowData(String rowKey) {
        List<User> userList = (List<User>) getWrappedData();
        for (User user : userList) {
            if (user.getId().equals(rowKey)) {
                return user;
            }
        }
        return null;
    }
}
