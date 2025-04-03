package model

import java.time.LocalDate

class SeguroVida : Seguro {

    private val fechaNac: LocalDate
    private val nivelRiesgo: Riesgo
    private val indemnizacion: Double



    companion object {
        private var numPolizasVida = 800000
        private fun generateId() = numPolizasVida++

        fun crearSeguro(datos: List<String>): SeguroVida {
            val numPoliza = datos[0].toInt()
            val dniTitular = datos[1]
            val importe = datos[2].toDouble()
            val fechaNac = LocalDate.parse(datos[3])
            val nivelRiesgo = Riesgo.getRiesgo(datos[4])
            val indemnizacion = datos[5].toDouble()

            return SeguroVida(numPoliza, dniTitular, importe, fechaNac, nivelRiesgo, indemnizacion)
        }
    }

    constructor(dniTitular: String, importe: Double, fechaNac: LocalDate, nivelRiesgo: Riesgo, indemnizacion: Double) :
            super(numPoliza = SeguroVida.generateId(), dniTitular, importe) {
        this.fechaNac = fechaNac
        this.nivelRiesgo = nivelRiesgo
        this.indemnizacion = indemnizacion
    }

    private constructor(numPoliza: Int, dniTitular: String, importe: Double, fechaNac: LocalDate, nivelRiesgo: Riesgo, indemnizacion: Double) :
            super(numPoliza, dniTitular, importe) {
        this.fechaNac = fechaNac
        this.nivelRiesgo = nivelRiesgo
        this.indemnizacion = indemnizacion
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        val anios = LocalDate.now().year - fechaNac.year
        val interesResidual = anios * 0.05

        return importe * (1 + interes / 100 + interesResidual / 100 + nivelRiesgo.interesAplicado / 100)
    }

    override fun serializar(separador: String): String {
        return "${super.serializar(separador)}$separador$importe$separador$fechaNac$separador$nivelRiesgo$separador$indemnizacion$separador${tipoSeguro()}"
    }

    override fun toString(): String {
        return "Seguro Vida(numPoliza=$numPolizasVida, dniTitular=${getDniTitular()}, importe=$importe," +
                "fecha de nacimiento=$fechaNac, nivel de riesgo=$nivelRiesgo e indenminación=$indemnizacion"
    }
}
