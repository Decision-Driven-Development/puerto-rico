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
def quarry = context.valueFor('function', 'quarry-available')

buildings << quarry
buildings.toString()