package kr.co.kalpa.batch.multi.sbbatmultidb.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
//@MapperScan(value="kr.co.kalpa.batch.multi.sbbatmultidb.repository", sqlSessionFactoryRef = "srcSqlSessionFactory")
@MapperScan(value="kr.co.kalpa.batch.multi.sbbatmultidb.repository.src", sqlSessionFactoryRef = "srcSqlSessionFactory")
@EnableTransactionManagement
public class SrcDatabaseConfig {

    @Bean(name="srcDataSource")
    @ConfigurationProperties(prefix="spring.src.datasource")
    @Primary
    public DataSource srcDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name="srcSqlSessionFactory")
    @Primary
    public SqlSessionFactory srcSqlSessionFactory(@Qualifier("srcDataSource") DataSource srcDataSource,
                                                  ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(srcDataSource);

        sqlSessionFactoryBean.setConfigLocation(
                new PathMatchingResourcePatternResolver()
                        .getResource("classpath:mybatis/mybatis-config.xml"));

        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:mybatis/mapper/src/*.xml"));

        //sqlSessionFactoryBean.setMapperLocations(applicationContext.getResource("classpath:mybatis-mapper/src/*-mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

}
