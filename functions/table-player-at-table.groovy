String player = context.valueFor('request', 'player')
for (i in 1..5) {
    if (context.valueFor('table', 'player' + i) == player) {
        return true
    }
}
false