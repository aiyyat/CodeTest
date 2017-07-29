package com.technicalyorker.deal.dealservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.technicalyorker.deal.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
