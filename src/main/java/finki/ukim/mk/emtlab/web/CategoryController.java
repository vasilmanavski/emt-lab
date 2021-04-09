package finki.ukim.mk.emtlab.web;

import finki.ukim.mk.emtlab.model.enums.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoryController {

    @GetMapping
    public List<Category> categories(){
        return Arrays.asList(Category.values());
    }
}
