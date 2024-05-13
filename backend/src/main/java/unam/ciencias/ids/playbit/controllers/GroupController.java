package unam.ciencias.ids.playbit.controllers;

import unam.ciencias.ids.playbit.models.*;
import unam.ciencias.ids.playbit.repositories.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
@CrossOrigin
public class GroupController {
   
    @Autowired
    GroupRepository groupRepository;


    @PostMapping("/create/")
    public void createGroup(@RequestBody Group group){
        List<Group> groups = groupRepository.getGroupById(group.getId());
            
        if(groups.size() > 0)
            throw new IllegalArgumentException("Group already exists");
        
        groupRepository.save(group);
    }


    @PostMapping("/edit/")
    public void editGroup(@RequestBody Group group){
        List<Group> groups = groupRepository.getGroupById(group.getId());

        if(groups.size() == 0)
            throw new IllegalArgumentException("Group does not exist");

        groupRepository.delete(groups.get(0));
        groupRepository.save(group);
    }


    @PostMapping("/delete/")
    public void deleteGroup(@RequestBody Group group){
        List<Group> groups = groupRepository.getGroupById(group.getId());

        if(groups.size() == 0)
            throw new IllegalArgumentException("Group does not exist");
        
        groupRepository.delete(groups.get(0));
    }

    
}
