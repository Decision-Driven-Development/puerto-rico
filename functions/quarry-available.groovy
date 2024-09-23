for (i in 1..8) {
    if (context.valueFor('quarry' + i, 'owner') == 'board') {
        return 'quarry' + i
    }
}
false