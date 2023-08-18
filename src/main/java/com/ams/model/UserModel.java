/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.model;

import java.util.List;
import org.primefaces.model.SelectableDataModel;
import javax.faces.model.ListDataModel;

public class UserModel extends ListDataModel<Client> implements SelectableDataModel<Client> {

    public UserModel() {
    }

    public UserModel(List<Client> userList) {
        super(userList);
    }

    @Override
    public Object getRowKey(Client user) {
        return user.getId();
    }

    @Override
    public Client getRowData(String rowKey) {
        List<Client> userList = (List<Client>) getWrappedData();
        for (Client user : userList) {
            if (user.getId().equals(rowKey)) {
                return user;
            }
        }
        return null;
    }
}
