package com.educacionit.limpiezait.repositorio;

import com.educacionit.limpiezait.dominio.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoRepository {

    private List<Producto> productos;

    public ProductoRepository() {
        productos = new ArrayList<>();

        productos.add(new Producto(1L, "Jabón líquido 250 ml", "Jabon liquido marca zzz de 250 ml", 250.00, ""));
        productos.add(new Producto(2L, "Jabón líquido 500 ml", "Jabon liquido marca zzz de 500 ml", 500.00, ""));
        productos.add(new Producto(3L, "Jabon liquido 1000 ml", "Jabon liquido marca zzz de 1000 ml", 850.00, ""));
    }

    public List<Producto> obtenerTodos() {
        return this.productos;
    }

    public Optional<Producto> buscarPorId(Long id) {
        return this.productos
                .stream()
                .filter(prod -> prod.getId().equals(id))
                .findFirst();
    }

    public Producto guardar (Producto producto){
        producto.setId((long) (this.productos.size() + 1 ));
        this.productos.add(producto);
        return producto;
    }

    public Producto actualizar(Producto producto){
        this.productos
                .stream() //me ahorro de hacer un for recorre la lista de productos
                .filter(prod -> prod.getId().equals(producto.getId())) //prod.getId() obtiene el id de cada producto y lo compara con el id recibido.
                .findFirst()
                .ifPresent(prod -> {
                    prod.setNombre(producto.getNombre());
                    prod.setDescripcion(producto.getDescripcion());
                    prod.setPrecio(producto.getPrecio());
                    prod.setUrlFoto(producto.getUrlFoto());
                });

        return producto;

    }

    public boolean eliminar(Long id){
        return this.productos.removeIf(prod -> prod.getId().equals(id));
    }

}
