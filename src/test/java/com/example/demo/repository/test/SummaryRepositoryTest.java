package com.example.demo.repository.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.demo.model.Summary;
import com.example.demo.model.User;
import com.example.demo.repository.SummaryRepository;

@DataJpaTest
public class SummaryRepositoryTest {
	
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private SummaryRepository summaryRepository;
    
    User user1;
    
    @BeforeEach
    public void setUp() {
    	

        user1 = new User();
        user1.setName("taro");
        entityManager.persist(user1);
    
        Summary summary1 = new Summary();
        summary1.setUser(user1);
        summary1.setYear(1900);
        summary1.setMonth(12);
        summary1.setGenre("衣服");
        summary1.setStatus(true);
        entityManager.persist(summary1);
        
        Summary summary2 = new Summary();
        summary2.setUser(user1);
        summary2.setYear(1900);
        summary2.setMonth(12);
        summary2.setGenre("衣服");
        summary2.setStatus(true);
        entityManager.persist(summary2);
        
        Summary summary3 = new Summary();
        summary3.setUser(user1);
        summary3.setYear(2000);
        summary3.setMonth(10);
        summary3.setGenre("衣服");
        summary3.setStatus(false);
        entityManager.persist(summary3);
        
        
        Summary summary4 = new Summary();
        summary4.setUser(user1);
        summary4.setMonth(2);
        summary4.setStatus(true);
        summary4.setGenre("衣服");
        entityManager.persist(summary4);
    }
    
    @Test
	public void testfindByUser() {
		List<Summary> summaries = summaryRepository. findByStatusTrueAndUserAndYearAndMonth(user1, 1900, 12);
		assertThat(summaries.size()).isEqualTo(2);
	}
    
    
    
    @Test
	public void testfindByMonth() {
		List<Summary> summaries = summaryRepository. findByStatusFalseAndUserAndYearAndMonth(user1, 2000, 10);
		assertThat(summaries.size()).isEqualTo(1);
	}
        
}
