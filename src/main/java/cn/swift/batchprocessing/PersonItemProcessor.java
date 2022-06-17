package cn.swift.batchprocessing;

import org.springframework.batch.item.ItemProcessor;

import cn.swift.batchprocessing.exception.SkipException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(Person person) throws Exception {
        int id = person.getId();
        String firstName = person.getFirstName().toUpperCase();
        String lastName = person.getLastName().toUpperCase();

        if(id == 2) {
            throw new SkipException("test skip");
        }
        
        Person transformedPerson = new Person(id, firstName, lastName);
//        log.info("Converting (" + person + ") into (" + transformedPerson);

        return transformedPerson;
    }

}
