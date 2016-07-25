package unit.org.validoc.shopping

import org.scalatest.{FlatSpec, Matchers}
import org.validoc.shopping.{BuyNCheaper, Money, Offer, SaleableItem}

class BuyNCheaperSpec extends FlatSpec with Matchers with ShoppingCartsFixture {


  val discountedApple = SaleableItem(s"Buy 3 Apples for £1.30, means a discount of £0.20", Money(-20))
  val discountedBanana = SaleableItem(s"Buy 2 Bananas for £0.45, means a discount of £0.15", Money(-15))

  val discountedApples = BuyNCheaper(apple, 3, Money(130))
  val discountedBananas = BuyNCheaper(banana, 2, Money(45))


  "The buy N cheaper" should "add a discounted offer for every 2 items, when N is 2" in {
    discountedBananas.find(Seq()) shouldBe Seq()
    discountedBananas.find(Seq(apple, banana)) shouldBe Seq()
    discountedBananas.find(Seq(apple, banana, banana)) shouldBe Seq(discountedBanana)
    discountedBananas.find(Seq(apple, banana, banana)) shouldBe Seq(discountedBanana)
    discountedBananas.find(Seq(apple, apple, apple, banana, banana, banana, banana)) shouldBe Seq(discountedBanana, discountedBanana)
  }

  it should "add a discounted offer for every 3 items when N is 3" in {
    discountedApples.find(Seq()) shouldBe Seq()
    discountedApples.find(Seq(apple, apple)) shouldBe Seq()
    discountedApples.find(Seq(apple, apple, banana)) shouldBe Seq()
    discountedApples.find(Seq(apple, apple, apple, banana)) shouldBe Seq(discountedApple)
    discountedApples.find(Seq(apple, apple, apple, apple, banana)) shouldBe Seq(discountedApple)
    discountedApples.find(Seq(apple, apple, apple, apple, apple, banana)) shouldBe Seq(discountedApple)
    discountedApples.find(Seq(apple, apple, apple, apple, apple, apple, banana)) shouldBe Seq(discountedApple, discountedApple)
    discountedApples.find(Seq(apple, apple, apple, apple, apple, apple, apple, banana)) shouldBe Seq(discountedApple, discountedApple)
  }

}
