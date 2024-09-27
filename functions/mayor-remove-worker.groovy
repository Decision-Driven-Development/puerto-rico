def building = context.valueFor('request', 'building')
def workers = context.valueFor(building, 'workers').isNumber() ? context.valueFor(building, 'workers').toInteger() : 0

workers - 1