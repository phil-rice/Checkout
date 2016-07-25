package unit.org.validoc.shopping

import org.validoc.shopping.{ItemFinder, Money, Offer, SaleableItem}

trait ShoppingCartsFixture {
  val apple = SaleableItem("Apple", Money(50))
  val banana = SaleableItem("Banana", Money(30))
  val crisps = SaleableItem("Crisps", Money(20))
  val donuts = SaleableItem("Donuts", Money(15))

  val nameToSaleableItem = Map("A" -> apple, "B" -> banana, "C" -> crisps, "D" -> donuts)


}
