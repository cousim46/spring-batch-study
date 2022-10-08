package spring.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

//@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    // @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(st1())
                .next(st2())
                .build();
    }

    // @Bean
    public Step st1() {
        return stepBuilderFactory.get("st1")
                .tasklet(
                        new Tasklet() {
                            @Override
                            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                                System.out.println("st1 was executed");
                                return RepeatStatus.FINISHED;
                            }
                        }
                ).build();
    }

    //@Bean
    public Step st2() {
        return stepBuilderFactory.get("st2")
                .tasklet(
                        new Tasklet() {
                            @Override
                            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                                System.out.println("st12 was executed");
                                return RepeatStatus.FINISHED;
                            }
                        }
                ).build();
    }


}
