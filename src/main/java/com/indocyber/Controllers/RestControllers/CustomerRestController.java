package com.indocyber.Controllers.RestControllers;

import com.indocyber.DTO.Customer.CustomerDto;
import com.indocyber.DTO.Customer.UpsertCustomerDTO;
import com.indocyber.Entities.Customer;
import com.indocyber.Services.Interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> addCustomer(@Valid @RequestBody UpsertCustomerDTO dto){

        customerService.addCustomer(dto);

        return new ResponseEntity<>("Data has been inserted!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Customer>> getAllCustomer (@RequestParam(defaultValue = "1") Integer page){

        Page<Customer> customers = customerService.findAllCustomer(page);


        return new ResponseEntity<>(customers,HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<Object> put (@Valid @RequestBody UpsertCustomerDTO dto, BindingResult bindingResult){

        if(!bindingResult.hasErrors()){
            customerService.addCustomer(dto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
        } else{
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Validation Failed, Http Request Body is not validated.");
        }
    }

    @DeleteMapping("/{membershipNumber}")
    public ResponseEntity<Object> deleteCustomer (@PathVariable String membershipNumber){
        Customer customer = new Customer();
        customer.setMembershipNumber(membershipNumber);
        customerService.deleteCustomer(customer);

        return ResponseEntity.status(HttpStatus.OK).body("The following customer has been deleted : " + membershipNumber);

    }
}
