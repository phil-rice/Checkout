package org.validoc.shopping

case class Money( amount: Int) {
  override def toString = {
    val rawAmount = f"£${Math.abs(amount) / 100.0}%.2f"
    if (amount >= 0) rawAmount else s"($rawAmount)"
  }

  def negated = Money(-amount)

  def +(other: Money) = Money(amount + other.amount)
  def -(other: Money) = Money(amount - other.amount)

  def *(n: Int) = Money(amount * n)
}

