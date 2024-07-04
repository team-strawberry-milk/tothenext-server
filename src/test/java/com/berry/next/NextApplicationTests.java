package com.berry.next;

import com.berry.next.account.storage.AccountEntity;
import com.berry.next.account.storage.AccountJpaRepository;
import com.berry.next.profile.storage.LanguageEntity;
import com.berry.next.profile.storage.LanguageJpaRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
class NextApplicationTests {

	@Autowired private EntityManager em;
	@Autowired private AccountJpaRepository accountJpaRepository;
	@Autowired private LanguageJpaRepository languageJpaRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void Language_No_Constraint_Create() {
		AccountEntity account = accountJpaRepository.save(
				AccountEntity.builder()
						.email("abc@gmail.com")
						.password("password")
						.campus("이화여자대학교")
						.isCampusCertificated(true)
						.build()
		);

		LanguageEntity language = languageJpaRepository.save(
				LanguageEntity.builder()
						.rank("IH")
						.certificate("Opic English")
						.issuer("Opic")
						.acquiredAt(LocalDate.now())
						.account(account)
						.build()
		);
	}

}
