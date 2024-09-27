String player = context.valueFor('name-to-id', 'id')

def buildings = context.valueFor(player, 'buildings')
if (buildings == 'undefined') {
    buildings = []
} else {
    buildings = buildings
            .replaceAll("\\[|\\]", '')
            .split(', ')
            .toList()
}
def boardPlantationNum = context.valueFor('request', 'plantation')
def plantation = context.valueFor('board', 'plantation' + boardPlantationNum)

buildings << plantation
buildings.toString()
