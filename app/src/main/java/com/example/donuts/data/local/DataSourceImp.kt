package com.example.donuts.data.local

import com.example.donuts.R
import com.example.donuts.domain.entities.DonutEntity
import javax.inject.Inject

class DataSourceImp @Inject constructor() : DataSource {

    override fun getAllDonuts() = donuts

    override fun getAllOffers() = donutsOffer

    private val donuts: List<DonutEntity> = listOf(
        DonutEntity(
            id = 0,
            name = "Chocolate Cherry",
            hasOffer = false,
            oldPrice = null,
            newPrice = 22,
            image = R.drawable.doughnut_chocolate_cherry_drizzle,
            description = "Experience the delightful fusion of chocolate and cherry with our Chocolate Cherry donuts. Enjoy a tender chocolate dough filled with sweet and tangy cherry, topped with a drizzle of smooth chocolate glaze and cherry bits."
        ),
        DonutEntity(
            id = 1,
            name = "Strawberry Rain",
            hasOffer = false,
            oldPrice = null,
            newPrice = 22,
            image = R.drawable.doughnut_strawberry_rain_sprinkles,
            description = "Savor the refreshing sweetness of our Strawberry Rain donuts. These airy yeast donuts are adorned with a delicate glaze made from ripe strawberries, providing a burst of vibrant flavor."
        ),
        DonutEntity(
            id = 2,
            name = "Strawberry",
            hasOffer = false,
            oldPrice = null,
            newPrice = 22,
            image = R.drawable.doughnut_strawberry_chocolate_drizzle,
            description = "Delight in the simple pleasure of our Strawberry donuts. Moist and cake-like, these treats are infused with fresh strawberry flavor and topped with a creamy strawberry glaze. Perfect for a sweet morning or afternoon indulgence."
        ),
        DonutEntity(
            id = 3,
            name = "Strawberry Wheel",
            oldPrice = 20,
            newPrice = 16,
            hasOffer = true,
            image = R.drawable.doughnut_strawberry_wheel_sprinkles,
            description = "These soft, cake-like Strawberry Frosted Donuts feature fresh strawberries and a delicious fresh strawberry glaze frosting. Pretty enough for company and the perfect treat to satisfy your sweet tooth."
        )
    )

    private val donutsOffer: List<DonutEntity> = listOf(
        DonutEntity(
            id = 3,
            name = "Strawberry Wheel",
            oldPrice = 20,
            newPrice = 16,
            hasOffer = true,
            image = R.drawable.doughnut_strawberry_wheel_sprinkles,
            description = "These soft, cake-like Strawberry Frosted Donuts feature fresh strawberries and a delicious fresh strawberry glaze frosting. Pretty enough for company and the perfect treat to satisfy your sweet tooth."
        ),
        DonutEntity(
            id = 4,
            name = "Chocolate Glaze",
            oldPrice = 20,
            newPrice = 16,
            hasOffer = true,
            image = R.drawable.doughnut_chocolate_glaze_sprinkles,
            description = "Indulge in the rich and velvety goodness of our Chocolate Glaze donuts. These fluffy yeast donuts are coated in a glossy layer of luscious chocolate, creating a decadent treat for chocolate lovers."
        ),
    )
}