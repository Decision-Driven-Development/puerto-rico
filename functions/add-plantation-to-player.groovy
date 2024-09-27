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
def plantationNum = context.valueFor('request', 'plantation')

buildings << 'plantation' + plantationNum
buildings.toString()
