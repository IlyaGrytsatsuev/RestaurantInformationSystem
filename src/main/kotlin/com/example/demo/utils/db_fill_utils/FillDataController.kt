package com.example.demo.utils.db_fill_utils

import com.example.demo.data.services.ResetPrimaryKeysSequencesService
import com.example.demo.domain.models.*
import com.example.demo.domain.services.*
import com.example.demo.utils.Constants
import com.example.demo.utils.OrderItemStatus
import com.example.demo.utils.OrderStatus
import com.example.demo.utils.UserRole
import com.example.demo.utils.db_fill_utils.Categories.APPETIZER_CATEGORY
import com.example.demo.utils.db_fill_utils.Categories.BREAKFAST_CATEGORY
import com.example.demo.utils.db_fill_utils.Categories.SALADS_CATEGORY
import com.example.demo.utils.db_fill_utils.Categories.SOUP_CATEGORY
import com.example.demo.utils.exceptions.NullReceivedException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Controller
import java.time.LocalDateTime

@Controller
internal class FillDataController @Autowired constructor(
    private val menuItemsCategoriesService: MenuItemsCategoriesService,
    private val menuItemsService: MenuItemsService,
    private val orderItemsService: OrderItemsService,
    private val orderItemsStatusesService: OrderItemsStatusesService,
    private val ordersService: OrdersService,
    private val orderStatusesService: OrderStatusesService,
    private val tablesService: TablesService,
    private val usersRolesService: UsersRolesService,
    private val usersService: UsersService,
    private val primaryKeysSequencesService: ResetPrimaryKeysSequencesService
) {

    private fun randomPrice(): Int {
        return (100..3000).random()
    }

    init {
//        clearData()
//        primaryKeysSequencesService.resetSequences()
//        fillMenuItemsCategories()
//        fillMenuItems()
//        fillOrderStatuses()
//        fillOderItemsStatuses()
//        fillTables()
//        fillUsersRoles()
//        fillUsers()
//        fillOrders()
    }

    fun fillMenuItemsCategories() {
        val categoriesList = listOf(
            MenuItemCategoryModel(
                id = Constants.UNDEFINED_ID,
                name = BREAKFAST_CATEGORY,
            ),
            MenuItemCategoryModel(
                id = Constants.UNDEFINED_ID,
                name = SALADS_CATEGORY,
            ),
            MenuItemCategoryModel(
                id = Constants.UNDEFINED_ID,
                name = APPETIZER_CATEGORY,
            ),
            MenuItemCategoryModel(
                id = Constants.UNDEFINED_ID,
                name = SOUP_CATEGORY,
            ),
        )
        menuItemsCategoriesService
            .createOrUpdateMenuItemsCategories(categoriesList)
    }

    fun fillMenuItems() {

        val categoriesList = menuItemsCategoriesService.getMenuItemsCategoriesList()
        val breakfastId = categoriesList
            .find { it.name == BREAKFAST_CATEGORY }?.id ?: -1
        val saladsId = categoriesList
            .find { it.name == SALADS_CATEGORY }?.id ?: -1
        val appetizerId = categoriesList
            .find { it.name == APPETIZER_CATEGORY }?.id ?: -1
        val soupId = categoriesList
            .find { it.name == SOUP_CATEGORY }?.id ?: -1


        val breakfastItemsList =
            BreakfastCategoryItems.toMenuItemModelsList(
                categoryId = breakfastId,
                price = ::randomPrice
            )
        val saladItemsList =
            SaladsCategoryItems.toMenuItemModelsList(
                categoryId = saladsId,
                price = ::randomPrice
            )

        val appetizerItemsList = AppetizerCategoryItems.toMenuItemModelsList(
            categoryId = appetizerId,
            price = ::randomPrice
        )

        val soupItemsList = SoupCategoryItems.toMenuItemModelsList(
            categoryId = soupId,
            price = ::randomPrice
        )

        val listForSaving =
            breakfastItemsList + saladItemsList +
                    appetizerItemsList + soupItemsList

        menuItemsService.createOrUpdateMenuItems(listForSaving)
    }

    fun fillOrderItems() {

    }

    fun fillOderItemsStatuses() {
        val statusesList = listOf(
            OrderItemStatusModel(
                id = Constants.UNDEFINED_ID,
                status = OrderItemStatus.RECEIVED.status,
            ),
            OrderItemStatusModel(
                id = Constants.UNDEFINED_ID,
                status = OrderItemStatus.IS_BEING_COOKED.status,
            ),
            OrderItemStatusModel(
                id = Constants.UNDEFINED_ID,
                status = OrderItemStatus.READY.status,
            )
        )
        orderItemsStatusesService.createOrUpdateOrderItemStatus(statusesList)
    }

    fun fillOrders() {
        val ordersList = listOf(
            OrderModel(
                id = -1,
                waiterId = 1,
                dateTime = LocalDateTime.now(),
                tableId = 1,
                statusId = 1
            )
        )
        ordersService.createOrUpdateOrder(ordersList)
    }

    fun fillOrderStatuses() {
        val statusesList = listOf(
            OrderStatusModel(
                id = Constants.UNDEFINED_ID,
                status = OrderStatus.OPENED.status,
            ),
            OrderStatusModel(
                id = Constants.UNDEFINED_ID,
                status = OrderStatus.CLOSED.status,
            )
        )
        orderStatusesService.createOrUpdateOrderStatuses(statusesList)
    }

    fun fillTables() {
        repeat(5) {
            tablesService.createTable()
        }
    }

    fun fillUsersRoles() {
        val rolesList = listOf(
            UserRoleModel(
                id = Constants.UNDEFINED_ID,
                roleName = UserRole.ADMIN.name,
            ),
            UserRoleModel(
                id = Constants.UNDEFINED_ID,
                roleName = UserRole.WAITER.name,
            ),
            UserRoleModel(
                id = Constants.UNDEFINED_ID,
                roleName = UserRole.CLIENT.name,
            )
        )

        usersRolesService.createOrUpdateUserRole(rolesList)
    }

    fun fillUsers() {
        val adminRoleId = usersRolesService.getUsersRolesList()
            .find { role ->
                role.roleName == UserRole.ADMIN.name
            }?.id ?: throw NullReceivedException()
        val waiterRoleId = usersRolesService.getUsersRolesList()
            .find { role ->
                role.roleName == UserRole.WAITER.name
            }?.id ?: throw NullReceivedException()
        val clientRoleId = usersRolesService.getUsersRolesList()
            .find { role ->
                role.roleName == UserRole.CLIENT.name
            }?.id ?: throw NullReceivedException()

        val usersList = listOf(
            UserAuthorizationModel(
                id = Constants.UNDEFINED_ID,
                username = UserRole.ADMIN.name,
                password = UserRole.ADMIN.name,
                name = "Админ",
                surname = "Админов",
                roleId = adminRoleId
            ),
            UserAuthorizationModel(
                id = Constants.UNDEFINED_ID,
                username = UserRole.WAITER.name ,
                password = UserRole.WAITER.name,
                name = "Иван",
                surname = "Соломин",
                roleId = waiterRoleId
            ),
            UserAuthorizationModel(
                id = Constants.UNDEFINED_ID,
                username = UserRole.CLIENT.name,
                password = UserRole.CLIENT.name,
                name = "Антон",
                surname = "Голубков",
                roleId = clientRoleId
            )
        )
        usersService.createOrUpdateUser(usersList)
    }

    private fun clearData() {
        menuItemsCategoriesService.deleteMenuItemCategories(emptyList())
        menuItemsService.deleteMenuItems(emptyList())
        orderItemsStatusesService.deleteOrderItemStatuses(emptyList())
        ordersService.deleteOrders(emptyList())
        orderStatusesService.deleteOrderStatuses(emptyList())
        tablesService.deleteTables(emptyList())
        usersService.deleteUsers(emptyList())
        usersRolesService.deleteUserRoles(emptyList())
    }

}