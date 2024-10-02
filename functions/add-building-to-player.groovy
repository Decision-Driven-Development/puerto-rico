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
def building = context.valueFor('temp', 'building')

if (building != 'undefined') {
    buildings << building
}
buildings.toString()
