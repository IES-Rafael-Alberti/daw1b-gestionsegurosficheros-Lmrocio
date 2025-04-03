package model

class SeguroAuto : Seguro {


    val descripcion: String
    val combustible: String
    val tipoAuto: Auto
    val tipoCobertura: Cobertura
    val asistenciaCarretera: Boolean
    val numPartes: Int


    companion object {
        private var numPolizasAuto = 400000
        private fun generateId() = numPolizasAuto++

        fun crearSeguro(datos: List<String>): SeguroAuto {
            val numPoliza = datos[0].toInt()
            val dniTitular = datos[1]
            val importe = datos[2].toDouble()
            val descripcion = datos[3]
            val combustible = datos[4]
            val tipoAuto = Auto.getAuto(datos[5])
            val tipoCobertura = Cobertura.getCobertura(datos[6])
            val asistenciaCarretera = datos[7].toBoolean()
            val numPartes = datos[8].toInt()

            return SeguroAuto(numPoliza, dniTitular, importe, descripcion, combustible, tipoAuto, tipoCobertura, asistenciaCarretera, numPartes)
        }

        val PORCENTAJE_INCREMENTO_PARTES = 2
    }

    constructor(dniTitular: String, importe: Double, descripcion: String, combustible: String, tipoAuto: Auto, tipoCobertura: Cobertura, asistenciaCarretera: Boolean, numPartes: Int) :
            super(numPoliza = SeguroAuto.generateId(), dniTitular, importe) {
        this.descripcion = descripcion
        this.combustible = combustible
        this.tipoAuto = tipoAuto
        this.tipoCobertura = tipoCobertura
        this.asistenciaCarretera = asistenciaCarretera
        this.numPartes = numPartes
    }

    private constructor(numPoliza: Int, dniTitular: String, importe: Double, descripcion: String, combustible: String, tipoAuto: Auto, tipoCobertura: Cobertura, asistenciaCarretera: Boolean, numPartes: Int) :
            super(numPoliza, dniTitular, importe) {
        this.descripcion = descripcion
        this.combustible = combustible
        this.tipoAuto = tipoAuto
        this.tipoCobertura = tipoCobertura
        this.asistenciaCarretera = asistenciaCarretera
        this.numPartes = numPartes
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        val interesResidual = numPartes * 0.02
        return importe * (1 + interes) * (1 + interesResidual)
    }

    override fun serializar(separador: String): String {
        return "${super.serializar(separador)}$separador\"$descripcion\"$separador$combustible;$tipoAuto;${tipoCobertura.desc}$separador$asistenciaCarretera$separador$numPartes$separador${tipoSeguro()}"
    }

    override fun toString(): String {
        return "Seguro Auto(numPoliza=$numPolizasAuto, dniTitular=${getDniTitular()}, importe=$importe," +
                "descripción=$descripcion, combustible=$combustible, tipo de auto=$tipoAuto, tipo de cobertura=${tipoCobertura.desc}, " +
                "asistencia en carretera=${if (asistenciaCarretera) "Sí" else "No"}, número de partes=$numPartes"
    }

}
