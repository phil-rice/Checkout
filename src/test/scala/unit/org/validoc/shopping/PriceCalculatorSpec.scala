package unit.org.validoc.shopping

import org.scalatest.{FlatSpec, Matchers}
import org.validoc.shopping._

class PriceCalculatorSpec extends FlatSpec with Matchers with ShoppingCartsFixture {
  val discountedApples = BuyNCheaper(apple, 3, Money(130))
  val discountedBananas = BuyNCheaper(banana, 2, Money(45))

  "A shopping trolley price calculator, with no offers" should "add up the values of the items in a cart" in {
    val itemFinder = new ItemFinder(nameToSaleableItem)
    val priceCalculator = new PriceCalculator(itemFinder)
    priceCalculator.calculatePayment("A") shouldBe Money(50)
    priceCalculator.calculatePayment("B") shouldBe Money(30)
    priceCalculator.calculatePayment("A", "B") shouldBe Money(80)
    priceCalculator.calculatePayment("A", "A", "B", "A") shouldBe Money(180)
  }

  "A shopping trolley price calculator with two offers" should "add up the values of the items, including the discounts added by the offer calculator" in {
    val itemFinder = new ItemFinder(nameToSaleableItem)
    val priceCalculator = new PriceCalculator(itemFinder, List(discountedApples, discountedBananas))
    priceCalculator.calculatePayment("A") shouldBe Money(50)
    priceCalculator.calculatePayment("B") shouldBe Money(30)
    priceCalculator.calculatePayment("A", "B") shouldBe Money(80)
    priceCalculator.calculatePayment("A", "A", "B", "A") shouldBe Money(130 + 30)
    priceCalculator.calculatePayment("A", "A", "B", "A", "A", "B") shouldBe Money(130 + 50 + 45)
    priceCalculator.calculatePayment("A", "A", "A", "B", "B", "A", "A", "A", "A") shouldBe Money(130+130+45+50)
  }
}
