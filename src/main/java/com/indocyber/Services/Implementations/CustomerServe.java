package com.indocyber.Services.Implementations;

import com.indocyber.DTO.Customer.UpsertCustomerDTO;
import com.indocyber.Entities.Customer;
import com.indocyber.ExceptionHandling.ObjectNotFound;
import com.indocyber.Repositories.CustomerRepository;
import com.indocyber.Services.Interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServe implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private int ROWS_PER_PAGE = 5;

    @Override
    public void addCustomer(UpsertCustomerDTO dto) {
        Customer newCustomer = new Customer();
        newCustomer.setMembershipNumber(dto.getMembershipNumber());
        newCustomer.setFirstName(dto.getFirstName());
        newCustomer.setLastName(dto.getLastName());
        newCustomer.setBirthDate(dto.getBirthDate());
        newCustomer.setGender(dto.getGender());
        newCustomer.setPhone(dto.getPhone());
        newCustomer.setAddress(dto.getAddress());
        dto.setMembershipExpireDate(LocalDate.now().plusYears(2));
        newCustomer.setMembershipExpireDate(dto.getMembershipExpireDate());

        customerRepository.save(newCustomer);

    }

    @Override
    public Page<Customer> findAllCustomer(Integer page) {

        Pageable pageable = PageRequest.of( page - 1,ROWS_PER_PAGE, Sort.by("membershipNumber"));

        return customerRepository.findAll(pageable);
    }

    @Override
    public List<UpsertCustomerDTO> findByName(String name, Integer page) {
        Pageable pagination = PageRequest.of(page - 1, ROWS_PER_PAGE, Sort.by("membershipNumber") );

        return customerRepository.findByName(name,pagination);
    }

    @Override
    public List<UpsertCustomerDTO> findByMembershipNumber(String membershipNumber, Integer page) {

        Pageable pageable = PageRequest.of(page - 1 , ROWS_PER_PAGE, Sort.by("membershipNumber"));

        return customerRepository.findByMembershipNumber(membershipNumber,pageable) ;
    }

    @Override
    public UpsertCustomerDTO updateCustomer(String membershipNumber){
        Optional<Customer> nullEntity = customerRepository.findById(membershipNumber);
        Customer entity = nullEntity.get();
        UpsertCustomerDTO updateDto = new UpsertCustomerDTO(
                entity.getMembershipNumber(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthDate(),
                entity.getGender(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getMembershipExpireDate()
        );
        return  updateDto;
    }

    @Override
    public Customer findByCustomerId(String membershipNumber) {
        Customer entity = customerRepository.findById(membershipNumber).
                orElseThrow(() ->{
                    throw new ObjectNotFound("Customer with membershipNumber : " + membershipNumber + "Not Found!");
                }
        );

        return entity;
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.deleteById(customer.getMembershipNumber());
    }
}
