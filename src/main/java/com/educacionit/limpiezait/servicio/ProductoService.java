package com.educacionit.limpiezait.servicio;

import com.educacionit.limpiezait.dominio.Producto;
import com.educacionit.limpiezait.repositorio.ProductoRepository;

import java.util.List;
import java.util.Optional;

public class ProductoService {

//el servicio llama al repositorio para funcionar

    private ProductoRepository productoRepository;

//Instanciar :

    public ProductoService(){
        this.productoRepository = new ProductoRepository();
    }

    public List<Producto> getAll(){
        return this.productoRepository.obtenerTodos();
    }

    public Producto getById(Long id){

        Optional<Producto> oProducto = this.productoRepository.buscarPorId(id);
        //si hay presencia de datos retorno el objeto
        if(oProducto.isPresent()){
            return oProducto.get();
        }
        return null;
    }

    public Producto create(Producto producto){
        Optional<Producto> oProducto = this.productoRepository.buscarPorId(producto.getId());
        if(oProducto.isPresent()) {
            return null;
        }
        return this.productoRepository.guardar(producto);
    }

    public Producto update(Long id,
                           Producto producto){
        Optional<Producto> oProducto = this.productoRepository.buscarPorId(id);
        //si hay presencia de datos retorno el objeto
        if(oProducto.isPresent()){
            producto.setId(id);
            this.productoRepository.actualizar(producto);
            return producto;
        }
        return null;
    }

    public boolean delete(Long id){
        return this.productoRepository.eliminar(id);
    }


}
