int vacantPlaces = 0
for (i in 1..5) {
    def buildings = context.valueFor('player' + i, 'buildings')
    if (buildings == 'undefined') {
        buildings = []
    } else {
        buildings = buildings
                .replaceAll("\\[|\\]", '')
                .split(', ')
                .toList()
    }
    
    buildings.each { building ->
        def workplaces = context.valueFor(building, 'workplaces').toInteger()
        def workers = context.valueFor(building, 'workers').isNumber() ? context.valueFor(building, 'workers').toInteger() : 0
        vacantPlaces += (workplaces - workers)
    }
}

int colonistReserve = context.valueFor('board', 'colonistReserve').toInteger()
int playersNumber = context.valueFor('table', 'playersNum').toInteger()

return Math.min(Math.max(vacantPlaces, playersNumber), colonistReserve)
