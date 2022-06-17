package cn.swift.batchprocessing;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import cn.swift.batchprocessing.exception.SkipException;
import cn.swift.batchprocessing.listener.step.DummyChunkListener;
import cn.swift.batchprocessing.listener.step.DummyItemProcessListener;
import cn.swift.batchprocessing.listener.step.DummyItemReadListener;
import cn.swift.batchprocessing.listener.step.DummyItemWriteListener;
import cn.swift.batchprocessing.listener.step.DummySkipListener;
import cn.swift.batchprocessing.listener.step.DummyStepExecutionListener;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Bean
    public FlatFileItemReader<Person> reader() {
        return new FlatFileItemReaderBuilder<Person>()
        .name("personItemReader")
        .resource(new ClassPathResource("sample-data.csv"))
        .delimited()
        .names("id", "firstName", "lastName")
        .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{setTargetType(Person.class);}})//NOSONAR
        .build();
    }
    
    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }
    
    @Bean
    public JdbcBatchItemWriter<Person> writer(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Person>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO people (id, first_name, last_name) VALUES (:id, :firstName, :lastName)")
                .dataSource(dataSource)
                .build();
    }
    
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1,
            JobBuilderFactory jobBuilderFactory) {
      return jobBuilderFactory.get("importUserJob")
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .flow(step1)
        .end()
        .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Person> writer,
            StepBuilderFactory stepBuilderFactory) {
      return stepBuilderFactory.get("step1")
        .<Person, Person> chunk(10)
        .writer(writer)
        .reader(reader())
        .processor(processor())
        .faultTolerant()
        .noRetry(SkipException.class)
        .skipLimit(1)
        .skip(SkipException.class)
        .listener(new DummySkipListener())
        .listener(new DummyItemReadListener())
        .listener(new DummyItemProcessListener())
        .listener(new DummyItemWriteListener())
        .listener(new DummyStepExecutionListener())
        .listener(new DummyChunkListener())
        .build();
    }
    
}
