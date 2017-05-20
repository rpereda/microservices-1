package com.example.demo20170520.business.impl

import com.example.demo20170520.model.FacturaCommand
import spock.lang.Ignore
import spock.lang.Specification

/**
 * Created by domix on 5/20/17.
 */
class FacturaServiceImplSpec extends Specification {

  def 'deberia guardar una factura con el camino feliz'() {
    given:
      FacturaServiceImpl service = new FacturaServiceImpl()
      FacturaCommand command = new FacturaCommand(
        fecha: new Date(),
        ruc: '12345678901',
        razonSocial: 'Algo',
        montoTotal: 30,
        subTotal: 20,
        igv: 10,
        concepto: 'Un producto'
      )

      def factura = service.guardarFactura(command)
    expect:
      factura
  }

  def 'deberia fallar al enviar una factura en NULL'() {
    when:
      FacturaServiceImpl service = new FacturaServiceImpl()
      service.guardarFactura(null)
    then:
      IllegalArgumentException error = thrown()
      error
      error.message == 'La factura es requerida'
  }

  def 'deberia fallar al intentar guardar una factura con los montos incorrectos'() {
    when:
      FacturaServiceImpl service = new FacturaServiceImpl()
      FacturaCommand command = new FacturaCommand(
        fecha: new Date(),
        ruc: '12345678901',
        razonSocial: 'Algo',
        montoTotal: 30,
        subTotal: 20,
        igv: 0,
        concepto: 'Un producto'
      )

      service.guardarFactura(command)
    then:
      RuntimeException error = thrown()
      error.message == FacturaServiceImpl.ERROR_MONTO
  }
}
