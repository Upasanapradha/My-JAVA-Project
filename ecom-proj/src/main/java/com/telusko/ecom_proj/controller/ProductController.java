package com.telusko.ecom_proj.controller;

import com.telusko.ecom_proj.Model.Product;
import com.telusko.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
      return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {

        Product product = service.getProductById(id);
        if (product !=null)
         return new ResponseEntity<>(product, HttpStatus.OK);
        else
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
       public ResponseEntity<?> addProduct(@RequestPart Product product,@RequestPart MultipartFile imageFile) {
           try {
               Product product1 = service.addProduct(product, imageFile);
               return new ResponseEntity<>(product1, HttpStatus.CREATED);
           } catch (Exception e) {
               return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);

           }
       }
        @GetMapping("product/{productId}/image")
       public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {

        Product product = service.getProductById(productId);
        byte[] imageFile = product.getImageData();
        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);

        }
          @PutMapping("/product/{id}")
        public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile) {
              Product product1 = null;
              try {
                  product1 = service.updateProduct(id,product,imageFile);
              } catch (IOException e) {
                  throw new RuntimeException(e);
              }
              if (product1 != null)
                 return new ResponseEntity<>("updated",HttpStatus.OK);
             else return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);

        }

        @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
      Product product = service.getProductById(id);
      if (product != null) {
          service.deleteProduct(id);
          return new ResponseEntity<>("deleted", HttpStatus.OK);
      }
      else return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);

        }

       @GetMapping("/products/search")
        public ResponseEntity<List<Product>> searchProducts(String keyword) {
        System.out.println("Searching with keyword: " + keyword);
           List<Product> products = service.searchProducts(keyword);
           return new ResponseEntity<>(products, HttpStatus.OK);
        }
}
