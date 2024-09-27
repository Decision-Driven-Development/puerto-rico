def stackPlantations = []
def dropPlantations = []

(1..50).each { i ->
    def owner = context.valueFor('plantation'+i,'owner')
    if (owner == 'stack') {
        stackPlantations << i
    } else if (owner == 'drop') {
        dropPlantations << i
    }
}

println "Stack: ${stackPlantations}"
println "Drop: ${dropPlantations}"

if (stackPlantations.size() > 0) {
    return stackPlantations.shuffled().first()
}

if (dropPlantations.size() > 0) {
    return dropPlantations.shuffled().first()
}

return 'undefined'