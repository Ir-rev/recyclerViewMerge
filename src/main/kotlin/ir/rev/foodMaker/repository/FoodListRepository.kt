package ir.rev.foodMaker.repository

import ir.rev.foodMaker.Food
import ir.rev.foodMaker.FoodDetails
import java.util.UUID

/**
 * Репозиторий для получение данные об еде
 */
interface FoodListRepository {

    /**
     * Возвращает список еды для главного экрана
     */
    fun getFoodList(position: Int): List<Food>

    /**
     * Возвращает детальную информацию об еде
     */
    fun getFoodDetails(foodId: UUID): FoodDetails

    /**
     * Возвращает список дополнительной еды (к примеру для "суши" предлагаем "соевый соус")
     */
    fun getAdditionalFood(group: String): List<Food>
}