def reserve = context.valueFor('board', "colonistReserve").toInteger()
def onShip = context.valueFor('board', "colonistShip").toInteger()
reserve - onShip