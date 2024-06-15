package uv.mx.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import uv.mx.inventario.gen.*;


@Endpoint
public class ProductoEndPoint {

    @Autowired
    private IProducto newProducto;

    @PayloadRoot(localPart = "AgregarProductoRequest", namespace = "https://inventario.mx.uv/inventario")
    @ResponsePayload
    public AgregarProductoResponse agregarProducto(@RequestPayload AgregarProductoRequest peticion) {
        
        Producto producto = new Producto();
        AgregarProductoResponse respuesta = new AgregarProductoResponse();
            producto.setNombre(peticion.getNombre());
            producto.setDescripcion(peticion.getDescripcion());
            producto.setPrecio(peticion.getPrecio());
            producto.setCantidad(peticion.getStock());
            newProducto.save(producto);
            respuesta.setMensaje("Producto agregado.");

            return respuesta;

    }

    @PayloadRoot(localPart = "VerificarProductoRequest", namespace = "https://inventario.mx.uv/inventario")
    @ResponsePayload
    public VerificarProductoResponse agregarProducto(@RequestPayload VerificarProductoRequest peticion) {
       
        VerificarProductoResponse respuesta = new VerificarProductoResponse();
        try {
            Producto producto = newProducto.findById(peticion.getId()).get();
            respuesta.setId(producto.getIdProducto());
            respuesta.setNombre(producto.getNombre());
            respuesta.setDescripcion(producto.getDescripcion());
            respuesta.setPrecio(producto.getPrecio());
            respuesta.setStock(producto.getCantidad());
            return respuesta;
           
        } catch (Exception e) {
            return null;
        }
    }

}
