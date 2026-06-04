package com.example.SpringEcom.controller;

import com.example.SpringEcom.model.Product;
import com.example.SpringEcom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int id){
        Product product=productService.getProduct(id);
        if(product!=null)
        return new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @PostMa
        @PostMapping(
                value = "/product",
                consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<?> addProduct(
                @RequestPart("product") Product product,
                @RequestPart("imageFile") MultipartFile imageFile) {
            try {
                Product saved = productService.addProduct(product, imageFile);
                return new ResponseEntity<>(saved, HttpStatus.CREATED);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<>(e.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImageById(@PathVariable int id){
        Product product1= productService.getProduct(id);
        if(product1 !=null)
            return new ResponseEntity<>(product1.getImageData(),HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PutMapping(
            value = "/product/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateProduct(@PathVariable int id,
            @RequestPart("product") Product product,
            @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            Product updated = productService.updateProduct(product, imageFile);
            return new ResponseEntity<>("Updated", HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(
            value = "/product/{id}"
    )    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
            Product check = productService.getProduct(id);
            if(check !=null){
                productService.deleteProduct(id);
                return new ResponseEntity<>("Deleted", HttpStatus.CREATED);
            }
            else return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST);


    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product> products=productService.getProductBykeyword(keyword);
//        if(products==null)
//            return new ResponseEntity<>(new List<Product>,HttpStatus.BAD_REQUEST);
//        else
            return new ResponseEntity<>(products,HttpStatus.ACCEPTED);
    }
}
