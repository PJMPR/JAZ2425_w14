package com.users.scheduler;

import com.users.updater.IUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UpdaterJob implements Job {

    private final IUpdate updater;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        updater.update(1);
        log.info("Wykonanie zadania Quartz: " + System.currentTimeMillis());
    }
}
