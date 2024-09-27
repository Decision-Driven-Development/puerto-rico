def player = context.valueFor("turnOrder", "1")
def currentColonists = context.valueFor(player, "colonists").isNumber() ? context.valueFor(player, "colonists").toInteger() : 0
def privileged = context.valueFor('board', "colonistReserve").toInteger() > 0 ? 1 : 0
def onShip = context.valueFor('board', 'colonistShip').toInteger()
def playersNum = context.valueFor('table', 'playersNum').toInteger()

def colonistsFromShip = (onShip / playersNum).toInteger()
if (onShip % playersNum > 0) {
    colonistsFromShip += 1
}

currentColonists + privileged + colonistsFromShip