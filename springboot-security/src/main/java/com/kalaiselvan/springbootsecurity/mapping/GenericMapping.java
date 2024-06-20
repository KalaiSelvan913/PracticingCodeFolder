package com.kalaiselvan.springbootsecurity.mapping;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;


@Component
public class GenericMapping {
	
	ModelMapper mapper ;
	
	public GenericMapping() {
		
		mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
	}
	
	public<O,I> O map(final I entity, Class<O> outClass) {
		return mapper.map(entity, outClass);
	}
	
	public <O,I> List<O> mapAll(final Collection<I> entityList, Class<O> outClass){
		return entityList.stream()
				.map(entity -> map(entity, outClass))
				.collect(Collectors.toList());
	}
	
	public <I,O> void map(final I source, O destination) {
		mapper.map(source, destination);
	}
	
	
	

}
