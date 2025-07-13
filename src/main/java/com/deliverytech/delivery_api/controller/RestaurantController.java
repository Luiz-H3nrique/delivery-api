package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.entity.Restaurante;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RestaurantController {

    @GetMapping("/restaurantes")
    public String listar(Model model){
        List<Restaurante> restaurantes = restauranteService.listar;
        Ativos();
        model.addAtribute("restaurantes",restaurantes);
        return "ListaRestaurantes"
    };



}
