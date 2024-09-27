def building = context.valueFor('request', 'building')
def workplaces = context.valueFor(building, 'workplaces').toInteger()
def workers = context.valueFor(building, 'workers').isNumber() ? context.valueFor(building, 'workers').toInteger() : 0

return workers < workplaces