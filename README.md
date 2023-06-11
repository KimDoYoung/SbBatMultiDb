# Spring boot & Spring batch for from db to db

## 개요

1. mariadb의 테이블(words) 조회 postgresql 의 테이블(words)로 데이터 옮기기
   words 테이블은 사전과 비슷한 내용으로  id-한글-영어-한자 단어 목록임  
2. 2개의 datasource
3. spring boot + spring batch
4. mybatis 사용 
   -  MyBatisBatchItemWriter, MyBatisCursorItemReader 사용
5. command line argument 지원

## packages
 
- batchjob : spring batch 관련 소스
- config : 2개의 DataSource 관련 config
- model : DTO에 해당하는 Words, 단 SrcWords와 DesWords로 나눔
- respository : Mapper 클래스

## files
- JobRunner : args처리, job 호출(JobWords)
- application.properties : datasources(src, des) 정보 기술
- mybatis 폴더 : mapper xml, mybatis-config.xml


## words table schema

- mariadb

```sql
CREATE TABLE `words` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `han` varchar(300) COLLATE utf8mb4_unicode_ci NOT NULL,
  `eng` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `china` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `note` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updateDt` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=299 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

```

- postgresql

```sql
create table words (
 id serial PRIMARY KEY,
 han varchar(300) not null,
 eng varchar(300) null,
 china varchar(300) null,
 note text ,
 updateDt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

```