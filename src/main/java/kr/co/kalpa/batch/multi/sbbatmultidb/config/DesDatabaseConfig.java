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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
//@MapperScan(value="kr.co.kalpa.batch.multi.sbbatmultidb.repository", sqlSessionFactoryRef = "desSqlSessionFactory")
@MapperScan(value="kr.co.kalpa.batch.multi.sbbatmultidb.repository.des", sqlSessionFactoryRef = "desSqlSessionFactory")
@EnableTransactionManagement
public class DesDatabaseConfig {

    @Bean(name="desDataSource")
    @ConfigurationProperties(prefix="spring.des.datasource")
    public DataSource desDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name="desSqlSessionFactory")
    @Qualifier("desSqlSessionFactory")
    public SqlSessionFactory desSqlSessionFactory(@Qualifier("desDataSource") DataSource desDataSource,
                                                  ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(desDataSource);

        sqlSessionFactoryBean.setConfigLocation(
                new PathMatchingResourcePatternResolver()
                        .getResource("classpath:mybatis/mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                    .getResources("classpath:mybatis/mapper/des/*.xml"));
        //sqlSessionFactoryBean.setMapperLocations(applicationContext.getResource("classpath:mybatis-mapper/des/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

//    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setConfigLocation(
//                new PathMatchingResourcePatternResolver()
//                    .getResource("classpath:mybatis/mybatis-config.xml"));
//        sqlSessionFactoryBean.setMapperLocations(
//                new PathMatchingResourcePatternResolver()
//                    .getResources("classpath:mapper/*.xml"));
//        return sqlSessionFactoryBean.getObject();
}
