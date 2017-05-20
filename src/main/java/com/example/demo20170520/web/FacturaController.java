package com.example.demo20170520.web;

import com.example.demo20170520.business.FacturaService;
import com.example.demo20170520.model.FacturaCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by domix on 5/20/17.
 */
@RestController
@RequestMapping("/v1/facturas")
public class FacturaController {
  private final FacturaService facturaService;

  @Autowired
  public FacturaController(FacturaService facturaService) {
    this.facturaService = facturaService;
  }

  @PostMapping
  public FacturaCommand crearFactura(@Valid FacturaCommand factura, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new IllegalArgumentException("Errores de validacion");
    }
    return facturaService.guardarFactura(factura);
  }
}
