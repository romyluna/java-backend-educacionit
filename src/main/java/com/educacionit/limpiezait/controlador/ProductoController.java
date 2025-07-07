package com.educacionit.limpiezait.controlador;

import com.educacionit.limpiezait.dominio.Producto;
import com.educacionit.limpiezait.servicio.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProductoController {



    private List<Producto> productos;
    private ProductoService productoService;

    public ProductoController(){
        this.productoService = new ProductoService();
    }


//BUSCAR TODOS
    @GetMapping("/productos")
    public List<Producto> getAllProductos(){
        return this.productoService.getAll();
    }
//BUSCAR POR ID
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id){
        Producto body = this.productoService.getById(id);
        if(body == null){
            return ResponseEntity
                    .notFound()
                    .build();//error 404.
        }
        return ResponseEntity
                .ok() //Crea una respuesta HTTP con código 200 OK.
                .body(body); //El cuerpo (body) es el producto encontrado (o null si no encontró ninguno).
    }

//CREATE
    @PostMapping("/productos")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto body = this.productoService.create(producto);
        if(body == null){
            return ResponseEntity
                    .badRequest()
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.CREATED) //Código 201 CREATED (que indica que se creó un recurso nuevo).
                .body(producto);
    }

//UPDATE - PUT
    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id ,
                                                   @RequestBody Producto producto){
        return ResponseEntity
                .ok(this.productoService.update(id, producto));
    }

//DELETE
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id){

        boolean resultado = this.productoService.delete(id);
        if(resultado)
        {
            return ResponseEntity
                    .noContent()// HTTP 204 : Significa que se eliminó correctamente, pero no hay nada que devolver.
                    .build();//Finaliza la construcción de esa respuesta y la devuelve. (siempre que no se use body se usa build)
        }
        return ResponseEntity
                .notFound()// HTTP 404
                .build();//Finaliza la construcción de esa respuesta y la devuelve. (siempre que no se use body se usa build)
    }
}
