package org.validoc.shopping


trait Offer {
  def find(items: Seq[SaleableItem]): Seq[SaleableItem]
}


case class BuyNCheaper(item: SaleableItem, n: Int, priceForN: Money) extends Offer {
  val discount = item.cost * n - priceForN
  val discountItem: SaleableItem = SaleableItem(s"Buy $n ${item.item}s for ${priceForN}, means a discount of $discount" , discount.negated)

  override def find(items: Seq[SaleableItem]): List[SaleableItem] = {
    val theseItems = items.filter(_ == item)
    val numberOfOffers = theseItems.size / n
    List.fill(numberOfOffers)(discountItem)
  }

}

