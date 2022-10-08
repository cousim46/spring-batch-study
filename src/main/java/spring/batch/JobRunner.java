package spring.batch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

//@Component
public class JobRunner implements ApplicationRunner {

    // @Autowired
    private JobLauncher jobLauncher;
    //@Autowired
    private Job job;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        jobLauncher.run(job, new JobParametersBuilder().addString("name", "user2").toJobParameters());
    }
}
