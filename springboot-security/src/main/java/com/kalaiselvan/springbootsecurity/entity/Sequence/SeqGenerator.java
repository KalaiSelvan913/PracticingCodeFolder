package com.kalaiselvan.springbootsecurity.entity.Sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;


@Component
public class SeqGenerator {
	
	@Autowired
	EntityManager em;
	
	public  String generateSeqCode(String prefix) {
		StoredProcedureQuery sp = em.createStoredProcedureQuery("generate_code")
				.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT)
				.setParameter(1, prefix);
		
		sp.execute();
		return (String)sp.getOutputParameterValue(2);
	}

}
