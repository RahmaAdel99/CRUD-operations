
package com.spring.restful.Controller;

import com.spring.restful.Repo.ProjectRepo;
import com.spring.restful.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
//@RequestMapping("employees")
public class EmployeeController {


    @Autowired
    private ProjectRepo projectrepo;


    @GetMapping("/projects")
    public List<Project> getprojects() {
        return projectrepo.findAll();
    }


    @PostMapping("/save")
    public String saveprojects(@RequestBody Project project) {
        projectrepo.save(project);
        return "saved";
    }

    @PutMapping(value = "update/{id}")
    public String updateproject(@PathVariable long id, @RequestBody Project project) {
        Project updatedproject = projectrepo.findById(id).get();
        updatedproject.setFirstName(project.getFirstName());
        updatedproject.setLastName(project.getLastName());
        updatedproject.setAge(project.getAge());
        updatedproject.setJob(project.getJob());
        projectrepo.save(updatedproject);
        return "updated";
    }

    @DeleteMapping(value = "delete/{id}")
    public String deleteproject(@PathVariable long id) {
        Project deletedproject = projectrepo.findById(id).get();
        projectrepo.delete(deletedproject);
        return "delete project with id :" + id;
    }
}

/*    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/test")
    public String getpage() {
        return "welcome";
    }

    @RequestMapping(value = "/templete/test" , method = RequestMethod.POST)
    public String createprojects(@RequestBody Project project){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Project> entity = new HttpEntity<Project>(project,headers);

        return restTemplate.exchange("http://localhost:8080/test", HttpMethod.GET,entity,String.class).getBody();

    }*/