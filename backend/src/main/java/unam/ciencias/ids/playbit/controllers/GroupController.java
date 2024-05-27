package unam.ciencias.ids.playbit.controllers;

import unam.ciencias.ids.playbit.models.*;
import unam.ciencias.ids.playbit.repositories.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/group")
@CrossOrigin
public class GroupController {
   
    @Autowired
    GroupRepository groupRepository;

    @GetMapping("/all/")
    public List<Group> findAll(){
        return (List<Group>) groupRepository.findAll();
    }


    @PostMapping("/create/")
    @Transactional
    public int createGroup(@RequestBody Group[] groups){
        groupRepository.saveAll(Arrays.asList(groups));
        return 1;
    }


    @PostMapping("/edit/")
    public void editGroup(@RequestBody Group group){
        List<Group> groups = groupRepository.getGroupById(group.getId());

        if(groups.size() == 0)
            throw new IllegalArgumentException("Group does not exist");
        groupRepository.save(group);
    }


    @PostMapping("/delete/")
    public void deleteGroup(@RequestBody Group group){
        List<Group> groups = groupRepository.getGroupById(group.getId());

        if(groups.size() == 0)
            throw new IllegalArgumentException("Group does not exist");
        
        groupRepository.delete(groups.get(0));
    }


    @GetMapping("/find/{id}")
    public List<Group> findGroup(@PathVariable int id){
        List<Group> groups = groupRepository.getGroupById(id);

        if (groups.size() == 0)
            throw new IllegalArgumentException("Group does not exist");


        return groups;
    }

    
}
