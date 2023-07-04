package com.example.codesharing;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
public class shareCodeController {
    @GetMapping("/sharecode")
    public String sharing(@RequestParam("text") String text){
        //System.out.println(text);
        String ID;
        while(true){
            ID = generateID();
            if(OperateDB.idNotExist(ID)){
                break;
            }
            else{
                System.out.println("ID conflict: " + ID);
            }
        }

        if(OperateDB.insertData(ID, text))
            return ID;
        return "Failed";
    }

    private String generateID(){
        UUID uuid = UUID.randomUUID();
        String uuidstr = uuid.toString().substring(0,4);
        //System.out.println(uuidstr);
        return uuidstr;
    }

    @GetMapping("/pull")
    public String pullCode(@RequestParam("codekey")String codekey){
        try{
            return OperateDB.getCodeByID(codekey);
        }
        catch (Exception e){
            System.out.println(e.toString());
            return "Key is invalid";
        }
    }
}
