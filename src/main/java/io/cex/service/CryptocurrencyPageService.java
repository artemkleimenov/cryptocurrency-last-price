package io.cex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import io.cex.Cryptocurrency;

@Service
public class CryptocurrencyPageService {
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
    public Page<Cryptocurrency> getAll(String name, Integer pageNumber, Integer pageSize) {
    	Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "lprice");
        
        final Query query = new Query()
                .with(paging)
                .addCriteria(Criteria.where("curr1").regex(name));
        
        return PageableExecutionUtils.getPage(
                mongoTemplate.find(query, Cryptocurrency.class),
                paging,
                () -> mongoTemplate.count(query.skip(0).limit(0), Cryptocurrency.class));
   }
	
}
