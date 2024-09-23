def indices = context.valueFor('temp', 'plantations')
        .replaceAll("\\[|\\]", '')
        .split(',')
        .collect { it.toInteger() }
def first = indices.first()
context.setValueFor('temp', 'plantations', indices.drop(1).toString())
first