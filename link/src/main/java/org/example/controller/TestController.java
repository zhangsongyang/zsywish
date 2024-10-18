package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Redick01
 */
@Slf4j
@RestController
@AllArgsConstructor
public class TestController {


    @GetMapping("/testJuc")
    public String testJuc() {
        System.out.println("123456789");
        return "testJucTp success";
    }

    @PostMapping("/testpagehelp")
    public String testpagehelp() {
        System.out.println("123456789");
        return "testJucTp success";
    }

}
