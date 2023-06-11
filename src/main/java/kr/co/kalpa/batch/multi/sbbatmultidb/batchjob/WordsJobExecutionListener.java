package kr.co.kalpa.batch.multi.sbbatmultidb.batchjob;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;


public class WordsJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("작업 수행 전에....");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("작업 수행 후에....");
    }
}
