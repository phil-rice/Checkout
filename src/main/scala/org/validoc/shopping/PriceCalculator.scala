package org.validoc.shopping

/** The class that calculates the price in a shopping basket */
class PriceCalculator(itemFinder: ItemFinder, offers: List[Offer] = List()) {

  def calculatePayment(items: Item*): Money = {
    val saleableItems: Seq[SaleableItem] = items.map(itemFinder)
    val discountedItems = offers.flatMap(offer => offer.find(saleableItems))
    (saleableItems ++ discountedItems).map(_.cost).foldLeft(Money(0))((acc, value) => acc + value)
  }

}


object PriceCalculator {

  val apple = SaleableItem("Apple", Money(50))
  val banana = SaleableItem("Banana", Money(30))
  val crisps = SaleableItem("Crisps", Money(20))
  val donuts = SaleableItem("Donuts", Money(15))

  val nameToSaleableItem = Map("A" -> apple, "B" -> banana, "C" -> crisps, "D" -> donuts)
  val discountedApples = BuyNCheaper(apple, 3, Money(130))
  val discountedBananas = BuyNCheaper(banana, 2, Money(45))
  val priceCalculator = new PriceCalculator(new ItemFinder(nameToSaleableItem), List(discountedApples, discountedBananas))

  def main(args: Array[String]) {
    if (args.length == 0) {
      println("Usage: The parameters are the items. Called A B C or D.")
      println("example  ./checkout.sh A B C   (result £1.00)")
      println("         ./checkout.sh A A A   (result £1.30)")
    }
    println(priceCalculator.calculatePayment(args: _*))
  }

}