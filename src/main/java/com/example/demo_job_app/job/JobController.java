package com.example.demo_job_app.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//Request mapping annotation {alternative way. two parameters, 1st parameter::value --> specify the path
//                                                             2nd parameter::method-->request type}
//When request mapping is applied in a class level, it sets a base URL path for all the methods that are handling request in that particular controller
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    /*    Request mapping annotation helps to reduce repetition, in the future if you want to change the base URL,
        you just   have to change in one place and is applied for all the controller rather than going in every request(method) and change it individually */
    //    @GetMapping("/jobs")
    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    //    @PostMapping("/jobs")
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
//        return new ResponseEntity<>("Job created successfully",  HttpStatus.CREATED);
        return new ResponseEntity<>("Job created successfully", HttpStatus.CREATED);
    }

    //    @GetMapping("/jobs/{id}")
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {

        Job job = jobService.getJobById(id);
        if (job != null)
            return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //    @DeleteMapping("/jobs/{id}")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        boolean deleted = jobService.deleteJobById(id);
        if (deleted)
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //    @PutMapping("/jobs/{id}")
    @PutMapping("/{id}")
    //@RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        //updateJob method parameters: id-->which job to update
        //                             updateJob-->what to update
        boolean updated = jobService.updateJob(id, updatedJob);
        if (updated)
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
