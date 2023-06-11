package kr.co.kalpa.batch.multi.sbbatmultidb;

import kr.co.kalpa.batch.multi.sbbatmultidb.model.SrcWords;
import kr.co.kalpa.batch.multi.sbbatmultidb.repository.src.SrcWordsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Slf4j
public class JobRunner implements CommandLineRunner {

    @Autowired
    private JobLauncher jobLanuner;

    @Autowired
    private Job jobWords;

    @Override
    public void run(String... args) throws Exception {
        log.info("job launcher started");
        JobParameters jobParameter = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLanuner.run(jobWords, jobParameter);
        log.debug("job execution done");
    }
}