package ir.rev.foodMaker.repository

import ir.rev.foodMaker.Food
import ir.rev.foodMaker.FoodDetails
import ir.rev.foodMaker.utils.isInternetAvailable
import java.net.ConnectException
import java.util.UUID

/**
 * Реализация интерфейса [FoodListRepository]
 */
class FoodListRepositoryImpl : FoodListRepository {

    override fun getFoodList(position: Int): List<Food> {
        checkInternet()
    }

    override fun getFoodDetails(foodId: UUID): FoodDetails {
        checkInternet()
    }

    override fun getAdditionalFood(group: String): List<Food> {
        checkInternet()
    }

    private fun checkInternet() {
        if (isInternetAvailable().not()) throw ConnectException("нет интернета")
    }
}