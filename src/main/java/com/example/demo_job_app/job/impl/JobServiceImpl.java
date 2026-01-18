package com.example.demo_job_app.job.impl;

import com.example.demo_job_app.job.Job;
import com.example.demo_job_app.job.JobService;

import java.util.ArrayList;
import java.util.List;

public class JobServiceImpl implements JobService {

    private List<Job> jobs = new ArrayList<>();

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {

    }
}
