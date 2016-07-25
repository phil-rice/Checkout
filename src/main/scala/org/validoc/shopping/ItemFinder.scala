package org.validoc.shopping

class ItemNotFoundException(item: Item) extends Exception(s"Item [$item] was not found")

/** I expect the actual 'key' to be a bar code'. This allows me to test against apples and oranges readably, but in practice to have numbers for the item codes */
class ItemFinder(nameToSaleableItem: Map[Item, SaleableItem]) extends (Item => SaleableItem) {
  def apply(item: Item) = nameToSaleableItem.getOrElse(item, throw new ItemNotFoundException(item))
}
