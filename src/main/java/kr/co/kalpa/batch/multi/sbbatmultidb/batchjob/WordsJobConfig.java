package kr.co.kalpa.batch.multi.sbbatmultidb.batchjob;

import kr.co.kalpa.batch.multi.sbbatmultidb.model.DesWords;
import kr.co.kalpa.batch.multi.sbbatmultidb.model.SrcWords;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.mybatis.spring.batch.builder.MyBatisCursorItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@EnableBatchProcessing
@Configuration
@Slf4j
public class WordsJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job jobWords(){
        return jobBuilderFactory.get("job-words")
                .incrementer(new RunIdIncrementer())
                .listener(listenerWords())
                .start(stepWords())
                .build();
    }

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Bean
    public Step stepWords() {
       return  stepBuilderFactory.get("step-words")
                .<SrcWords, DesWords>chunk(10)
                .reader(readerWords())
                .processor(processorWords())
                .writer(writerWords())
                .build();
    }

    @Autowired
    @Qualifier("desSqlSessionFactory")
    private SqlSessionFactory desSqlSessionFactory;
    @Bean
    public MyBatisBatchItemWriter<DesWords> writerWords() {
        return new MyBatisBatchItemWriterBuilder<DesWords>()
                .sqlSessionFactory(desSqlSessionFactory)
                .statementId("insert")
                .build();
    }

//    @Autowired
//    @Qualifier("desDataSource")
//    private DataSource desDataSource;
//    @Bean
//    //3. writer
//    public JdbcBatchItemWriter<DesWords> writerWords(){
//        log.debug("desDataSource : "+ desDataSource.getClass().getName());
//        return new JdbcBatchItemWriterBuilder<DesWords>()
//                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//                .sql("insert into words(han,eng,china) values (:han,:eng,:china)")
//                .dataSource(desDataSource)
//                .build();
//    }

    @Bean
    public ItemProcessor<SrcWords, DesWords> processorWords() {
        return new ProcessorWords();
    }

    @Autowired
    private SqlSessionFactory srcSqlSessionFactory;
    @Bean
    public MyBatisCursorItemReader<SrcWords> readerWords() {
            String strQueryId = "selectAll";
            Map<String, Object> parameterValues = new HashMap<>();
            return new MyBatisCursorItemReaderBuilder<SrcWords>()
                    .sqlSessionFactory(srcSqlSessionFactory)
                    .queryId(strQueryId)
                    .parameterValues(parameterValues)
                    .saveState(false)
                    .build();
    }

    @Bean
    public JobExecutionListener listenerWords() {
        return new WordsJobExecutionListener();
    }
}
