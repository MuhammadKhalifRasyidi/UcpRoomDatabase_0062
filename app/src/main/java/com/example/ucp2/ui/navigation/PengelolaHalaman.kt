package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.view.MainScreen
import com.example.ucp2.ui.view.barang.DetailBrgView
import com.example.ucp2.ui.view.barang.HomeBrgView
import com.example.ucp2.ui.view.barang.InsertBrgView
import com.example.ucp2.ui.view.barang.UpdateBrgView
import com.example.ucp2.ui.view.suplier.HomeSprView
import com.example.ucp2.ui.view.suplier.InsertSprView


@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController, startDestination = DestinasiHome.route
    ) {
        composable(
            route = DestinasiHome.route
        ) {
            MainScreen(
               onAddBarangClick = {navController.navigate(DestinasiInsertBrg.route)},
               onListBarangClick = {navController.navigate(DestinasiHomeBrg.route)},
               onAddSuplierClick = {navController.navigate(DestinasiInsertSpr.route)},
               onListSuplierClick = {navController.navigate(DestinasiHomeSpr.route)},
            )
        }

        composable(
            route =  DestinasiInsertBrg.route
        ) {
            InsertBrgView(onBack = {
                navController.popBackStack()
            }, onNavigate = {
                navController.popBackStack()
            }, modifier = modifier
            )
        }

        composable(
            DestinasiDetail.routeWithArg, arguments = listOf(navArgument(DestinasiDetail.ID) {
                type = NavType.StringType
            })
        ) {
            val id = it.arguments?.getString(DestinasiDetail.ID)
            id?.let { id ->
                DetailBrgView(onBack = {
                    navController.popBackStack()
                }, onEditClick = {
                    navController.navigate("${DestinasiUpdate.route}/$it")
                }, modifier = modifier, onDeleteClick = {
                    navController.popBackStack()
                })
            }
        }

        composable(
            DestinasiUpdate.routesWithArg, arguments = listOf(navArgument(DestinasiUpdate.ID) {
                type = NavType.StringType
            })
        ) {
            UpdateBrgView(onBack = {
                navController.popBackStack()
            }, onNavigate = {
            navController.popBackStack()
            }, modifier = modifier
            )
        }

        composable(
            route = DestinasiInsertSpr.route
        ) {
            InsertSprView(onBack = {
                navController.popBackStack()
            }, onNavigate = {
                navController.popBackStack()
            }, modifier = modifier
            )
        }

        composable(
            route = DestinasiHomeBrg.route
        ) {
            HomeBrgView(onDetailClick = { id ->
                navController.navigate("${DestinasiDetail.route}/$id")
                println("PengelolaHalaman: id = $id")
            }, onBack = {
                navController.popBackStack()
            }, onAddBrg = {
                navController.navigate(DestinasiInsertBrg.route)
            }, Modifier = Modifier
            )
        }

        composable(
            route = DestinasiHomeSpr.route
        ) {
            HomeSprView( onBack = {
                navController.popBackStack()
            }, onNavigate = {
                navController.popBackStack()
            }, onAddSpr = {
                navController.navigate(DestinasiInsertSpr.route)
            }, Modifier = Modifier
            )
        }
    }
}