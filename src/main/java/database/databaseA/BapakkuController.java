/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.databaseA;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
@CrossOrigin
//@ResponseBody
public class BapakkuController {
    
    A2020 data = new A2020();
    A2020JpaController control = new A2020JpaController();

    
    @GetMapping(value="/GET", produces = APPLICATION_JSON_VALUE)
    public List<A2020> getNama(){
        List<A2020> buffer = new ArrayList<>();
        buffer = control.findA2020Entities();
        return buffer;
    }

    @PostMapping(value = "/POST", consumes = APPLICATION_JSON_VALUE)
    public String sendData(HttpEntity<String> datasend) throws JsonProcessingException{
        ObjectMapper export = new ObjectMapper();
        String feedback = "Data";
        data = export.readValue(datasend.getBody(), A2020.class);
        try {
            control.create(data);
            feedback = data.getNama()+ "saved";
        } catch (Exception error) {
            feedback = error.getMessage();
        }
        return feedback;
    }
    
    @PutMapping(value = "/PUT", consumes = APPLICATION_JSON_VALUE)
    public String putData(HttpEntity<String> datasend) throws JsonProcessingException{
        ObjectMapper export = new ObjectMapper();
        String feedback = "Data";
        data = export.readValue(datasend.getBody(), A2020.class);
        try {
            control.edit(data);
            feedback = data.getNama()+ "edited";
        } catch (Exception error) {
            feedback = error.getMessage();
        }
        return feedback;
    }
    
    @DeleteMapping(value = "/Delete", consumes = APPLICATION_JSON_VALUE)
    public String deleteData(HttpEntity<String> datasend) throws JsonProcessingException{
        ObjectMapper export = new ObjectMapper();
        String feedback = "Data";
        data = export.readValue(datasend.getBody(), A2020.class);
        try {
            control.destroy(data.getId());
            feedback = data.getNama()+ "deleted";
        } catch (Exception error) {
            feedback = error.getMessage();
        }
        return feedback;
    }
}
