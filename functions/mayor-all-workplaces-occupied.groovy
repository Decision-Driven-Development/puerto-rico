def player = context.valueFor('name-to-id', 'id')

def buildings = context.valueFor(player, 'buildings')
        .replaceAll("\\[|\\]", '')
        .split(', ')
        .toList()

buildings.findAll {
    def workers = context.valueFor(it, 'workers').isNumber() ? context.valueFor(it, 'workers').toInteger() : 0
    context.valueFor(it, 'workplaces').toInteger() > workers
}.isEmpty()