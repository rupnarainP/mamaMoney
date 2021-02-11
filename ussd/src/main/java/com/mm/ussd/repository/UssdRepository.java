package com.mm.ussd.repository;

import com.mm.ussd.entity.UssdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UssdRepository extends JpaRepository<UssdEntity, Integer> {
    UssdEntity findBySessionId(String sessionId);
    UssdEntity findByMsisdn(String msidn);
    UssdEntity findByUserEntry(String userEntry);
}
