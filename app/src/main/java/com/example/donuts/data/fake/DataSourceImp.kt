package com.example.donuts.data.fake

import com.example.donuts.domain.entities.Donut
import javax.inject.Inject

class DataSourceImp @Inject constructor() : IDataSource {

    override suspend fun getAllDonuts() = donuts

    override suspend fun getAllOffers() = donutsOffer

    private val donuts: List<Donut> = listOf(
        Donut(
            id = "0",
            name = "Chocolate Cherry",
            price = 22.0,
            discountPercentage = 0.0,
            hasOffer = false,
            imageUrl = "https://i.imgur.com/9eSzuQX.png",
            description = "Experience the delightful fusion of chocolate and cherry with our Chocolate Cherry donuts. Enjoy a tender chocolate dough filled with sweet and tangy cherry, topped with a drizzle of smooth chocolate glaze and cherry bits."
        ),
        Donut(
            id = "1",
            name = "Strawberry Rain",
            price = 22.0,
            discountPercentage = 0.0,
            hasOffer = false,
            imageUrl = "https://i.imgur.com/7aDgI8C.png",
            description = "Savor the refreshing sweetness of our Strawberry Rain donuts. These airy yeast donuts are adorned with a delicate glaze made from ripe strawberries, providing a burst of vibrant flavor."
        ),
        Donut(
            id = "2",
            name = "Strawberry",
            price = 22.0,
            discountPercentage = 0.0,
            hasOffer = false,
            imageUrl = "https://i.imgur.com/KklGjje.png",
            description = "Delight in the simple pleasure of our Strawberry donuts. Moist and cake-like, these treats are infused with fresh strawberry flavor and topped with a creamy strawberry glaze. Perfect for a sweet morning or afternoon indulgence."
        ),
        Donut(
            id = "3",
            name = "Strawberry Wheel",
            price = 22.0,
            discountPercentage = 0.27,
            hasOffer = true,
            imageUrl = "https://i.imgur.com/TO43FGz.png",
            description = "These soft, cake-like Strawberry Frosted Donuts feature fresh strawberries and a delicious fresh strawberry glaze frosting. Pretty enough for company and the perfect treat to satisfy your sweet tooth."
        )
    )

    private val donutsOffer: List<Donut> = listOf(
        Donut(
            id = "3",
            name = "Strawberry Wheel",
            price = 22.0,
            discountPercentage = 0.27,
            hasOffer = true,
            imageUrl = "https://i.imgur.com/TO43FGz.png",
            description = "These soft, cake-like Strawberry Frosted Donuts feature fresh strawberries and a delicious fresh strawberry glaze frosting. Pretty enough for company and the perfect treat to satisfy your sweet tooth."
        ),
        Donut(
            id = "4",
            name = "Chocolate Glaze",
            price = 22.0,
            discountPercentage = 0.27,
            hasOffer = true,
            imageUrl = "https://i.imgur.com/foQWeHB.png",
            description = "Indulge in the rich and velvety goodness of our Chocolate Glaze donuts. These fluffy yeast donuts are coated in a glossy layer of luscious chocolate, creating a decadent treat for chocolate lovers."
        ),
    )
}