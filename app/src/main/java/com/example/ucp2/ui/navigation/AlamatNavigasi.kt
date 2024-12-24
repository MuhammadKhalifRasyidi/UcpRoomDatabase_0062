package com.example.ucp2.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}
object DestinasiHomeBrg : AlamatNavigasi {
    override val route = "brghome"
}
object DestinasiHomeSpr : AlamatNavigasi {
    override val route = "sprhome"
}

object DestinasiInsertBrg : AlamatNavigasi {
    override val route = "insert_brg"
}

object DestinasiInsertSpr : AlamatNavigasi {
    override val route = "insert_spr"
}

object DestinasiDetail : AlamatNavigasi {
    override val route = "detail"
    const val ID = "id"
    val routeWithArg = "$route/{$ID}"
}

object DestinasiUpdate : AlamatNavigasi {
    override val route = "update"
    const val ID = "id"
    val routesWithArg = "${route}/{${ID}}"
}