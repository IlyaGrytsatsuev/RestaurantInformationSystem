package com.example.demo.utils.db_fill_utils

import com.example.demo.domain.models.MenuItemModel
import com.example.demo.utils.Constants

object Categories {
    const val BREAKFAST_CATEGORY = "Завтраки"
    const val SALADS_CATEGORY = "Салаты"
    const val APPETIZER_CATEGORY = "Закуски"
    const val SOUP_CATEGORY = "Супы"
    const val COMMON_DESCRITION = "Белки: 14,23\n" +
            "Жиры: 24,75\n" +
            "Углеводы: 15,51\n" +
            "Ккал: 341,74"
}



interface MenuItems {
    val namesAndImages: Map<String, String>
}
object BreakfastCategoryItems: MenuItems {

    const val SALMON_TOAST_NAME = "Тост с копченым лососем"
    const val SALMON_TOAST_IMG = "SalmonToast.jpg"

    const val GLAZUNIA_S_SOSISKAMI_NAME = "Яичница глазунья с сосисками"
    const val GLAZUNIA_S_SOSISKAMI_IMG = "Glazunia_s_sosiskami.jpg"

    const val OVSYANKA_NAME = "Каша овсяная с маслом"
    const val OVSYANKA_IMG = "Ovsyanka.jpg"

    const val SYRNYKY_NAME = "Два сырника с сахарной пудрой"
    const val SYRNYKY_IMG = "Syrnyky.jpg"

    override val namesAndImages = mapOf(
            SALMON_TOAST_NAME to SALMON_TOAST_IMG,
            GLAZUNIA_S_SOSISKAMI_NAME to GLAZUNIA_S_SOSISKAMI_IMG,
            OVSYANKA_NAME to OVSYANKA_IMG,
            SYRNYKY_NAME to SYRNYKY_IMG,
    )
}

object SaladsCategoryItems: MenuItems {

    const val SALAD_WITH_SHRIMPS_NAME = "Салат с креветками"
    const val SALAD_WITH_SHRIMPS_IMG = "Salad with shrimps.jpg"

    const val SALAD_WITH_TURKEY_NAME = "Салат с индейкой"
    const val SALAD_WITH_TURKEY_IMG = "Salad with turkey.jpg"

    const val CHUKKA_NAME = "Чукка салат"
    const val CHUKKA_IMG = "Chukka.jpg"

    const val JAPANESE_SALAD_WITH_CHIKEN_NAME = "Японский салат с курицей"
    const val JAPANESE_SALAD_WITH_CHIKEN_IMG = "Japanese salad with chiken.jpg"

    const val CAESAR_SALAD_WITH_CHIKEN_NAME = "Салат цезарь с курицей"
    const val CAESAR_SALAD_WITH_CHIKEN_IMG = "Caesar salad with chiken.jpg"

    override val namesAndImages = mapOf(
            SALAD_WITH_SHRIMPS_NAME to SALAD_WITH_SHRIMPS_IMG,
            SALAD_WITH_TURKEY_NAME to SALAD_WITH_TURKEY_IMG,
            CHUKKA_NAME to CHUKKA_IMG,
            JAPANESE_SALAD_WITH_CHIKEN_NAME to JAPANESE_SALAD_WITH_CHIKEN_IMG,
            CAESAR_SALAD_WITH_CHIKEN_NAME to  CAESAR_SALAD_WITH_CHIKEN_IMG
    )

}

object AppetizerCategoryItems: MenuItems {

    const val KREVETKI_V_TEMPURE_NAME = "Креветки в темпуре"
    const val KREVETKI_V_TEMPURE_IMG = "krevetki v tempure.jpg"

    const val GRENKI_NAME = "Гренки с сыром и чесноком"
    const val GRENKI_IMG = "Grenki.jpg"

    const val KESADILIA_WITH_CHIKEN_NAME = "Кесадилья с курицей"
    const val KESADILIA_WITH_CHIKEN_IMG = "kesadilia with chiken.jpg"

    const val KREVETKI_PO_SYNGAPURSKY_NAME = "Креветки по-сингапурски"
    const val KREVETKI_PO_SYNGAPURSKY_IMG = "krevetki po singapursky.jpg"

    override val namesAndImages = mapOf(
            KREVETKI_V_TEMPURE_NAME to KREVETKI_V_TEMPURE_IMG,
            GRENKI_NAME to GRENKI_IMG,
            KESADILIA_WITH_CHIKEN_NAME to KESADILIA_WITH_CHIKEN_IMG,
            KREVETKI_PO_SYNGAPURSKY_NAME to KREVETKI_PO_SYNGAPURSKY_IMG
    )

}

object SoupCategoryItems: MenuItems {

    const val TOM_YAM_WITH_CHIKEN_NAME = "Том ям с курицей"
    const val TOM_YAM_WITH_CHIKEN_IMG = "tom yam with chiken.jpg"

    const val MUSHROOM_CREAM_SOUP_NAME = "Грибной крем-суп"
    const val MUSHROOM_CREAM_SOUP_IMG = "mushroom cream soup.jpg"

    const val UDON_NAME = "Удон"
    const val UDON_IMG = "udon.jpg"

    const val BORSH_S_GOVYADINOY_NAME = "Борщ с говядиной"
    const val BORSH_S_GOVYADINOY_IMG = "borsh s govyadinoy.jpg"

    override val namesAndImages = mapOf(
        TOM_YAM_WITH_CHIKEN_NAME to TOM_YAM_WITH_CHIKEN_IMG,
        MUSHROOM_CREAM_SOUP_NAME to MUSHROOM_CREAM_SOUP_IMG,
        UDON_NAME to UDON_IMG,
        BORSH_S_GOVYADINOY_NAME to BORSH_S_GOVYADINOY_IMG
    )

}

internal fun MenuItems.toMenuItemModelsList(categoryId: Int, price: ()->Int): List<MenuItemModel>{
    return namesAndImages.map { entry ->
        MenuItemModel(
                id = Constants.UNDEFINED_ID,
                name = entry.key,
                categoryId = categoryId,
                price = price(),
                description = Categories.COMMON_DESCRITION,
                imgPath = entry.value
        )
    }
}
