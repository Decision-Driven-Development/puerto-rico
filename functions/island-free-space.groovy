def playerId = context.valueFor('name-to-id', 'id')
def islandEmptySpace = 12
for (i in 1..50) {
    if (context.valueFor('plantation' + i, 'owner') == playerId) {
        islandEmptySpace--
    }
}

for (i in 1..8) {
    if (context.valueFor('quarry' + i, 'owner') == playerId) {
        islandEmptySpace--
    }
}

islandEmptySpace