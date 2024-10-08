def player = context.valueFor("turnOrder", "3")
def currentColonists = context.valueFor(player, "colonists").isNumber() ? context.valueFor(player, "colonists").toInteger() : 0
def onShip = context.valueFor('board', 'colonistShip').toInteger()
def playersNum = context.valueFor('table', 'playersNum').toInteger()

def colonistsFromShip = (onShip / playersNum).toInteger()
if (onShip % playersNum > 2) {
    colonistsFromShip += 1
}

currentColonists + colonistsFromShip