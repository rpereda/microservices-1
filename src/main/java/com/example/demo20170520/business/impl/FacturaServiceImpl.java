package com.example.demo20170520.business.impl;

import com.example.demo20170520.business.FacturaService;
import com.example.demo20170520.model.FacturaCommand;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by domix on 5/20/17.
 */
@Service
public class FacturaServiceImpl implements FacturaService {

  public static final String ERROR_MONTO = "El monto total difiere del subtotal + IGV.";

  @Override
  public FacturaCommand guardarFactura(FacturaCommand factura) {
    String message = "La factura es requerida";

    Assert.notNull(factura, message);

    if (!factura.getMontoTotal()
      .equals(factura.getIgv()
        .add(factura.getSubTotal()))) {
      throw new RuntimeException(ERROR_MONTO);
    }
    factura.setId(1L);
    return factura;
  }
}
