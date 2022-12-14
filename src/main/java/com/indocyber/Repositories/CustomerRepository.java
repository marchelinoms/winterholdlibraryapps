package com.indocyber.Repositories;

import com.indocyber.DTO.Customer.UpsertCustomerDTO;
import com.indocyber.Entities.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,String> {
    @Query("""
			SELECT new com.indocyber.DTO.Customer.UpsertCustomerDTO(cus.membershipNumber, cus.firstName, cus.lastName, cus.birthDate, cus.gender, cus.phone, cus.address, cus.membershipExpireDate) 
			FROM Customer AS cus
			WHERE cus.firstName LIKE %:firstName% 
				OR cus.lastName LIKE %:lastName%
				AND (:membershipNumber IS NULL OR cus.membershipNumber = :membershipNumber)
				 """)
    public List<UpsertCustomerDTO> findByName(@Param("firstName") String name, Pageable pageable);

	@Query("""
			SELECT new com.indocyber.DTO.Customer.UpsertCustomerDTO(cus.membershipNumber, cus.firstName, cus.lastName, cus.birthDate, cus.gender, cus.phone, cus.address, cus.membershipExpireDate) 
			FROM Customer AS cus
			WHERE  (:membershipNumber IS NULL OR cus.membershipNumber = :membershipNumber)
				 """)
	public List<UpsertCustomerDTO> findByMembershipNumber(@Param("membershipNumber") String membershipNumber, Pageable pageable);

}
