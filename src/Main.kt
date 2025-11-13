import kotlin.random.Random

fun main(){
    val DAUS: String = "⚀ ⚁ ⚂ ⚃ ⚄ ⚅"
    val CARES_DAU: Array<String> = arrayOf("⚀", "⚁", "⚂", "⚃", "⚄", "⚅")
    val RED: String = "\u001b[0;31m"
    val GREEN: String = "\u001b[0;32m"
    val RESET: String = "\u001b[0m"
    val YELLOW: String = "\u001b[0;33m"
    val PURPLE_BOLD: String = "\u001b[1;35m"
    val GREEN_BOLD: String = "\u001b[1;32m"
    val RED_BOLD: String = "\u001b[1;31m"

    var partides: Int?
    var tiradesPerPartida: Int?

    //VARIABLES CREADES NOVES
    var quantGuanyat = 0
    var quantPerdut = 0
    var quantEmpat = 0
    var guardarTirada = 0



    println(DAUS)
    println("Benvingut/da al joc dels daus.\nPer guanyar cada partida, la suma dels punts de les teves tirades dels teus daus ha de ser superior a la de la CPU")
    println(DAUS)

    // Llegim el número de partides que volem jugar
    do {
        println("Quantes partides vols jugar? (de 1 a 3)")
        partides = readLine()?.toIntOrNull()
        guardarTirada++

        if (partides != null && (partides < 1 || partides > 3)){
            partides = null
            println("ERROR: Valor no acceptat!")
        }
    }while(partides == null)

    // Llegim el número de quantes tirades volem fer per cada partida
    do {
        println("Quantes tirades vols fer per cada partida? (de 1 a 6)")
        tiradesPerPartida = readLine()?.toIntOrNull()

        if (tiradesPerPartida != null && (tiradesPerPartida < 1 || tiradesPerPartida > 6)){
            tiradesPerPartida = null
            println("ERROR: Valor no acceptat!")
        }
    }while(tiradesPerPartida == null)

    // Declarem les matrius
    var tiradesGuardades: Array<IntArray>
    var tiradesGuardadesCPU: Array<IntArray>

    // Inicialitzem les matrius de partides files i (tiradesPerPartida + 1) columnes
    tiradesGuardades = Array(partides){IntArray((tiradesPerPartida + 1)) }
    tiradesGuardadesCPU = Array(partides){IntArray((tiradesPerPartida + 1)) }

    // Repetim tantes vegades com partides
    for(partida in 0 until partides) {
        var acumuladorCPU: Int = 0
        var tiradaActual: Int = 0

        println("${GREEN_BOLD}Partida numero ${partida + 1} del usuari@${RESET}")
        for (tirada in 0 until tiradesGuardades[partida].size - 1) {
            /** Tirades persona **/
            println("Tira el dau! (Intent ${tirada + 1})")
            tiradaActual = Random.nextInt(1, 6 + 1)
            println("Has tret un ${CARES_DAU[tiradaActual-1]} !\n")

            // Guardem la tirada
            tiradesGuardades[partida][tirada] = tiradaActual

            // Acumulem el sumatori a l'última columna de la fila
            tiradesGuardades[partida][tiradesPerPartida] += tiradaActual
        }

        println("${PURPLE_BOLD}Partida numero ${partida + 1} del CPU${RESET}")
        for (tiradaCPU in 0 until tiradesGuardadesCPU[partida].size -1) {
            /** Tirades CPU **/
            println("El CPU tira el dau! (Intent ${tiradaCPU + 1})")
            acumuladorCPU = Random.nextInt(1, 6 + 1)
            println("El CPU ha tret un ${CARES_DAU[acumuladorCPU-1]}\n")

            // Guardem la tirada
            tiradesGuardadesCPU[partida][tiradaCPU] = acumuladorCPU

            // Acumulem el sumatori a l'última columna de la fila
            tiradesGuardadesCPU[partida][tiradesPerPartida] += acumuladorCPU
        }

        println("${RED_BOLD}PARTIDA ACABADA!${RESET}")
        println("Tu has aconseguit ${tiradesGuardades[partida][tiradesPerPartida]} punts")
        println("La CPU ha aconseguit ${tiradesGuardadesCPU[partida][tiradesPerPartida]} punts")

        if (tiradesGuardades[partida][tiradesPerPartida] > acumuladorCPU){
            println("${GREEN}HAS GUANYAT!${RESET} \uD83C\uDFC6")
            quantGuanyat++
        }else if (tiradesGuardades[partida][tiradesPerPartida] < acumuladorCPU){
            println("${RED}Has perdut per nuv${RESET} \uD83D\uDC7B!" )
            quantPerdut++
        }else{
            println("${YELLOW}Heu empatat!${RESET}")
            quantEmpat++
        }
        println("\n-------------------------------- \n")
    }

    val percentGuanyades = quantGuanyat / guardarTirada * 100
    val percentPedut = quantPerdut / guardarTirada * 100
    val percentEmpat = quantEmpat / guardarTirada * 100

    println("> PARTIDES GUANYADES: $quantGuanyat VEGADES --> (${percentGuanyades.toInt()}%)")
    println("> PARTIDES PERDUDES: $quantPerdut VEGADES --> (${percentPedut.toInt()}%)")
    println("> PARTIDES EMPATADES: $quantEmpat VEGADES --> (${percentEmpat.toInt()}%)")
}