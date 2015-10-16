package br.com.bplm.yesbrasil.rest.api;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by lucas on 16/10/15.
 * Methods of the API
 */
public interface HttpApi {

    @GET("/classificacao/consultarTodos")
    List<Object> getClassifications();

    @GET("/familia/consultarTodos/{login}")
    List<Object> getFamilies(@Path("login") String login);

    @GET("/grupoProduto/consultarTodos")
    List<Object> getProducts();

    @GET("/preco/consultarTodos/{login}")
    List<Object> getAllPrices(@Path("login") String login);

    @GET("/preco/{login}")
    List<Object> getPrices(@Path("login") String login);

    @GET("/produto/consultarTodos/{login}")
    List<Object> getAllProducts(@Path("login") String login);

    @GET("/produto/{id_product}/{login}")
    List<Object> getProductsById(@Path("id_product") String id, @Path("login") String login);

    @GET("/usuarioPreco/consultarTodos/{login}")
    List<Object> getAllPricesByUser(@Path("login") String login);

    @GET("/usuarioPreco/{login}")
    List<Object> getPricesByUser(@Path("login") String login);

    @GET("/usuario/consultarTodos/{login}")
    List<Object> getUsers(@Path("login") String login);

    @GET("/usuario/login/{info_login}")
    Object authenticate(@Path("info_login") String login);

    //Verificar se será assim
    @GET("/imagens/{image_url}")
    Object getProductImage(@Path("image_url") String image_url);

    @GET("/imagens/folder/{image_url}")
    Object getFolderImage(@Path("image_url") String image_url);

    @GET("/imagens/familia/{image_url}")
    Object getFamilyImage(@Path("image_url") String image_url);
}
