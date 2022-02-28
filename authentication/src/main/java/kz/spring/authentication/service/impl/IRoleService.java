package kz.spring.authentication.service.impl;

import kz.spring.authentication.model.Customer;
import kz.spring.authentication.model.Role;

import java.util.List;

public interface IRoleService {
//    Role saveRole(Role role);

    void addRoleToCustomer(Customer customer, String roleName);
//    void addRoleToCustomer(String email, String roleName);
//
//    List<Role> getRoles();
//
//    public Role saveRole(Role role);
}
