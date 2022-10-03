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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HelloJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")
                .start(helloStep1())
                .next(helloStep2()) // 그다음 step을 지정해주는 api
                .build();
    }

    /**
     * Step는 tasklet을 무한 반복시킴 그래서 RepeatStatus 값을 지정해줘야됨
     * null일 경우 기본적으로 한번실행하고 종료됨 RepeatStatus.FINISHED도 Null과 같은 의미
     * */

    @Bean
    public Step helloStep1() {
        return stepBuilderFactory.get("helloStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                        System.out.println("================================");
                        System.out.println(" >> Hello Spring Batch!");
                        System.out.println("================================");

                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
    @Bean
    public Step helloStep2() {
        return stepBuilderFactory.get("helloStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

                        System.out.println("================================");
                        System.out.println(" step2 was executed ");
                        System.out.println("================================");

                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

}
