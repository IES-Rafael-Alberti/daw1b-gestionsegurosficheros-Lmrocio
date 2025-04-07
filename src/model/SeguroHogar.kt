package model


class SeguroHogar : Seguro {

    private val metrosCuadrados: Int
    private val valorContenido: Double
    private val direccion: String
    private val aniosConstruccion: Int

    companion object {
        var numPolizasHogar = 100000
        private fun generateId() = numPolizasHogar++

        fun crearSeguro(datos: List<String>): SeguroHogar {
            val numPoliza = datos[0].toInt()
            val dniTitular = datos[1]
            val importe = datos[2].toDouble()
            val metrosCuadrados = datos[3].toInt()
            val valorContenido = datos[4].toDouble()
            val direccion = datos[5]
            val aniosConstruccion = datos[6].toInt()

            return SeguroHogar(numPoliza, dniTitular, importe, metrosCuadrados, valorContenido, direccion, aniosConstruccion)
        }

        val PORCENTAJE_INCREMENTO_ANIOS = 0.02
        val CICLO_ANIOS_INCREMENTO = 5



    }

    constructor(dniTitular: String, importe: Double, metrosCuadrados: Int, valorContenido: Double, direccion: String, aniosConstruccion: Int) :
            super(numPoliza = generateId(), dniTitular, importe) {
        this.metrosCuadrados = metrosCuadrados
        this.valorContenido = valorContenido
        this.direccion = direccion
        this.aniosConstruccion = aniosConstruccion
    }

    private constructor(numPoliza: Int, dniTitular: String, importe: Double, metrosCuadrados: Int, valorContenido: Double, direccion: String, aniosConstruccion: Int) :
            super(numPoliza, dniTitular, importe) {
        this.metrosCuadrados = metrosCuadrados
        this.valorContenido = valorContenido
        this.direccion = direccion
        this.aniosConstruccion = aniosConstruccion
    }

    fun getPoliza () : Int {
        return numPolizasHogar
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        val interesResidual = (aniosConstruccion / 5) * 0.02

        return importe * (1 + interes + interesResidual)
    }

    override fun serializar(separador: String): String {
        return "${super.serializar(separador)}$separador$importe$separador$metrosCuadrados$separador$valorContenido$separador$direccion$separador${tipoSeguro()}"
    }

    override fun toString() : String {
        return "Seguro Hogar(numPoliza=$numPolizasHogar, dniTitular=${getDniTitular()}, importe=$importe," +
                "metros cuadrado=$metrosCuadrados, valor contenido=$valorContenido, dirección=$direccion y años de" +
                " construcción=$aniosConstruccion)"
    }

}