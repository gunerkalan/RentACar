package com.gunerk.rentacar.repository.concrete;

import com.gunerk.rentacar.entities.concrete.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer getById(Long id);

    Customer getByCustomerTc(String customerTc);

    Customer getByCustomerTcAndIdNot(String Tc, Long id);
}
